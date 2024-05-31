package ptithcm.centerservice.DTOResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BangGiaDTO {
    long maBangGia;
    Timestamp thoiGianBatDau;
    Timestamp thoiGianKetThuc;
    String noiDung;
    Boolean trangThai;
    ChiNhanhDTO chiNhanh;
}
