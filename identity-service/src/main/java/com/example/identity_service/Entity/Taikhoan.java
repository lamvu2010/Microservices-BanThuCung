package com.example.identity_service.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "TAIKHOAN")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Taikhoan {
    @Id
    @Column(name = "TENDANGNHAP", nullable = false, length = 50)
    private String tendangnhap;

    @Column(name = "MATKHAU", nullable = true, length = 50)
    private String matkhau;

    @Column(name = "QUYEN", nullable = true)
    private String quyen;

    @Column(name = "TRANGTHAI", nullable = true)
    private Boolean trangthai;

    @Column(name = "MAXACNHAN", nullable = true)
    private String maxacnhan;

    @Column(name = "THOIGIANXACNHAN", nullable = true)
    private LocalDateTime thoigianxacnhan;

    @Column(name = "THOIGIANTAOMA", nullable = true)
    private LocalDateTime thoigiantaoma;

    @Column(name = "THOIGIANHETHAN", nullable = true)
    private LocalDateTime thoigianhethan;
}
