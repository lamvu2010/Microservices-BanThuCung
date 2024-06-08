package ptithcm.centerservice.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ptithcm.centerservice.Entity.Ghthucung;
import ptithcm.centerservice.Entity.GhthucungPK;

import java.util.List;
import java.util.Map;

public interface GhThuCungRepo extends JpaRepository<Ghthucung, GhthucungPK> {

    @Query(value = "{call GetThucungByChinhanh(:maChiNhanh,:maKhachHang)}", nativeQuery = true)
    List<Map<?, ?>> sanPhamTrongGioHang(@Param("maChiNhanh") int maChiNhanh, @Param("maKhachHang") String maKhachhang);
}
