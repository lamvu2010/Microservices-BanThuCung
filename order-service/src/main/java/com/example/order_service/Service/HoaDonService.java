package com.example.order_service.Service;

import com.example.order_service.Entity.Hoadon;
import com.example.order_service.Repository.HoaDonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HoaDonService {
    @Autowired
    HoaDonRepo hoaDonRepo;

    public Hoadon save(Hoadon hoadon){
        return hoaDonRepo.save(hoadon);
    }

    public void delete(Hoadon hoadon){
        hoaDonRepo.delete(hoadon);
    }

}