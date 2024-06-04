package com.example.order_service.Service;

import com.example.order_service.Entity.Ctmuathucung;
import com.example.order_service.Repository.CtMuaThuCungRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CtMuaThuCungService {
    @Autowired
    private CtMuaThuCungRepo ctMuaThuCungRepo;

    public Ctmuathucung save(Ctmuathucung ctmuathucung){
        return ctMuaThuCungRepo.save(ctmuathucung);
    }
}
