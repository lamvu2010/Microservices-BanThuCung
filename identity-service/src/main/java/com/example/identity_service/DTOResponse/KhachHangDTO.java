package com.example.identity_service.DTOResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KhachHangDTO {
    private String maKhachHang;
    private String ho;
    private String ten;
    private Boolean gioiTinh;
    private Date ngaySinh;
    private String soDienThoai;
    private String email;
    private String cccd;
    private String diaChi;
    private List<Long> hinhAnh;
}
