package com.example.identity_service.DTOResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private String tenDangNhap;
    private String quyen;
//    private String ho;
//    private String ten;
//    private String gioiTinh;
//    private Date ngaySinh;
//    private String soDienThoai;
//    private String email;
//    private String cccd;
    private String token;
}
