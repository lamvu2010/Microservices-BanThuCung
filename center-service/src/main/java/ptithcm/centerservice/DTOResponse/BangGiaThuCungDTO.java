package ptithcm.centerservice.DTOResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BangGiaThuCungDTO {
    private long maBangGia;
    private Timestamp thoiGianBatDau;
    private Timestamp thoiGianKetThuc;
    private int maChiNhanh;
    private String tenChiNhanh;
    private long maThuCung;
    private String tenThuCung;
    private String moTa;
    private int maGiong;
    private String tenGiong;
    private BigDecimal giaHienTai;
    private BigDecimal giaKhuyenMai;
}