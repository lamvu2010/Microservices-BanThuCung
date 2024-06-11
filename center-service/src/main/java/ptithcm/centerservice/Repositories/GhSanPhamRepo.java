package ptithcm.centerservice.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import ptithcm.centerservice.Entity.Ghsanpham;
import ptithcm.centerservice.Entity.GhsanphamPK;

import java.util.List;
import java.util.Map;

public interface GhSanPhamRepo extends JpaRepository<Ghsanpham, GhsanphamPK> {

    @Query(value = "{call GetSanPhamByKhachHangSanPhamChiNhanh(:MaKhachHang,:MaChiNhanh)}", nativeQuery = true)
    List<Map<?, ?>> sanPhamTrongGioHang(@Param("MaKhachHang") String maKhachHang, @Param("MaChiNhanh") int maChiNhanh);

    @Modifying
    @Transactional
    @Query("DELETE FROM Ghsanpham gh WHERE gh.id.makhachhang = :makhachhang and gh.chinhanh.machinhanh = :machinhanh")
    void xoaGioHangSanPham(@Param("makhachhang")String makhachhang,@Param("machinhanh") int machinhanh);
}
