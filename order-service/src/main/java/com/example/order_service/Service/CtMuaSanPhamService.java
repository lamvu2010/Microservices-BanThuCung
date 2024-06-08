package com.example.order_service.Service;

import com.example.order_service.Entity.Ctmuasanpham;
import com.example.order_service.Repository.CtMuaSanPhamRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CtMuaSanPhamService {
    @Autowired
    private CtMuaSanPhamRepo ctMuaSanPhamRepo;

    public Ctmuasanpham save(Ctmuasanpham ctmuasanpham){
        return ctMuaSanPhamRepo.save(ctmuasanpham);
    }
}
