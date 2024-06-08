package ptithcm.centerservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ptithcm.centerservice.DTORequest.BangGiaThuCungRequest;
import ptithcm.centerservice.DTOResponse.BangGiaThuCungDTO;
import ptithcm.centerservice.Entity.Banggia;
import ptithcm.centerservice.Entity.Ctbanggiathucung;
import ptithcm.centerservice.Entity.CtbanggiathucungPK;
import ptithcm.centerservice.Entity.Hinhanh;
import ptithcm.centerservice.Entity.Sanpham;
import ptithcm.centerservice.Entity.Thucung;
import ptithcm.centerservice.File.StorageService;
import ptithcm.centerservice.Services.BangGiaService;
import ptithcm.centerservice.Services.BangGiaThuCungService;
import ptithcm.centerservice.Services.ThuCungService;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/center/ct-thu-cung")
public class BangGiaThuCungController {
    @Autowired
    BangGiaThuCungService bangGiaThuCungService;
    @Autowired
    ThuCungService thuCungService;
    @Autowired
    BangGiaService bangGiaService;
    @Autowired
    StorageService storageService;

    @GetMapping
    public ResponseEntity<?> getChiTietBangGiaThuCung() {
        List<Map<?, ?>> result = bangGiaThuCungService.danhSachThuCungBan();
        if (result.isEmpty()) {
            return new ResponseEntity<>("Không có dữ liệu", HttpStatus.BAD_REQUEST);
        }
        List<BangGiaThuCungDTO> dtoList = new ArrayList<>();
        for (Map<?, ?> item : result) {
            BangGiaThuCungDTO bangGiaThuCungDTO = new BangGiaThuCungDTO();
            if (item.get("MABANGGIA") != null) {
                bangGiaThuCungDTO.setMaBangGia((long) item.get("MABANGGIA"));
                bangGiaThuCungDTO.setThoiGianBatDau((Timestamp) item.get("THOIGIANBATDAU"));
                bangGiaThuCungDTO.setThoiGianKetThuc((Timestamp) item.get("THOIGIANKETTHUC"));
                bangGiaThuCungDTO.setGiaKhuyenMai((BigDecimal) item.get("GIAKM"));
            }
            if (item.get("MACHINHANH") != null) {
                bangGiaThuCungDTO.setMaChiNhanh((int) item.get("MACHINHANH"));
                bangGiaThuCungDTO.setTenChiNhanh((String) item.get("TENCHINHANH"));
            }
            if (item.get("MATHUCUNG") != null) {
                bangGiaThuCungDTO.setMaThuCung((long) item.get("MATHUCUNG"));
                bangGiaThuCungDTO.setTenThuCung((String) item.get("TENTHUCUNG"));
                bangGiaThuCungDTO.setMoTa((String) item.get("MOTA"));
                bangGiaThuCungDTO.setGiaHienTai((BigDecimal) item.get("GIAHIENTAI"));
                Thucung thucung = thuCungService.findById(bangGiaThuCungDTO.getMaThuCung()).get();
                List<Hinhanh> hinhanhList = thucung.getHinhanh();
                if(hinhanhList!=null&&hinhanhList.size()!=0){
                long idHinhAnh = hinhanhList.get(0).getMahinhanh();
                try{
                    byte[] image = storageService.downloadImageFromFileSystem(idHinhAnh);
                    bangGiaThuCungDTO.setHinhAnh(image);

                }
                catch(Exception e){
                    System.out.println(e.getMessage());
                    bangGiaThuCungDTO.setHinhAnh(null);
                }}
                else{
                    bangGiaThuCungDTO.setHinhAnh(null);
                }
            }
            if (item.get("MAGIONG") != null) {
                bangGiaThuCungDTO.setMaGiong((int) item.get("MAGIONG"));
                bangGiaThuCungDTO.setTenGiong((String) item.get("TENGIONG"));
            }
            dtoList.add(bangGiaThuCungDTO);
        }
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> insertChiTietBangGiaThuCung(@RequestBody List<BangGiaThuCungRequest> bangGiaThuCungRequests) {
        try {
            for (BangGiaThuCungRequest item : bangGiaThuCungRequests) {
                if (item.getMaBangGia() == 0 || item.getMaThuCung() == 0) {
                    continue;
                }
                CtbanggiathucungPK ctbanggiathucungPK = new CtbanggiathucungPK(item.getMaBangGia(), item.getMaThuCung());
                Ctbanggiathucung ctbanggiathucung = new Ctbanggiathucung();
                ctbanggiathucung.setId(ctbanggiathucungPK);
                ctbanggiathucung.setDongia(item.getDonGia());
                Thucung thucung = thuCungService.findById(ctbanggiathucungPK.getMathucung()).orElse(null);
                Banggia banggia = bangGiaService.findById(ctbanggiathucungPK.getMabanggia()).orElse(null);
                ctbanggiathucung.setThucung(thucung);
                ctbanggiathucung.setBanggia(banggia);
                bangGiaThuCungService.save(ctbanggiathucung);
            }
            return new ResponseEntity<>("Cập nhật thành công", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Cập nhật thất bại", HttpStatus.BAD_REQUEST);
        }
    }
}