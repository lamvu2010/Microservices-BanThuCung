package ptithcm.centerservice.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ptithcm.centerservice.Entity.Ctsanpham;
import ptithcm.centerservice.Entity.CtsanphamPK;

public interface CtSanPhamRepo extends JpaRepository<Ctsanpham, CtsanphamPK> {
}
