package com.example.identity_service.Service;

import com.example.identity_service.Repository.KhachHangRepo;
import com.example.identity_service.Entity.Khachhang;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
