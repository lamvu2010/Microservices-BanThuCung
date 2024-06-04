package com.example.order_service.DTORequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SoLuongThuCungRequest {
    private long maThuCung;
    private int soLuongTon;
}
