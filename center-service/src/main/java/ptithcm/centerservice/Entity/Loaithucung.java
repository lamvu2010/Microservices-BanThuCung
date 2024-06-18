package ptithcm.centerservice.Entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "LOAITHUCUNG")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Loaithucung {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "MALOAITHUCUNG", nullable = false)
    private int maloaithucung;

    @Column(name = "TENLOAITHUCUNG", nullable = true, length = 50)
    private String tenloaithucung;

    @OneToMany(mappedBy = "loaithucung")
    List<Giong> giong;
}
