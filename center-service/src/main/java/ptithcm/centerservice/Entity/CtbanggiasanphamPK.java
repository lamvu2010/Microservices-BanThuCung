package ptithcm.centerservice.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
@Embeddable
@Data
@AllArgsConstructor
public class CtbanggiasanphamPK implements Serializable {
    @Column(name = "MABANGGIA", nullable = false)
    private long mabanggia;
    @Column(name = "MASANPHAM", nullable = false)
    private long masanpham;
}
