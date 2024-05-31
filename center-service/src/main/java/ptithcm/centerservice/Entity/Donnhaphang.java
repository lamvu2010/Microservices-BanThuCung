package ptithcm.centerservice.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "DONNHAPHANG")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Donnhaphang {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "MADONNHAPHANG", nullable = false)
    private long madonnhaphang;

    @Column(name = "NGAYLAP", nullable = true)
    private Date ngaylap;

//    @ManyToOne
//    @JoinColumn(name = "MANHANVIEN")
//    private Nhanvien nhanvien;
    @Column(name = "MANHANVIEN")
    private String manhanvien;

    @ManyToOne
    @JoinColumn(name = "MACHINHANH")
    private Chinhanh chinhanh;

    @OneToMany(mappedBy = "donnhaphang")
    private List<Ctnhapthucung> ctnhapthucung;

    @OneToMany(mappedBy = "donnhaphang")
    private List<Ctnhapsanpham> ctnhapsanpham;
}
