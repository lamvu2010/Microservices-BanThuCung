package ptithcm.centerservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ptithcm.centerservice.DTORequest.DonNhapSanPhamRequest;
import ptithcm.centerservice.DTORequest.DonNhapThuCungRequest;
import ptithcm.centerservice.DTOResponse.ChiNhanhDTO;
import ptithcm.centerservice.DTOResponse.DonNhapHangDTO;
import ptithcm.centerservice.DTOResponse.GiongDTO;
import ptithcm.centerservice.DTOResponse.LoaiThuCungDTO;
import ptithcm.centerservice.DTOResponse.ThuCungDTO;
import ptithcm.centerservice.Entity.Donnhaphang;
import ptithcm.centerservice.Entity.Giong;
import ptithcm.centerservice.Entity.Thucung;
import ptithcm.centerservice.Services.ApiService;
import ptithcm.centerservice.Services.NhapHangService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("center/nhaphang")
public class NhapHangController {

    @Autowired
    NhapHangService nhapHangService;

    // public DonNhapHangDTO convertToDTO(Donnhaphang donnhaphang){
    //     DonNhapHangDTO donNhapHangDTO = new DonNhapHangDTO();
    //     if(donnhaphang == null){
    //         return donNhapHangDTO;
    //     }
    //     donNhapHangDTO.setMaDonNhapHang(donnhaphang.getMadonnhaphang());
    //     donNhapHangDTO.setMaNhanVien(donnhaphang.getManhanvien());
    //     donNhapHangDTO.setNgayLap(donnhaphang.getNgaylap());
    //     donNhapHangDTO.setChiNhanhDTO(new ChiNhanhDTO());
    //     if(donnhaphang.getChinhanh()!= null){
    //         donNhapHangDTO.getChiNhanhDTO().setMaChiNhanh(donnhaphang.getChinhanh().getMachinhanh());
    //         donNhapHangDTO.getChiNhanhDTO().setTenChiNhanh(donnhaphang.getChinhanh().getTenchinhanh());
    //     }
    //     return donNhapHangDTO;
    // }

    @GetMapping
    public ResponseEntity<List<DonNhapHangDTO>> getAll() {
        List<DonNhapHangDTO> dtoList = new ArrayList<>();
        List<Donnhaphang> donNhapHangList = new ArrayList<>();
        donNhapHangList=nhapHangService.findAll();
        for (Donnhaphang donnhaphang : donNhapHangList) {
            DonNhapHangDTO donNhapHangDTO = nhapHangService.convertTODTO(donnhaphang);
            dtoList.add(donNhapHangDTO);
        }
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    @PostMapping("/tao-phieu-nhap")
    public ResponseEntity<?> taoPhieuNhap(@RequestBody DonNhapHangDTO donNhapHangDTO) {
        try {
            DonNhapHangDTO donNhapHangDTO1 = nhapHangService.taoDonNhapHang(donNhapHangDTO);
            return new ResponseEntity<>(donNhapHangDTO1, HttpStatus.OK);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>("Tạo thất bại" + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/them-san-pham")
    public ResponseEntity<?> themSanPham(@RequestBody List<DonNhapSanPhamRequest> list){
        try{
            nhapHangService.nhapSanPham(list);
            return new ResponseEntity<>("Thêm thành công",HttpStatus.OK);
        }catch (IllegalStateException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/them-thu-cung")
    public ResponseEntity<?> themThuCung(@RequestBody List<DonNhapThuCungRequest> list){
        try{
            nhapHangService.nhapThuCung(list);
            return new ResponseEntity<>("Thêm thành công",HttpStatus.OK);
        }catch (IllegalStateException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
