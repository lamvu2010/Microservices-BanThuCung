package ptithcm.centerservice.Entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CtbanggiasanphamPK implements Serializable {
    @Column(name = "MABANGGIA", nullable = false)
    private long mabanggia;
    @Column(name = "MASANPHAM", nullable = false)
    private long masanpham;
}
