package ptithcm.centerservice.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "CTBANGGIATHUCUNG")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ctbanggiathucung {
    @EmbeddedId
    private CtbanggiathucungPK id;

    @Column(name = "DONGIA", nullable = true)
    private BigDecimal dongia;

    @ManyToOne
    @MapsId("mabanggia")
    @JoinColumn(name = "MABANGGIA")
    Banggia banggia;

    @ManyToOne
    @MapsId("mathucung")
    @JoinColumn(name = "MATHUCUNG")
    Thucung thucung;
}
