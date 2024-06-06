package ptithcm.centerservice.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "GHTHUCUNG")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ghthucung {
    @EmbeddedId
    private GhthucungPK id;

//    @ManyToOne
//    @MapsId("makhachhang")
//    @JoinColumn(name = "MAKHACHHANG")
//    private String makhachhang;

    @ManyToOne
    @MapsId("mathucung")
    @JoinColumn(name = "MATHUCUNG")
    private Thucung thucung;
}
