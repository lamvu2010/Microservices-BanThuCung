package com.example.identity_service.Controller;

import com.example.identity_service.DTOResponse.KhachHangDTO;
import com.example.identity_service.Entity.Khachhang;
import com.example.identity_service.Entity.Khachhang;
import com.example.identity_service.Service.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/identity/khachhang")
public class KhachHangController {

    @Autowired
    private KhachHangService khachHangService;

    public KhachHangDTO convertToDTO(Khachhang khachhang){
        KhachHangDTO khachHangDTO = new KhachHangDTO();
        khachHangDTO.setMaKhachHang(khachhang.getMakhachhang());
        khachHangDTO.setHo(khachhang.getHo());
        khachHangDTO.setTen(khachhang.getTen());
        khachHangDTO.setNgaySinh(khachhang.getNgaysinh());
        khachHangDTO.setGioiTinh(khachhang.getGioitinh());
        khachHangDTO.setEmail(khachhang.getEmail());
        khachHangDTO.setSoDienThoai(khachhang.getSodienthoai());
        khachHangDTO.setCccd(khachhang.getCccd());
        khachHangDTO.setDiaChi(khachhang.getDiachi());
        return khachHangDTO;
    }

    @GetMapping
    public ResponseEntity<?> listKH(){
        List<Khachhang> list = new ArrayList<>();
        List<KhachHangDTO> dtoList = new ArrayList<>();
        list = khachHangService.findAll();
        for(Khachhang item : list){
            KhachHangDTO khachHangDTO = convertToDTO(item);
            dtoList.add(khachHangDTO);
        }
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable String id) {
        Optional<Khachhang> khachhang = khachHangService.findById(id);
        if (khachhang.isEmpty()) {
            return new ResponseEntity<>("Entity not found", HttpStatus.NOT_FOUND);
        } else {
            KhachHangDTO khachHangDTO = new KhachHangDTO();
            khachHangDTO = convertToDTO(khachhang.orElse(null));
            return new ResponseEntity<>(khachHangDTO, HttpStatus.OK);
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody KhachHangDTO khachHangDTO) {
        if (!khachHangService.isExistsById(khachHangDTO.getMaKhachHang())) {
            return new ResponseEntity<>("Khách hàng không tồn tại", HttpStatus.BAD_REQUEST);
        }
        Khachhang khachhang = khachHangService.findById(khachHangDTO.getMaKhachHang()).get();
        khachhang.setCccd(khachHangDTO.getCccd());
        khachhang.setHo(khachHangDTO.getHo());
        khachhang.setEmail(khachHangDTO.getEmail());
        khachhang.setTen(khachHangDTO.getTen());
        khachhang.setSodienthoai(khachHangDTO.getSoDienThoai());
        khachhang.setDiachi(khachHangDTO.getDiaChi());
        khachhang.setGioitinh(khachHangDTO.getGioiTinh());
        try{
            khachhang.setNgaysinh(new java.sql.Date(khachHangDTO.getNgaySinh().getTime()));
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        khachhang.setMakhachhang(khachHangDTO.getMaKhachHang());
        khachhang = khachHangService.save(khachhang);
        KhachHangDTO khachHangDTO1 = convertToDTO(khachhang);
        return new ResponseEntity<>(khachHangDTO1, HttpStatus.OK);
    }

}