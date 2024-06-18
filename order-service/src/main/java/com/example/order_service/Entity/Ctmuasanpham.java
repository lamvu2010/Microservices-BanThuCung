package com.example.order_service.Entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "CTMUASANPHAM")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ctmuasanpham {
    @EmbeddedId
    private CtmuasanphamPK id;

    @Column(name = "SOLUONG", nullable = true)
    private Integer soluong;

    @Column(name = "DONGIA", nullable = true)
    private BigDecimal dongia;

    @ManyToOne
    @MapsId("sodondat")
    @JoinColumn(name = "SODONDAT")
    private Dondat dondat;

    @Column(name = "MASANPHAM",insertable = false,updatable = false)
    private long masanpham;

    @Column(name = "MACHINHANH",insertable = false, updatable = false)
    private int machinhanh;
}
