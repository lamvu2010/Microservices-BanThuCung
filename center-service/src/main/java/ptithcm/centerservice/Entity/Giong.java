package ptithcm.centerservice.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "GIONG")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Giong {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "MAGIONG", nullable = false)
    private int magiong;

    @Column(name = "TENGIONG", nullable = true, length = 50)
    private String tengiong;

    @ManyToOne
    @JoinColumn(name = "MALOAITHUCUNG")
    private Loaithucung loaithucung;

    @OneToMany(mappedBy = "giong")
    private List<Thucung> thucung;
}
