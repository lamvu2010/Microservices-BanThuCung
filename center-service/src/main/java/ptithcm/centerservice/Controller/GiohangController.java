package ptithcm.centerservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ptithcm.centerservice.DTORequest.GhSanPhamRequest;
import ptithcm.centerservice.DTORequest.GhThuCungRequest;
import ptithcm.centerservice.DTORequest.GioHangRequest;
import ptithcm.centerservice.DTOResponse.SanPhamDTO;
import ptithcm.centerservice.DTOResponse.ThuCungDTO;
import ptithcm.centerservice.Services.GioHangService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/center/gio-hang")
public class GiohangController {

    @Autowired
    private GioHangService gioHangService;

    @PostMapping("/thu-cung")
    public ResponseEntity<?> getThuCung(@RequestBody GioHangRequest gioHangRequest) {
        List<ThuCungDTO> list = new ArrayList<>();
        list = gioHangService.thuCungTrongGiohang(gioHangRequest.getMaChiNhanh(), gioHangRequest.getMaKhachHang());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/san-pham")
    public ResponseEntity<?> getSanPham(@RequestBody GioHangRequest gioHangRequest) {
        List<SanPhamDTO> list = new ArrayList<>();
        list = gioHangService.sanPhamTrongGioHang(gioHangRequest.getMaChiNhanh(), gioHangRequest.getMaKhachHang());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("them-thu-cung")
    public ResponseEntity<?> themThuCung(@RequestBody GhThuCungRequest ghThuCungRequest) {
        try {
            gioHangService.themThuCung(ghThuCungRequest);
            return new ResponseEntity<>("Thêm thành công", HttpStatus.OK);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>("Thêm thất bại" + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("them-san-pham")
    public ResponseEntity<?> themSanPham(@RequestBody GhSanPhamRequest ghSanPhamRequest) {
        try {
            gioHangService.themSanPham(ghSanPhamRequest);
            return new ResponseEntity<>("Thêm thành công", HttpStatus.OK);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>("Thêm thất bại" + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
