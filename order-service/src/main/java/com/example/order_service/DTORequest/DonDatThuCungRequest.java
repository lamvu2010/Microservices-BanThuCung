package com.example.order_service.DTORequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DonDatThuCungRequest {
    private long maDonDat;
    private long maThuCung;
    private BigDecimal donGia;
    private int soLuong;
}
