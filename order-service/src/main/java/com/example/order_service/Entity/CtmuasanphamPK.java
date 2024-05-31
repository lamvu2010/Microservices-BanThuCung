package com.example.order_service.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CtmuasanphamPK implements Serializable {
    @Column(name = "SODONDAT", nullable = false)
    private long sodondat;
    @Column(name = "MASANPHAM", nullable = false)
    private long masanpham;
    @Column(name = "MACHINHANH",nullable = false)
    private long machinhanh;
}
