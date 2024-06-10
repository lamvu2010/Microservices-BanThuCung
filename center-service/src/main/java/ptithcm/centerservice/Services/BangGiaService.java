package ptithcm.centerservice.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ptithcm.centerservice.Entity.Banggia;
import ptithcm.centerservice.Repositories.BangGiaRepo;

import java.util.List;
import java.util.Optional;

@Service
public class BangGiaService {
    @Autowired
    BangGiaRepo bangGiaRepo;

    public Banggia save(Banggia banggia) {
        return bangGiaRepo.save(banggia);
    }

    public List<Banggia> findAll() {
        return bangGiaRepo.findAll();
    }

    public Optional<Banggia> findById(long id) {
        return bangGiaRepo.findById(id);
    }

    public void delete(Banggia banggia) {
        bangGiaRepo.delete(banggia);
    }

    public void deleteById(long id) {
        bangGiaRepo.deleteById(id);
    }

    public boolean isExistsById(long id) {
        return bangGiaRepo.existsById(id);
    }

    public void updateBangGia(int machinhanh, long mabanggia) {
        bangGiaRepo.updateTrangThaiToFalse(machinhanh, mabanggia);
    }
}
