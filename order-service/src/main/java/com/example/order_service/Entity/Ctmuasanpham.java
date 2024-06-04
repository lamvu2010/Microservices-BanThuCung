package com.example.order_service.Entity;

import jakarta.persistence.*;
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

}
