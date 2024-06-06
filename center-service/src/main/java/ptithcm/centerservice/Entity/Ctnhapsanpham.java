package ptithcm.centerservice.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "CTNHAPSANPHAM")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ctnhapsanpham {

    @EmbeddedId
    private CtnhapsanphamPK id;

    @Column(name = "SOLUONG", nullable = true)
    private Integer soluong;

    @Column(name = "DONGIA", nullable = true)
    private BigDecimal dongia;

    @ManyToOne
    @MapsId("madonhap")
    @JoinColumn(name = "MADONNHAP")
    private Donnhaphang donnhaphang;

    @ManyToOne
    @MapsId("masanpham")
    @JoinColumn(name = "MASANPHAM")
    private Sanpham sanpham;
}
