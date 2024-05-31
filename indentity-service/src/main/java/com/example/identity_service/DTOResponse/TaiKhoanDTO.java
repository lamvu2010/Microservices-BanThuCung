package com.example.identity_service.DTOResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaiKhoanDTO {
    private String tenDangNhap;
    private String matKhau;
    private Boolean quyen;
    private Boolean trangThai;
    private String maXacNhan;
    private LocalDateTime thoiGianTaoMa;
    private LocalDateTime thoiGianHetHan;
    private LocalDateTime thoiGianXacNhan;
}
