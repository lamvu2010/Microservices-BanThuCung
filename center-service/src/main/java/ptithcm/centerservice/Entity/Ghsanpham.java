package ptithcm.centerservice.Entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "GHSANPHAM")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ghsanpham {
    @EmbeddedId
    private GhsanphamPK id;

    @ManyToOne
    @MapsId("masanpham")
    @JoinColumn(name = "MASANPHAM", insertable = false, updatable = false)
    private Sanpham sanpham;

    @ManyToOne
    @MapsId("machinhanh")
    @JoinColumn(name = "MACHINHANH", insertable = false, updatable = false)
    private Chinhanh chinhanh;
}
