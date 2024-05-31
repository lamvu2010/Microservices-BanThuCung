package com.example.identity_service.Repository;

import com.example.identity_service.Entity.Nhanvien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NhanVienRepo extends JpaRepository<Nhanvien,String> {
    @Query(value = "SELECT dbo.MANHANVIENMOI()",nativeQuery = true)
    String maNhanVienMoi();
}
