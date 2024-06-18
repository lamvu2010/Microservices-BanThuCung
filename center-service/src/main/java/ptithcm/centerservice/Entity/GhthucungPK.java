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
public class GhthucungPK implements Serializable {
    @Column(name = "MAKHACHHANG", nullable = false)
    private String makhachhang;

    @Column(name = "MATHUCUNG", nullable = false)
    private long mathucung;
}
