package ptithcm.centerservice.DTOResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BangGiaSanPhamDTO {
    private long maSanPham;
    private String tenSanPham;
    private int maLoaiSanPham;
    private String tenLoaiSanPham;
    private long maBangGia;
    private Timestamp thoiGianBatDau;
    private Timestamp thoiGianKetThuc;
    private int maChiNhanh;
    private String tenChiNhanh;
    private BigDecimal giaHienTai;
    private BigDecimal giaKhuyenMai;
    private long soLuongTon;
    private byte[] hinhAnh;
}
