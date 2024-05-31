package com.example.identity_service.Service;

import com.example.identity_service.Repository.TaiKhoanRepo;
import com.example.identity_service.Entity.Taikhoan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaiKhoanService {
    @Autowired
    private TaiKhoanRepo taiKhoanRepo;

    public List<Taikhoan> findAll(){
        return taiKhoanRepo.findAll();
    }

    public Optional<Taikhoan> findById(String id){
        return taiKhoanRepo.findById(id);
    }

    public Taikhoan save(Taikhoan taikhoan){
        return taiKhoanRepo.save(taikhoan);
    }

    public void delete(Taikhoan taikhoan){
        taiKhoanRepo.delete(taikhoan);
    }

    public void deleteById(String id){
        taiKhoanRepo.deleteById(id);
    }

    public boolean isExistsById(String id){
        return taiKhoanRepo.existsById(id);
    }

    public Taikhoan findBytendangnhap(String tendangnhap){
        return taiKhoanRepo.findBytendangnhap(tendangnhap);
    }
}
