package com.example.order_service.DTOResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DonDatDTO {
    private long soDonDat;
    private Date ngayLap;
    private String diaChi;
    private String soDienThoai;
    private Integer maChiNhanh;
    private String maKhachhang;
}
