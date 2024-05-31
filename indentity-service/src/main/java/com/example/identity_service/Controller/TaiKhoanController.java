package com.example.identity_service.Controller;

import com.example.identity_service.DTOResponse.TaiKhoanDTO;
import com.example.identity_service.Entity.Taikhoan;
import com.example.identity_service.Service.KhachHangService;
import com.example.identity_service.Service.NhanVienService;
import com.example.identity_service.Service.TaiKhoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/identity/tk")
public class TaiKhoanController {
    @Autowired
    private TaiKhoanService taiKhoanService;
    @Autowired
    private NhanVienService nhanVienService;
    @Autowired
    private KhachHangService khachHangService;

    public TaiKhoanDTO convertToDTO(Taikhoan taikhoan){
        TaiKhoanDTO taiKhoanDTO = new TaiKhoanDTO();
        taiKhoanDTO.setTenDangNhap(taikhoan.getTendangnhap());
        taiKhoanDTO.setMatKhau(taikhoan.getMatkhau());
        taiKhoanDTO.setQuyen(taikhoan.getQuyen());
        taiKhoanDTO.setTrangThai(taikhoan.getTrangthai());
        return taiKhoanDTO;
    }
    @GetMapping
    public ResponseEntity<?> findAll(){
        List<Taikhoan> list = new ArrayList<>();
        List<TaiKhoanDTO> dtoList = new ArrayList<>();
        list = taiKhoanService.findAll();
        if(list.isEmpty()){
            return new ResponseEntity<>("Không có dữ liệu", HttpStatus.BAD_REQUEST);
        }
        for(Taikhoan taikhoan: list){
            TaiKhoanDTO taiKhoanDTO= convertToDTO(taikhoan);
            dtoList.add(taiKhoanDTO);
        }
        return new ResponseEntity<>(dtoList,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable String id){
        if(!taiKhoanService.isExistsById(id)){
            return new ResponseEntity<>("Tài khoản không tồn tại",HttpStatus.BAD_REQUEST);
        }
        Taikhoan taikhoan = taiKhoanService.findById(id).get();
        TaiKhoanDTO taiKhoanDTO= convertToDTO(taikhoan);
        return new ResponseEntity<>(taiKhoanDTO,HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody TaiKhoanDTO taiKhoanDTO){
        if(!taiKhoanService.isExistsById(taiKhoanDTO.getTenDangNhap())){
            return new ResponseEntity<>("Tài khoản không tồn tại",HttpStatus.BAD_REQUEST);
        }
        Taikhoan taikhoan = taiKhoanService.findById(taiKhoanDTO.getTenDangNhap()).get();
        taikhoan.setMatkhau(taiKhoanDTO.getMatKhau());
        taikhoan.setTrangthai(taiKhoanDTO.getTrangThai());
        try{
            taikhoan = taiKhoanService.save(taikhoan);
            return new ResponseEntity<>("Cập nhật thành công", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Cập nhật thất bại", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/reset")
    public ResponseEntity<?> reset(@RequestBody TaiKhoanDTO taiKhoanDTO){
        if(!taiKhoanService.isExistsById(taiKhoanDTO.getTenDangNhap())){
            return new ResponseEntity<>("Tài khoản không tồn tại",HttpStatus.BAD_REQUEST);
        }
        Taikhoan taikhoan = taiKhoanService.findById(taiKhoanDTO.getTenDangNhap()).get();
        taikhoan.setMatkhau(UUID.randomUUID().toString().substring(0, 10));
        try{
            taikhoan = taiKhoanService.save(taikhoan);
            return new ResponseEntity<>(taikhoan.getMatkhau(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Cập nhật thất bại", HttpStatus.BAD_REQUEST);
        }
    }
}
