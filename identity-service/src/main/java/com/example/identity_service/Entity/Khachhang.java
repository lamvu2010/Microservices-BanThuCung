package com.example.identity_service.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "KHACHHANG")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Khachhang {
    @Id
    @Column(name = "MAKHACHHANG", nullable = false, length = 50)
    private String makhachhang;

    @Column(name = "HO", nullable = true, length = 10)
    private String ho;

    @Column(name = "TEN", nullable = true, length = 10)
    private String ten;

    @Column(name = "GIOITINH", nullable = true)
    private Boolean gioitinh;

    @Column(name = "NGAYSINH", nullable = true)
    private Date ngaysinh;

    @Column(name = "DIACHI", nullable = true, length = 50)
    private String diachi;

    @Column(name = "SODIENTHOAI", nullable = true, length = 15)
    private String sodienthoai;

    @Column(name = "EMAIL", nullable = true, length = 50)
    private String email;

    @Column(name = "CCCD", nullable = true, length = 20)
    private String cccd;

    @OneToMany(mappedBy = "khachhang")
    private List<Hinhanh> hinhanh;
}
