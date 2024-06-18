package ptithcm.centerservice.Entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CtnhapsanphamPK implements Serializable {
    @Column(name = "MADONNHAP", nullable = false)
    private long madonnhap;
    @Column(name = "MASANPHAM", nullable = false)
    private long masanpham;
}
