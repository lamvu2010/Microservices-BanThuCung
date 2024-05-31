package com.example.identity_service.Repository;

import com.example.identity_service.Entity.Hinhanh;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HinhAnhRepo extends JpaRepository<Hinhanh,Long> {
    Hinhanh findByTenduynhat(String tenduynhat);
}
