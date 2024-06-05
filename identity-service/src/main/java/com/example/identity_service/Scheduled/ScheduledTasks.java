package com.example.identity_service.Scheduled;

import com.example.identity_service.Entity.Taikhoan;
import com.example.identity_service.Service.TaiKhoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ScheduledTasks {

    @Autowired
    TaiKhoanService taiKhoanService;
    @Scheduled(cron = "0 0 24 15 * * ?")
    public void deleteAccountUnused(){
        List<Taikhoan> list = new ArrayList<>();
        list = taiKhoanService.findAll();
        for(Taikhoan taikhoan : list){
            if(taikhoan.getTrangthai() == Boolean.FALSE){
                taiKhoanService.delete(taikhoan);
            }
        }
    }
}
