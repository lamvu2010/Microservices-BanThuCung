package ptithcm.centerservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ptithcm.centerservice.DTOResponse.LoaiSanPhamDTO;
import ptithcm.centerservice.DTOResponse.SanPhamDTO;
import ptithcm.centerservice.Entity.*;
import ptithcm.centerservice.Repositories.CtSanPhamRepo;
import ptithcm.centerservice.Services.ChiNhanhService;
import ptithcm.centerservice.Services.ChiTietSanPhamService;
import ptithcm.centerservice.Services.LoaiSanPhamService;
import ptithcm.centerservice.Services.SanPhamService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/center/sanpham")
public class SanPhamController {
    @Autowired
    private SanPhamService sanPhamService;
    @Autowired
    private ChiNhanhService chiNhanhService;
    @Autowired
    private LoaiSanPhamService loaiSanPhamService;
    @Autowired
    private ChiTietSanPhamService chiTietSanPhamService;

    // Lay danh sach san pham theo id loai
//    @GetMapping("/loaiSanPham/{id}")
//    public ResponseEntity<List<SanPhamDTO>> findByIdLoai(@PathVariable int id) {
//        List<SanPhamDTO> list = new ArrayList<>();
//        if (loaiSanPhamService.existsById(id)) {
//            Optional<Loaisanpham> loaisanpham = loaiSanPhamService.findById(id);
//            List<Sanpham> listSp = sanPhamService.findByIdLoai(loaisanpham.orElse(null));
//            list = sanPhamDTO(listSp);
//            return new ResponseEntity<>(list, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(list, HttpStatus.NOT_FOUND);
//        }
//    }

    // lay danh sach san pham
    @GetMapping
    public ResponseEntity<List<SanPhamDTO>> getAll() {
        List<Ctsanpham> list = chiTietSanPhamService.findAll();
        List<SanPhamDTO> dtoList = new ArrayList<>();
        for (Ctsanpham item : list) {
            SanPhamDTO sanPhamDTO = convertToDTO(item);
            dtoList.add(sanPhamDTO);
        }
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    // List san pham to list sanphamDTO
//    public List<SanPhamDTO> sanPhamDTO(List<Sanpham> list) {
//        List<SanPhamDTO> listJson = new ArrayList<>();
//        for (Sanpham item : list) {
//            SanPhamDTO sp = new SanPhamDTO();
//            sp.setMaSanPham(item.getMasanpham());
//            sp.setTenSanPham(item.getTensanpham());
//            sp.setGiaHienTai(item.getGiahientai());
//            sp.setLoaiSanPham(new LoaiSanPhamDTO());
//            if (item.getLoaisanpham() != null) {
//                sp.getLoaiSanPham().setMaLoaiSanPham(item.getLoaisanpham().getMaloaisanpham());
//                sp.getLoaiSanPham().setTenLoaiSanPham(item.getLoaisanpham().getTenloaisanpham());
//            }
//            listJson.add(sp);
//        }
//        return listJson;
//    }

    public SanPhamDTO convertToDTO(Ctsanpham ctsanpham) {
        SanPhamDTO sanPhamDTO = new SanPhamDTO();
        if (ctsanpham == null) {
            return sanPhamDTO;
        }
        sanPhamDTO.setMaSanPham(ctsanpham.getSanpham().getMasanpham());
        sanPhamDTO.setTenSanPham(ctsanpham.getSanpham().getTensanpham());
        sanPhamDTO.setGiaHienTai(ctsanpham.getSanpham().getGiahientai());
        sanPhamDTO.setLoaiSanPham(new LoaiSanPhamDTO());
        if (ctsanpham.getSanpham().getLoaisanpham() != null) {
            sanPhamDTO.getLoaiSanPham().setMaLoaiSanPham(ctsanpham.getSanpham().getLoaisanpham().getMaloaisanpham());
            sanPhamDTO.getLoaiSanPham().setTenLoaiSanPham(ctsanpham.getSanpham().getLoaisanpham().getTenloaisanpham());
        }
        if (ctsanpham.getSanpham().getHinhanh()!=null&&!ctsanpham.getSanpham().getHinhanh().isEmpty()) {
            sanPhamDTO.setHinhAnh(new ArrayList<>());
            for (Hinhanh item : ctsanpham.getSanpham().getHinhanh()) {
                sanPhamDTO.getHinhAnh().add(item.getMahinhanh());
            }
        }
        if (ctsanpham.getChinhanh() != null) {
            sanPhamDTO.setMaChiNhanh(ctsanpham.getChinhanh().getMachinhanh());
        }
        sanPhamDTO.setSoLuongTon(ctsanpham.getSoluongton());
        return sanPhamDTO;
    }

    // Them san pham
    @PostMapping
    public ResponseEntity<?> insert(@RequestBody SanPhamDTO sanPhamDTO) {
        try {
            Sanpham sp = new Sanpham();
            Ctsanpham ctsanpham = new Ctsanpham();
            CtsanphamPK ctsanphamPK = new CtsanphamPK();

            sp.setTensanpham(sanPhamDTO.getTenSanPham());
            sp.setGiahientai(sanPhamDTO.getGiaHienTai());
            sp.setLoaisanpham(loaiSanPhamService.findById(sanPhamDTO.getLoaiSanPham().getMaLoaiSanPham()).orElse(null));
            sp = sanPhamService.save(sp);
            ctsanphamPK.setMasanpham(sp.getMasanpham());
            ctsanphamPK.setMachinhanh(sanPhamDTO.getMaChiNhanh());

            if (!chiNhanhService.existById(sanPhamDTO.getMaChiNhanh())) {
                return new ResponseEntity<>("Chi nhánh không tồn tại", HttpStatus.BAD_REQUEST);
            }
            Chinhanh chinhanh = chiNhanhService.findById(sanPhamDTO.getMaChiNhanh()).get();
        
            ctsanpham.setId(ctsanphamPK);
            ctsanpham.setChinhanh(chinhanh);
            ctsanpham.setSanpham(sp);
            ctsanpham.setSoluongton(sanPhamDTO.getSoLuongTon());
            chiTietSanPhamService.save(ctsanpham);
            SanPhamDTO sanPhamDTO1 = convertToDTO(ctsanpham);
            return new ResponseEntity<>(sanPhamDTO1, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Thêm thất bại", HttpStatus.BAD_REQUEST);
        }
    }

    //Sua san pham
    @PutMapping
    public ResponseEntity<?> update(@RequestBody SanPhamDTO sanPhamDTO) {
        Sanpham sp = new Sanpham();
        if (!sanPhamService.existsById(sanPhamDTO.getMaSanPham())) {
            return new ResponseEntity<>(sp, HttpStatus.BAD_REQUEST);
        }
        sp.setMasanpham(sanPhamDTO.getMaSanPham());
        sp.setGiahientai(sanPhamDTO.getGiaHienTai());
        sp.setTensanpham(sanPhamDTO.getTenSanPham());
        sp.setLoaisanpham(loaiSanPhamService.findById(sanPhamDTO.getLoaiSanPham().getMaLoaiSanPham()).orElse(null));
        sp = sanPhamService.save(sp);
        Ctsanpham ctsanpham = chiTietSanPhamService.findById(new CtsanphamPK(sanPhamDTO.getMaChiNhanh(),sanPhamDTO.getMaSanPham())).get();
        SanPhamDTO sanPhamDTO1 = convertToDTO(ctsanpham);
        return new ResponseEntity<>(sanPhamDTO1, HttpStatus.OK);
    }

    // Xoa san pham
    @DeleteMapping("/{sanpham}/{chinhanh}")
    public ResponseEntity<?> delete(@PathVariable Long sanpham,@PathVariable int chinhanh) {
        CtsanphamPK ctsanphamPK = new CtsanphamPK(chinhanh,sanpham);
        boolean spTonTai = chiTietSanPhamService.existsById(ctsanphamPK);
        if (spTonTai == false) {
            return new ResponseEntity<>("Id khong ton tai", HttpStatus.NOT_FOUND);
        }
        Optional<Ctsanpham> ctsanpham = chiTietSanPhamService.findById(ctsanphamPK);
        chiTietSanPhamService.delete(ctsanpham.orElse(null));
        spTonTai = chiTietSanPhamService.existsById(ctsanphamPK);
        if (spTonTai == true) {
            return new ResponseEntity<>("Xoa that bai", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>("Xoa thanh cong", HttpStatus.OK);
        }
    }

    @PutMapping("/soluongton")
    public String capNhatSoLuongTon(@RequestBody SanPhamDTO sanPhamDTO){
        System.out.println(sanPhamDTO.getMaChiNhanh());
        System.out.println(sanPhamDTO.getMaSanPham());
        CtsanphamPK ctsanphamPK = new CtsanphamPK(sanPhamDTO.getMaChiNhanh(), sanPhamDTO.getMaSanPham());
        Ctsanpham ctsanpham = chiTietSanPhamService.findById(ctsanphamPK).get();
        ctsanpham.setSoluongton(ctsanpham.getSoluongton() + sanPhamDTO.getSoLuongTon());
        try{
            ctsanpham = chiTietSanPhamService.save(ctsanpham);
            return "Cập nhật thành công";
        }
        catch (Exception e){
            return "Cập nhật thất bại";
        }
    }
}
