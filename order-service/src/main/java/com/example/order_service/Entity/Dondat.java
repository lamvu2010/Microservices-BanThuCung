package com.example.order_service.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "DONDAT")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dondat {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "SODONDAT", nullable = false)
    private long sodondat;

    @Column(name = "NGAYLAP", nullable = true)
    private Date ngaylap;

    @Column(name = "DIACHIDAT", nullable = true, length = 50)
    private String diachidat;

    @Column(name = "SODIENTHOAI", nullable = true, length = 15)
    private String sodienthoai;

    @OneToOne
    @MapsId
    private Hoadon hoadon;

    @OneToMany(mappedBy = "dondat")
    private List<Ctmuasanpham> ctmuasanpham;

    @OneToMany(mappedBy = "dondat")
    private List<Ctmuathucung> ctmuathucung;
}
