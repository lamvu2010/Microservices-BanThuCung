package ptithcm.centerservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ptithcm.centerservice.DTOResponse.*;
import ptithcm.centerservice.Entity.Hinhanh;
import ptithcm.centerservice.Entity.Thucung;
import ptithcm.centerservice.File.StorageService;
import ptithcm.centerservice.Services.ChiNhanhService;
import ptithcm.centerservice.Services.GiongService;
import ptithcm.centerservice.Services.ThuCungService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/center/thucung")
public class ThuCungController {
    @Autowired
    ThuCungService thuCungService;
    @Autowired
    ChiNhanhService chiNhanhService;
    @Autowired
    GiongService giongService;
    @Autowired
    StorageService storageService;

    public HinhAnhDTO convertToDTO(Hinhanh hinhanh) throws IOException {
        HinhAnhDTO hinhAnhDTO = new HinhAnhDTO();
        hinhAnhDTO.setMaHinhAnh(hinhanh.getMahinhanh());
        hinhAnhDTO.setPath(hinhanh.getPath());
        hinhAnhDTO.setTenHinhAnh(hinhanh.getTenhinhanh());
        hinhAnhDTO.setLoaiHinhAnh(hinhanh.getLoaihinhanh());
        hinhAnhDTO.setTenDuyNhat(hinhanh.getTenduynhat());
        hinhAnhDTO.setSource(storageService.downloadImageFromFileSystem(hinhanh.getMahinhanh()));
        return hinhAnhDTO;
    }

    public ThuCungDTO convertToDTO(Thucung thucung) {
        ThuCungDTO thuCungDTO = new ThuCungDTO();
        if(thucung == null){
            return thuCungDTO;
        }
        thuCungDTO.setMaThuCung(thucung.getMathucung());
        thuCungDTO.setTenThuCung(thucung.getTenthucung());
        if (thucung.getTrangthaiban() != null) {
            thuCungDTO.setTrangThaiBan(thucung.getTrangthaiban());
        }
        thuCungDTO.setChu(thucung.getChu());
        thuCungDTO.setGiaHienTai(thucung.getGiahientai());
        thuCungDTO.setMoTa(thucung.getMota());
        thuCungDTO.setChiNhanh(new ChiNhanhDTO());
        thuCungDTO.setGiong(new GiongDTO());
        thuCungDTO.getGiong().setLoaiThuCung(new LoaiThuCungDTO());
        // thucung.getChiNhanh() là giá trị null thì không thể getMaChiNhanh();
        // cần kiểm tra
        if (thucung.getChinhanh() != null) {
            thuCungDTO.getChiNhanh().setMaChiNhanh(thucung.getChinhanh().getMachinhanh());
            thuCungDTO.getChiNhanh().setTenChiNhanh(thucung.getChinhanh().getTenchinhanh());
        }
        if (thucung.getGiong() != null) {
            thuCungDTO.getGiong().setMaGiong(thucung.getGiong().getMagiong());
            thuCungDTO.getGiong().setTengiong(thucung.getGiong().getTengiong());
            if (thucung.getGiong().getLoaithucung() != null) {
                thuCungDTO.getGiong().getLoaiThuCung().setMaLoaiThuCung(thucung.getGiong().getLoaithucung().getMaloaithucung());
                thuCungDTO.getGiong().getLoaiThuCung().setTenLoaiThuCung(thucung.getGiong().getLoaithucung().getTenloaithucung());
            }
        }
        if(thucung.getHinhanh()!=null&&!thucung.getHinhanh().isEmpty()){
            thuCungDTO.setHinhAnh(new ArrayList<>());
            for(Hinhanh item : thucung.getHinhanh()){
                thuCungDTO.getHinhAnh().add(item.getMahinhanh());
            }
        }
        thuCungDTO.setSoLuongTon(thuCungDTO.getSoLuongTon());
        return thuCungDTO;
    }

    // Lay danh sach thu cung tat ca thu cung
    @GetMapping
    public ResponseEntity<List<ThuCungDTO>> getAll() {
        List<ThuCungDTO> dtoList = new ArrayList<>();
        List<Thucung> thucungList = new ArrayList<>();
        thucungList = thuCungService.findAll();
        for (Thucung thucung : thucungList) {
            ThuCungDTO thuCungDTO = convertToDTO(thucung);
            dtoList.add(thuCungDTO);
        }
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    // Lay chi tiet thu cung dua vao id
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable long id) {
        if (!thuCungService.isExistsById(id)) {
            return new ResponseEntity<>("Thú cưng không tồn tại", HttpStatus.BAD_REQUEST);
        }
        Optional<Thucung> thucung = thuCungService.findById(id);
        assert thucung.orElse(null) != null;
        ThuCungDTO thuCungDTO = convertToDTO(thucung.orElse(null));
        return new ResponseEntity<>(thuCungDTO, HttpStatus.OK);
    }

    // Them thu cung
    @PostMapping
    public ResponseEntity<?> insert(@RequestBody ThuCungDTO thuCungDTO) {
        Thucung thucung = new Thucung();
        thucung.setTenthucung(thuCungDTO.getTenThuCung());
        thucung.setChu(thuCungDTO.getChu());
        thucung.setMota(thuCungDTO.getMoTa());
        thucung.setGiahientai(thuCungDTO.getGiaHienTai());
        thucung.setTrangthaiban(thuCungDTO.getTrangThaiBan());
        thucung.setChinhanh(chiNhanhService.findById(thuCungDTO.getChiNhanh().getMaChiNhanh()).orElse(null));
        thucung.setGiong(giongService.findById(thuCungDTO.getGiong().getMaGiong()).orElse(null));
        thucung = thuCungService.save(thucung);
        if (thuCungService.isExistsById(thucung.getMathucung())) {
            ThuCungDTO thuCungDTO1 = convertToDTO(thucung);
            return new ResponseEntity<>(thuCungDTO1, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Them that bai", HttpStatus.BAD_REQUEST);
        }
    }

    // Sua thông tin thu cung
    @PutMapping
    public ResponseEntity<?> update(@RequestBody ThuCungDTO thuCungDTO) {
        if (!thuCungService.isExistsById(thuCungDTO.getMaThuCung())) {
            return new ResponseEntity<>("Thú cưng khong ton tai", HttpStatus.BAD_REQUEST);
        }
        Thucung thucung = new Thucung();
        thucung.setMathucung(thuCungDTO.getMaThuCung());
        thucung.setTenthucung(thuCungDTO.getTenThuCung());
        thucung.setChu(thuCungDTO.getChu());
        thucung.setMota(thuCungDTO.getMoTa());
        thucung.setGiahientai(thuCungDTO.getGiaHienTai());
        thucung.setTrangthaiban(thuCungDTO.getTrangThaiBan());
        thucung.setChinhanh(chiNhanhService.findById(thuCungDTO.getChiNhanh().getMaChiNhanh()).orElse(null));
        thucung.setGiong(giongService.findById(thuCungDTO.getGiong().getMaGiong()).orElse(null));
        thucung = thuCungService.save(thucung);
        ThuCungDTO thuCungDTO1 = convertToDTO(thucung);
        return new ResponseEntity<>(thuCungDTO1, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        boolean tontai = thuCungService.isExistsById(id);
        if (!tontai) {
            return new ResponseEntity<>("Thú cưng không tồn tại", HttpStatus.BAD_REQUEST);
        }
        Optional<Thucung> thucung = thuCungService.findById(id);
        thuCungService.delete(thucung.orElse(null));
        tontai = thuCungService.isExistsById(id);
        if (!tontai) {
            return new ResponseEntity<>("Xóa thành công", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Xóa thất bại", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/soluongton")
    public String capNhatSoLuongTon(@RequestBody ThuCungDTO thuCungDTO){
        System.out.println("Có nhận request");
        Thucung thucung = thuCungService.findById(thuCungDTO.getMaThuCung()).get();
        thucung.setSoluongton(thucung.getSoluongton() + thuCungDTO.getSoLuongTon());
        try{
            thuCungService.save(thucung);
            return "Cập nhật thành công";
        }catch (Exception e){
            return "Cập nhật thất bại";
        }
    }
}
