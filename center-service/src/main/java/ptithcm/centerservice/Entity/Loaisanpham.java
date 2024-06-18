package ptithcm.centerservice.Entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "LOAISANPHAM")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Loaisanpham {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "MALOAISANPHAM", nullable = false)
    private int maloaisanpham;

    @Column(name = "TENLOAISANPHAM", nullable = true, length = 50)
    private String tenloaisanpham;

    @OneToMany(mappedBy = "loaisanpham")
    private List<Sanpham> sanpham;
}
