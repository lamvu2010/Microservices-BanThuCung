package ptithcm.centerservice.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ptithcm.centerservice.Entity.Loaisanpham;
import ptithcm.centerservice.Entity.Sanpham;

import java.util.List;

public interface SanPhamRepo extends JpaRepository<Sanpham,Long> {
    List<Sanpham> findByloaisanpham(Loaisanpham loaisanpham);
}
