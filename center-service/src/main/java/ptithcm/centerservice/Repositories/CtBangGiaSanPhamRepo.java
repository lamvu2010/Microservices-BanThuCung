package ptithcm.centerservice.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ptithcm.centerservice.Entity.Ctbanggiasanpham;
import ptithcm.centerservice.Entity.CtbanggiasanphamPK;

import java.util.List;
import java.util.Map;

public interface CtBangGiaSanPhamRepo extends JpaRepository<Ctbanggiasanpham, CtbanggiasanphamPK> {
    @Query(value = "EXECUTE DANHSACHSANPHAMBAN",nativeQuery = true)
    List<Map<?,?>> danhSachSanPhamBan();

    @Query(value = "EXECUTE DANHSACHBANGGIASANPHAM",nativeQuery = true)
    List<Map<?,?>> danhSachSanPhamBanQuanLy();
}