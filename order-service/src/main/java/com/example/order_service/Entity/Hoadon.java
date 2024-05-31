package com.example.order_service.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "HOADON")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hoadon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SOHOADON", nullable = false)
    private Long sohoadon;

    @Column(name = "NGAYLAP", nullable = true)
    private Date ngaylap;

    @Column(name = "TONGHOADON", nullable = true)
    private BigDecimal tonghoadon;

    @OneToOne(mappedBy = "hoadon")
    private Dondat dondat;
}