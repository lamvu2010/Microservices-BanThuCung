package ptithcm.centerservice.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ptithcm.centerservice.Entity.Ctsanpham;
import ptithcm.centerservice.Entity.CtsanphamPK;
import ptithcm.centerservice.Repositories.CtSanPhamRepo;

import java.util.List;
import java.util.Optional;

@Service
public class ChiTietSanPhamService {

    @Autowired
    private CtSanPhamRepo ctSanPhamRepo;

    public List<Ctsanpham> findAll(){
        return ctSanPhamRepo.findAll();
    }

    public Optional<Ctsanpham> findById(CtsanphamPK id){
        return ctSanPhamRepo.findById(id);
    }

    public Ctsanpham save(Ctsanpham ctsanpham){
        return ctSanPhamRepo.save(ctsanpham);
    }

    public void delete(Ctsanpham ctsanpham){
        ctSanPhamRepo.delete(ctsanpham);
    }

    public void deleteById(CtsanphamPK ctsanphamPK){
        ctSanPhamRepo.deleteById(ctsanphamPK);
    }

    public boolean existsById(CtsanphamPK ctsanphamPK){
        return ctSanPhamRepo.existsById(ctsanphamPK);
    }
}
