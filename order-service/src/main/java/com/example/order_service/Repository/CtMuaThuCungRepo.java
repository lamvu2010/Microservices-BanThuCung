package com.example.order_service.Repository;

import com.example.order_service.Entity.Ctmuathucung;
import com.example.order_service.Entity.CtmuathucungPK;

import org.springframework.data.jpa.repository.JpaRepository;


public interface CtMuaThuCungRepo extends JpaRepository<Ctmuathucung,CtmuathucungPK> {
}
