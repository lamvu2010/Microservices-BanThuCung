package ptithcm.centerservice.DTORequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DonNhapSanPhamRequest {
    private long maDonNhap;
    private long maSanPham;
    private int soLuong;
    private BigDecimal donGia;
}
