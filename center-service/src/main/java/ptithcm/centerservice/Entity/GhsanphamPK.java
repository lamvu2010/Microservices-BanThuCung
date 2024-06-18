package ptithcm.centerservice.Entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GhsanphamPK implements Serializable {
    @Column(name = "MAKHACHHANG", nullable = false)
    private String makhachhang;

    @Column(name = "MASANPHAM", nullable = false)
    private long masanpham;

    @Column(name = "MACHINHANH", nullable = false)
    private int machinhanh;

}
