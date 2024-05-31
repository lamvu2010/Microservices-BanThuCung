package com.example.identity_service.Controller;

import com.example.identity_service.DTOResponse.NhanVienDTO;
import com.example.identity_service.Entity.Hinhanh;
import com.example.identity_service.Entity.Nhanvien;
import com.example.identity_service.Entity.Taikhoan;
import com.example.identity_service.Service.NhanVienService;
import com.example.identity_service.Service.TaiKhoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/identity/nhanvien")
public class NhanVienController {

    @Autowired
    private NhanVienService nhanVienService;
    @Autowired
    private TaiKhoanService taiKhoanService;

    public NhanVienDTO convertToDTO(Nhanvien nhanvien) {
        NhanVienDTO nhanVienDTO = new NhanVienDTO();
        if (nhanvien.getMachinhanh() != null) {
            nhanVienDTO.setMaChiNhanh(nhanvien.getMachinhanh());
        }
        nhanVienDTO.setMaNhanVien(nhanvien.getManhanvien());
        nhanVienDTO.setHo(nhanvien.getHo());
        nhanVienDTO.setTen(nhanvien.getTen());
        nhanVienDTO.setCccd(nhanvien.getCccd());
        nhanVienDTO.setEmail(nhanvien.getEmail());
        nhanVienDTO.setChucVu(nhanvien.getChucvu());
        nhanVienDTO.setSoDienThoai(nhanvien.getSodienthoai());
        if (!nhanvien.getHinhanh().isEmpty()) {
            nhanVienDTO.setHinhAnh(new ArrayList<>());
            for (Hinhanh item : nhanvien.getHinhanh()) {
                nhanVienDTO.getHinhAnh().add(item.getMahinhanh());
            }
        }
        return nhanVienDTO;
    }

    @GetMapping
    public ResponseEntity<List<NhanVienDTO>> getAll() {
        List<Nhanvien> list = new ArrayList<>();
        List<NhanVienDTO> dtoList = new ArrayList<>();
        list = nhanVienService.findAll();
        if (list.isEmpty()) {
            return new ResponseEntity<>(dtoList, HttpStatus.OK);
        }
        for (Nhanvien nhanvien : list) {
            NhanVienDTO nhanVienDTO = convertToDTO(nhanvien);
            dtoList.add(nhanVienDTO);
        }
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable String id) {
        Optional<Nhanvien> nhanvien = nhanVienService.findById(id);
        if (nhanvien.isEmpty()) {
            return new ResponseEntity<>("Entity not found", HttpStatus.NOT_FOUND);
        } else {
            NhanVienDTO nhanVienDTO = new NhanVienDTO();
            nhanVienDTO = convertToDTO(nhanvien.orElse(null));
            return new ResponseEntity<>(nhanVienDTO, HttpStatus.OK);
        }
    }

//    @GetMapping("/moi")
//    public String maNVMoi() {
//        return nhanVienService.maNhanVienMoi();
//    }

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody NhanVienDTO nhanVienDTO) {
        Nhanvien nhanvien = new Nhanvien();
        nhanvien.setCccd(nhanVienDTO.getCccd());
        nhanvien.setHo(nhanVienDTO.getHo());
        nhanvien.setEmail(nhanVienDTO.getEmail());
        nhanvien.setTen(nhanVienDTO.getTen());
        nhanvien.setChucvu(nhanVienDTO.getChucVu());
        nhanvien.setSodienthoai(nhanVienDTO.getSoDienThoai());
        nhanvien.setManhanvien(nhanVienService.maNhanVienMoi());
        if (nhanVienDTO.getMaChiNhanh() != 0) {
            nhanvien.setMachinhanh(nhanVienDTO.getMaChiNhanh());
        }
        Taikhoan taikhoan = new Taikhoan();
        taikhoan.setTendangnhap(nhanvien.getManhanvien());
        taikhoan.setMatkhau("12345678910");
        taikhoan.setQuyen(nhanvien.getChucvu());
        taikhoan.setTrangthai(Boolean.TRUE);
        try {
            nhanvien = nhanVienService.save(nhanvien);
            taikhoan = taiKhoanService.save(taikhoan);
        } catch (Exception e) {
            return new ResponseEntity<>("Thêm thất bại", HttpStatus.BAD_REQUEST);
        }
        NhanVienDTO nhanVienDTO1 = convertToDTO(nhanvien);
        return new ResponseEntity<>(nhanVienDTO1, HttpStatus.OK);
        // lấy thông tin tài khoản bằng request mới
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody NhanVienDTO nhanVienDTO) {
        if (!nhanVienService.isExistsById(nhanVienDTO.getMaNhanVien())) {
            return new ResponseEntity<>("Nhân viên không tồn tại", HttpStatus.BAD_REQUEST);
        }
        Nhanvien nhanvien = nhanVienService.findById(nhanVienDTO.getMaNhanVien()).get();
        nhanvien.setCccd(nhanVienDTO.getCccd());
        nhanvien.setHo(nhanVienDTO.getHo());
        nhanvien.setEmail(nhanVienDTO.getEmail());
        nhanvien.setTen(nhanVienDTO.getTen());
        nhanvien.setChucvu(nhanVienDTO.getChucVu());
        nhanvien.setSodienthoai(nhanVienDTO.getSoDienThoai());
        nhanvien.setManhanvien(nhanVienDTO.getMaNhanVien());
        nhanvien = nhanVienService.save(nhanvien);
        NhanVienDTO nhanVienDTO1 = convertToDTO(nhanvien);
        return new ResponseEntity<>(nhanVienDTO1, HttpStatus.OK);
    }

    @DeleteMapping("/{id}/")
    public ResponseEntity<?> delete(@PathVariable String id) {
        if (!nhanVienService.isExistsById(id)) {
            return new ResponseEntity<>("Nhân viên không tồn tại", HttpStatus.BAD_REQUEST);
        }
        nhanVienService.deleteById(id);
        if (!nhanVienService.isExistsById(id)) {
            return new ResponseEntity<>("Xóa thành công", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Xóa thất bại", HttpStatus.BAD_REQUEST);
        }
    }
}