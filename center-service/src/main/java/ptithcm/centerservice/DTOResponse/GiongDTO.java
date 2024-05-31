package ptithcm.centerservice.DTOResponse;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GiongDTO {
    int maGiong;
    String tengiong;
    LoaiThuCungDTO loaiThuCung;
}
