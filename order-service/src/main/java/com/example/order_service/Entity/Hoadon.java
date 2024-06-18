package com.example.order_service.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "HOADON")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hoadon {
    @Id
    @Column(name = "SOHOADON", nullable = false)
    private long sohoadon;

    @Column(name = "NGAYLAP", nullable = true)
    private Timestamp ngaylap;

    @Column(name = "TONGHOADON", nullable = true)
    private BigDecimal tonghoadon;

    @Column(name = "MANHANVIEN",nullable = true)
    private String manhanvien;
}