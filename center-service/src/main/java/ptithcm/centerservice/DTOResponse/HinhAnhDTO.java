package ptithcm.centerservice.DTOResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HinhAnhDTO {
    private long maHinhAnh;
    private String path;
    private String tenHinhAnh;
    private String tenDuyNhat;
    private String loaiHinhAnh;
    private byte[] source;
}
