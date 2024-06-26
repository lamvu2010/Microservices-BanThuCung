package com.example.order_service.Service;

import com.example.order_service.Entity.Hoadon;
import com.example.order_service.Repository.HoaDonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class HoaDonService {
    @Autowired
    HoaDonRepo hoaDonRepo;

    public Optional<Hoadon> findById(long id){
        return hoaDonRepo.findById(id);
    }

    public List<Hoadon> findAll(){
        return hoaDonRepo.findAll();
    }

    public Hoadon save(Hoadon hoadon){
        return hoaDonRepo.save(hoadon);
    }

    public void delete(Hoadon hoadon){
        hoaDonRepo.delete(hoadon);
    }
    public BigDecimal tongHoaDon(Long id){
        return hoaDonRepo.tongHoaDon(id);
    }

    public boolean isExistsById(Long id){
        return hoaDonRepo.existsById(id);
    }

}