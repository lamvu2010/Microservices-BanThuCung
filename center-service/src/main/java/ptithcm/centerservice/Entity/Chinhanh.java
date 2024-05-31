package ptithcm.centerservice.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CHINHANH")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chinhanh {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "MACHINHANH", nullable = false)
    private int machinhanh;
    @Column(name = "TENCHINHANH", nullable = true, length = 50)
    private String tenchinhanh;

}
