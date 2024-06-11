package ptithcm.centerservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ptithcm.centerservice.DTORequest.GhSanPhamRequest;
import ptithcm.centerservice.DTORequest.GhThuCungRequest;
import ptithcm.centerservice.DTORequest.GioHangRequest;
import ptithcm.centerservice.DTOResponse.SanPhamDTO;
import ptithcm.centerservice.DTOResponse.ThuCungDTO;
import ptithcm.centerservice.Entity.GhsanphamPK;
import ptithcm.centerservice.Entity.GhthucungPK;
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

    @PostMapping("bo-thu-cung")
    public ResponseEntity<?> boThuCung(@RequestBody GhThuCungRequest ghThuCungRequest) {
        GhthucungPK ghthucungPK = new GhthucungPK();
        ghthucungPK.setMakhachhang(ghThuCungRequest.getMaKhachHang());
        ghthucungPK.setMathucung(ghThuCungRequest.getMaThuCung());
        boolean tontai = gioHangService.isExistsByIdThuCung(ghthucungPK);
        if(!tontai){
            return new ResponseEntity<>("Giỏ hàng thú cưng không tồn tại",HttpStatus.BAD_REQUEST);
        }
        else{
            gioHangService.deleteByIdThuCung(ghthucungPK);
            return new ResponseEntity<>("Xóa thành công", HttpStatus.OK);
        }
    }

    @PostMapping("bo-san-pham")
    public ResponseEntity<?> boSanPham(@RequestBody GhSanPhamRequest ghSanPhamRequest) {
        GhsanphamPK ghsanphamPK = new GhsanphamPK();
        ghsanphamPK.setMachinhanh(ghSanPhamRequest.getMaChiNhanh());
        ghsanphamPK.setMakhachhang(ghSanPhamRequest.getMaKhachHang());
        ghsanphamPK.setMasanpham(ghSanPhamRequest.getMaSanPham());

        boolean tontai = gioHangService.isExistsByIdSanPham(ghsanphamPK);
        if(!tontai){
            return new ResponseEntity<>("Giỏ hàng sản phẩm không tồn tại",HttpStatus.BAD_REQUEST);
        }
        else{
            gioHangService.deleteByIdSanPham(ghsanphamPK);
            return new ResponseEntity<>("Xóa thành công", HttpStatus.OK);
        }
    }

    @PostMapping("bo-tat-ca")
    public ResponseEntity<?> boAll(@RequestBody GioHangRequest gioHangRequest) {
        try{
            String makhachhang = gioHangRequest.getMaKhachHang();
            int machinhanh = gioHangRequest.getMaChiNhanh();
            gioHangService.xoaGioHangThuCung(makhachhang, machinhanh);
            gioHangService.xoaGioHangSanPham(makhachhang, machinhanh);
            return new ResponseEntity<>("Xóa thành công", HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
