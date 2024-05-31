package ptithcm.centerservice.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@Table(name = "THUCUNG")
@NoArgsConstructor
@AllArgsConstructor
public class Thucung {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "MATHUCUNG", nullable = false)
    private long mathucung;

    @Column(name = "TENTHUCUNG", nullable = true, length = 50)
    private String tenthucung;

    @Column(name = "TRANGTHAIBAN", nullable = true)
    private Integer trangthaiban;

    @Column(name = "CHU", nullable = true, length = 50)
    private String chu;

    @Column(name = "MOTA", nullable = true, length = -1)
    private String mota;

    @Column(name = "GIAHIENTAI", nullable = true)
    private BigDecimal giahientai;

    @OneToMany(mappedBy = "thucung")
    List<Ctbanggiathucung> ctbanggiathucung;

    @ManyToOne
    @JoinColumn(name = "MACHINHANH")
    private Chinhanh chinhanh;

    @ManyToOne
    @JoinColumn(name = "MAGIONG")
    private Giong giong;

    @OneToMany(mappedBy = "thucung")
    List<Hinhanh> hinhanh;
}
