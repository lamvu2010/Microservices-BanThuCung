package ptithcm.centerservice.DTORequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SoLuongSanPhamRequest {
    private long maSanPham;
    private int maChiNhanh;
    private long soLuongTon;
}
