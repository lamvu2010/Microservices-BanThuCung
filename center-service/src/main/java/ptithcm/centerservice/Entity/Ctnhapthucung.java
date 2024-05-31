package ptithcm.centerservice.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "CTNHAPTHUCUNG")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ctnhapthucung {
    @EmbeddedId
    private CtnhapthucungPK id;

    @Column(name = "GIANHAP", nullable = true)
    private BigDecimal gianhap;

    @ManyToOne
    @MapsId
    @JoinColumn(name = "MADONNHAP")
    private Donnhaphang donnhaphang;

    @ManyToOne
    @MapsId
    @JoinColumn(name = "MATHUCUNG")
    private Thucung thucung;
}
