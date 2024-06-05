package ptithcm.centerservice.DTORequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DonNhapThuCungRequest {
    private long maDonNhap;
    private long maThuCung;
    private BigDecimal giaNhap;
    private int soLuong;
}
