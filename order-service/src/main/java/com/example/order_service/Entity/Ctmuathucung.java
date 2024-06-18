package com.example.order_service.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "CTMUATHUCUNG")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ctmuathucung {
    @EmbeddedId
    private CtmuathucungPK id;

    @Column(name = "DONGIA", nullable = false)
    private BigDecimal dongia;

    @Column(name = "SOLUONG", nullable = false)
    private int soluong;

    @ManyToOne
    @MapsId("madondat")
    @JoinColumn(name = "MADONDAT")
    private Dondat dondat;
}
