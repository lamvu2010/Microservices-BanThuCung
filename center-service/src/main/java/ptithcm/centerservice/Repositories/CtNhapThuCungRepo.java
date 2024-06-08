package ptithcm.centerservice.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ptithcm.centerservice.Entity.Ctnhapthucung;
import ptithcm.centerservice.Entity.CtnhapthucungPK;

public interface CtNhapThuCungRepo extends JpaRepository<Ctnhapthucung,CtnhapthucungPK> {
}
