package com.example.identity_service.Repository;

import com.example.identity_service.Entity.Khachhang;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KhachHangRepo extends JpaRepository<Khachhang,String> {
}
