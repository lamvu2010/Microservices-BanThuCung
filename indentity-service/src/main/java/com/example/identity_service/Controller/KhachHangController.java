package com.example.identity_service.Controller;

import com.example.identity_service.DTOResponse.KhachHangDTO;
import com.example.identity_service.Entity.Khachhang;
import com.example.identity_service.Service.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/identity/khachhang")
public class KhachHangController {

    @Autowired
    private KhachHangService khachHangService;

    public KhachHangDTO convertToDTO(Khachhang khachhang){
        KhachHangDTO khachHangDTO = new KhachHangDTO();
        khachHangDTO.setHo(khachhang.getHo());
        khachHangDTO.setTen(khachhang.getTen());
        khachHangDTO.setNgaySinh(khachhang.getNgaysinh());
        khachHangDTO.setGioiTinh(khachhang.getGioitinh());
        khachHangDTO.setEmail(khachhang.getEmail());
        khachHangDTO.setSoDienThoai(khachhang.getSodienthoai());
        khachHangDTO.setCccd(khachhang.getCccd());
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
}