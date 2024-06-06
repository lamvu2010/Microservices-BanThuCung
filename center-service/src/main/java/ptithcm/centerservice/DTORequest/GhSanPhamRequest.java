package ptithcm.centerservice.DTORequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GhSanPhamRequest {
    private String maKhachHang;
    private long maSanPham;
    private int maChiNhanh;
}
