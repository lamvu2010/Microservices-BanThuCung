package ptithcm.centerservice.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CtsanphamPK implements Serializable {

    @Column(name = "MACHINHANH", nullable = false)
    private int machinhanh;
    @Column(name = "MASANPHAM", nullable = false)
    private long masanpham;
}
