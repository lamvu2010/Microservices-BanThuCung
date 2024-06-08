package com.example.identity_service.Service;

import com.example.identity_service.Repository.KhachHangRepo;
import com.example.identity_service.Entity.Khachhang;
import com.example.identity_service.Entity.Nhanvien;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KhachHangService {

    @Autowired
    private KhachHangRepo khachHangRepo;
    public Khachhang save(Khachhang khachhang){
        return khachHangRepo.save(khachhang);
    }
    public List<Khachhang> findAll(){
        return khachHangRepo.findAll();
    }
    public Optional<Khachhang> findById(String id){
        return khachHangRepo.findById(id);
    }

    public boolean isExistsById(String id){
        return khachHangRepo.existsById(id);
    }
}
