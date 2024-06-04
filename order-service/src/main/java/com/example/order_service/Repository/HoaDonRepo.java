package com.example.order_service.Repository;

import com.example.order_service.Entity.Hoadon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface HoaDonRepo extends JpaRepository<Hoadon,Long> {

    @Query(value = "SELECT dbo.THANHTIEN(:maDonDat)",nativeQuery = true)
    BigDecimal tongHoaDon(@Param("maDonDat") Long maDonDat);

}
