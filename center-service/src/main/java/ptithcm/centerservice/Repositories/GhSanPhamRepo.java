package ptithcm.centerservice.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ptithcm.centerservice.Entity.Ghsanpham;
import ptithcm.centerservice.Entity.GhsanphamPK;

import java.util.List;
import java.util.Map;

public interface GhSanPhamRepo extends JpaRepository<Ghsanpham, GhsanphamPK> {

    @Query(value = "{call GetSanPhamByKhachHangSanPhamChiNhanh(:MaKhachHang,:MaChiNhanh)}", nativeQuery = true)
    List<Map<?, ?>> sanPhamTrongGioHang(@Param("MaKhachHang") String maKhachHang, @Param("MaChiNhanh") int maChiNhanh);
}
