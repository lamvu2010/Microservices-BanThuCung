package ptithcm.centerservice.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "CTSANPHAM")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Ctsanpham {

    @EmbeddedId
    private CtsanphamPK id;

    @Column(name = "SOLUONGTON", nullable = true)
    private Long soluongton;

    @Column(name = "DONGIA", nullable = true)
    private BigDecimal dongia;

    @ManyToOne
    @MapsId("masanpham")
    @JoinColumn(name = "MASANPHAM")
    private Sanpham sanpham;

    @ManyToOne
    @MapsId("machinhanh")
    @JoinColumn(name = "MACHINHANH")
    private Chinhanh chinhanh;

//    @OneToMany(mappedBy = "ctsanpham")
//    private List<Ctmuasanpham> ctmuasanpham;

}
