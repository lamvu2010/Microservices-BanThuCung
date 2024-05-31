package ptithcm.centerservice.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ptithcm.centerservice.Entity.Chinhanh;
import ptithcm.centerservice.Repositories.ChiNhanhRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ChiNhanhService {
    @Autowired
    ChiNhanhRepo chiNhanhRepo;
    // Lay danh sach chi nhanh
    public List<Chinhanh> findAll(){
        List<Chinhanh> list = new ArrayList<>();
        list = chiNhanhRepo.findAll();
        return list;
    }
    // Lay thong tin chi nhanh dua vao id
    public Optional<Chinhanh> findById(int id){
        return chiNhanhRepo.findById(id);
    }
    // Them va cap nhat chi nhanh
    public Chinhanh save(Chinhanh chinhanh){
        return chiNhanhRepo.save(chinhanh);
    }
    //Xoa chi nhanh
    public void delete(Chinhanh chinhanh){
        chiNhanhRepo.delete(chinhanh);
    }
    public void deleteById(int id){
        chiNhanhRepo.deleteById(id);
    }

    // Kiem tra ton tai
    public boolean existsByTenChiNhanh(String tenChiNhanh){
        List<Chinhanh> listAll = chiNhanhRepo.findAll();
        for(Chinhanh item : listAll){
            if(item.getTenchinhanh().equals(tenChiNhanh)){
                return true;
            }
        }
        return false;
    }
    public boolean existById(int id){
        return chiNhanhRepo.existsById(id);
    }
}
