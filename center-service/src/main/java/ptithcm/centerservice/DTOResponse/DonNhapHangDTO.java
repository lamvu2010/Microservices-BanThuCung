package ptithcm.centerservice.DTOResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DonNhapHangDTO {
    private long maDonNhapHang;
    private Timestamp ngayLap;
    private String maNhanVien;
    private ChiNhanhDTO chiNhanhDTO;
}
