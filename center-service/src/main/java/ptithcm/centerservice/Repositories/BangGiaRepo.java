package ptithcm.centerservice.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ptithcm.centerservice.Entity.Banggia;

public interface BangGiaRepo extends JpaRepository<Banggia,Long> {
    @Modifying
    @Transactional
    @Query("UPDATE Banggia b SET b.trangthai = false WHERE b.mabanggia!= :mabanggia and b.chinhanh.machinhanh = :machinhanh and b.trangthai = true")
    void updateTrangThaiToFalse(@Param("machinhanh")int machinhanh, @Param("mabanggia")long mabanggia);
}
