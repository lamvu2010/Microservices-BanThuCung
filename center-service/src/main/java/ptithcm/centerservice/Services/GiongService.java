package ptithcm.centerservice.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ptithcm.centerservice.Entity.Giong;
import ptithcm.centerservice.Repositories.GiongRepo;

import java.util.List;
import java.util.Optional;

@Service
public class GiongService {
    @Autowired
    GiongRepo giongRepo;

    //Lay danh sach giong
    public List<Giong> findAll(){
        return giongRepo.findAll();
    }

    // Lay giong bang id
    public Optional<Giong> findById(int id){
        return giongRepo.findById(id);
    }

    // Them, cap nhat giong
    public Giong save(Giong giong){
        return giongRepo.save(giong);
    }

    // Xoa giong
    public void delete(Giong giong){
        giongRepo.delete(giong);
    }

    // kiem tra giong co ton tai
    public boolean isExistsById(int id){
        return giongRepo.existsById(id);
    }
}
