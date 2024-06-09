package com.example.order_service.DTOResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HoaDonDTO {
    private DonDatDTO donDat;
    private long maHoaDon;
    private Timestamp ngayLap;
    private BigDecimal tongHoaDon;
    private String maNhanVien;
}
