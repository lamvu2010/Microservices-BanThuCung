package ptithcm.centerservice.DTORequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BangGiaSanPhamRequest {
    private long maBangGia;
    private long maSanPham;
    private BigDecimal donGia;
}
