package ptithcm.centerservice.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Hinhanh {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "MAHINHANH",nullable = false)
    private long mahinhanh;

    @Column(name = "PATH",nullable = true)
    private String path;

    @Column(name = "TENHINHANH",nullable = true)
    private String tenhinhanh;

    @Column(name = "TENDUYNHAT",nullable = true)
    private String tenduynhat;

    @Column(name = "LOAIHINHANH",nullable = true)
    private String loaihinhanh;

    @Column(name="TRANGTHAI",nullable = true)
    private Boolean trangthai;

    @ManyToOne
    @JoinColumn(name = "MATHUCUNG")
    private Thucung thucung;

    @ManyToOne
    @JoinColumn(name = "MASANPHAM")
    private Sanpham sanpham;

}
