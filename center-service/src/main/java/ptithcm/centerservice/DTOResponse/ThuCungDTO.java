package ptithcm.centerservice.DTOResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ThuCungDTO {
    private long maThuCung;
    private String tenThuCung;
    private int trangThaiBan;
    private String chu;
    private String moTa;
    private BigDecimal giaHienTai;
    private BigDecimal giaKM;
    private ChiNhanhDTO chiNhanh;
    private GiongDTO giong;
    private List<Long> hinhAnh;
    private int soLuongTon;
}
