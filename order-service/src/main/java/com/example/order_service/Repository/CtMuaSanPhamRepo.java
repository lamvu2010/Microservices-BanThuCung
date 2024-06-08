package com.example.order_service.Repository;

import com.example.order_service.Entity.Ctmuasanpham;
import com.example.order_service.Entity.CtmuasanphamPK;

import org.springframework.data.jpa.repository.JpaRepository;


public interface CtMuaSanPhamRepo extends JpaRepository<Ctmuasanpham,CtmuasanphamPK> {
}
