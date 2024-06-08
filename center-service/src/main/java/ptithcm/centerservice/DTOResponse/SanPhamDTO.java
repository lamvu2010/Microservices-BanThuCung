package ptithcm.centerservice.DTOResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SanPhamDTO {
    private long maSanPham;
    private String tenSanPham;
    private BigDecimal giaHienTai;
    private BigDecimal giaKM;
    private LoaiSanPhamDTO loaiSanPham;
    private List<Long> hinhAnh;
    private int maChiNhanh;
    private long soLuongTon;
}
