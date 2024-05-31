package ptithcm.centerservice.DTORequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String ho;
    private String ten;
    private String tenDangNhap;
    private String matKhau;
    private String cccd;
    private String soDienThoai;
    private String email;
}
