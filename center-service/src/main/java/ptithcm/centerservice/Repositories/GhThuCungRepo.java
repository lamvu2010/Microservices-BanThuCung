package ptithcm.centerservice.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ptithcm.centerservice.Entity.Ghthucung;
import ptithcm.centerservice.Entity.GhthucungPK;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;

public interface GhThuCungRepo extends JpaRepository<Ghthucung, GhthucungPK> {

    @Query(value = "{call GetThucungByChinhanh(:maChiNhanh,:maKhachHang)}", nativeQuery = true)
    List<Map<?, ?>> sanPhamTrongGioHang(@Param("maChiNhanh") int maChiNhanh, @Param("maKhachHang") String maKhachhang);

    @Modifying
    @Transactional
    @Query("DELETE FROM Ghthucung gh WHERE gh.id.makhachhang = :makhachhang and gh.thucung.chinhanh.machinhanh = :machinhanh")
    void xoaGioHangThuCung(@Param("makhachhang")String makhachhang, @Param("machinhanh")int machinhanh);
}
