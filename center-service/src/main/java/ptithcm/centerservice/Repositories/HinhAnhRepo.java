package ptithcm.centerservice.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ptithcm.centerservice.Entity.Hinhanh;

public interface HinhAnhRepo extends JpaRepository<Hinhanh,Long> {
    Hinhanh findByTenduynhat(String tenduynhat);
}
