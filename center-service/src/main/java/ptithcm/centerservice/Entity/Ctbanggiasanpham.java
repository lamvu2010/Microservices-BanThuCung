package ptithcm.centerservice.Entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "CTBANGGIASANPHAM")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ctbanggiasanpham {
    @EmbeddedId
    private CtbanggiasanphamPK id;

    @ManyToOne
    @MapsId("mabanggia")
    @JoinColumn(name = "MABANGGIA")
    private Banggia banggia;

    @ManyToOne
    @MapsId("masanpham")
    @JoinColumn(name="MASANPHAM")
    private Sanpham sanpham;

    @Column(name = "DONGIA", nullable = true)
    private BigDecimal dongia;
}
