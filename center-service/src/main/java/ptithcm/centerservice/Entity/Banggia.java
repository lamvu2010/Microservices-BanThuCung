package ptithcm.centerservice.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "BANGGIA")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Banggia {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "MABANGGIA", nullable = false)
    private long mabanggia;
    @Column(name = "THOIGIANBATDAU", nullable = true)
    private Timestamp thoigianbatdau;
    @Column(name = "THOIGIANKETTHUC", nullable = true)
    private Timestamp thoigianketthuc;
    @Column(name = "NOIDUNG", nullable = true, length = -1)
    private String noidung;
    @Column(name = "TRANGTHAI",nullable = true)
    private Boolean trangthai;
    @ManyToOne
    @JoinColumn(name = "MACHINHANH")
    private Chinhanh chinhanh;

    @OneToMany(mappedBy = "banggia")
    List<Ctbanggiasanpham> ctbanggiasanpham;

    @OneToMany(mappedBy = "banggia")
    List<Ctbanggiathucung> ctbanggiathucung;

}
