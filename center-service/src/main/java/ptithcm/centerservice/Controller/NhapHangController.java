package ptithcm.centerservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ptithcm.centerservice.DTORequest.DonNhapSanPhamRequest;
import ptithcm.centerservice.DTORequest.DonNhapThuCungRequest;
import ptithcm.centerservice.DTOResponse.DonNhapHangDTO;
import ptithcm.centerservice.Services.ApiService;
import ptithcm.centerservice.Services.NhapHangService;

import java.util.List;

@RestController
@RequestMapping("center/nhaphang")
public class NhapHangController {

    @Autowired
    NhapHangService nhapHangService;


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
