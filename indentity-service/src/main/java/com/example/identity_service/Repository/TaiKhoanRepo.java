package com.example.identity_service.Repository;

import com.example.identity_service.Entity.Taikhoan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaiKhoanRepo extends JpaRepository<Taikhoan,String> {

    Taikhoan findBytendangnhap(String tendangnhap);
//    List<Taikhoan> findByquyen(Boolean quyen);

}
