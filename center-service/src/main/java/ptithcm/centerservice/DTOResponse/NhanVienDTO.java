package ptithcm.centerservice.DTOResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NhanVienDTO {
    private String maNhanVien;
    private String ho;
    private String ten;
    private String cccd;
    private String chucVu;
    private String soDienThoai;
    private String email;
    private ChiNhanhDTO chiNhanh;
    private List<Long> hinhAnh;
}
