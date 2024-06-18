package ptithcm.centerservice.Entity;

import javax.persistence.*;
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

    @Column(name = "SOLUONG",nullable = true)
    private int soluong;

    @ManyToOne
    @MapsId("madonnhap")
    @JoinColumn(name = "MADONNHAP")
    private Donnhaphang donnhaphang;

    @ManyToOne
    @MapsId("mathucung")
    @JoinColumn(name = "MATHUCUNG")
    private Thucung thucung;
}
