package ptithcm.centerservice.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CtnhapthucungPK implements Serializable {
    @Column(name = "MADONNHAP", nullable = false)
    private long madonnhap;
    @Column(name = "MATHUCUNG", nullable = false)
    private long mathucung;
}
