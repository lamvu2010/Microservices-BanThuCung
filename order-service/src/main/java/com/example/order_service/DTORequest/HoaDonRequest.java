package com.example.order_service.DTORequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HoaDonRequest {
    private long soHoaDon;
    private Timestamp ngayLap;
    private String maNhanVien;
}
