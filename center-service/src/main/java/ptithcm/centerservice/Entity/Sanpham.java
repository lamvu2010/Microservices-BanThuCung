package ptithcm.centerservice.Entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "SANPHAM")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sanpham {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "MASANPHAM", nullable = false)
    private long masanpham;

    @Column(name = "TENSANPHAM", nullable = true, length = 50)
    private String tensanpham;

    @Column(name = "GIAHIENTAI", nullable = true)
    private BigDecimal giahientai;


    @ManyToOne
    @JoinColumn(name = "MALOAISANPHAM")
    private Loaisanpham loaisanpham;

    @OneToMany(mappedBy = "sanpham")
    private List<Ctbanggiasanpham> ctbanggiasanpham;

    @OneToMany(mappedBy = "sanpham")
    private List<Ctnhapsanpham> ctnhapsanpham;

    @OneToMany(mappedBy = "sanpham")
    private List<Ctsanpham> ctsanpham;

    @OneToMany(mappedBy = "sanpham")
    private List<Hinhanh> hinhanh;

}
