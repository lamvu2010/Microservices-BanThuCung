package ptithcm.centerservice.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ptithcm.centerservice.Entity.Loaithucung;
import ptithcm.centerservice.Repositories.LoaiThuCungRepo;

import java.util.List;
import java.util.Optional;

@Service
public class LoaiThuCungService {
    @Autowired
    LoaiThuCungRepo loaiThuCungRepo;

    // Lấy danh sách tất cả bản ghi
    public List<Loaithucung> findAll(){
        return loaiThuCungRepo.findAll();
    }

    // Lưu và cập nhật bản ghi
    public Loaithucung save(Loaithucung loaithucung){
        return loaiThuCungRepo.save(loaithucung);
    }

    // Tìm bằng id
    public Optional<Loaithucung> findById(int id){
        return loaiThuCungRepo.findById(id);
    }

    // Xoá bản ghi theo ID
    public void deleteById(int id){
        loaiThuCungRepo.deleteById(id);
    }

    // Đếm số bản ghi
    public long count(){
        return loaiThuCungRepo.count();
    }

    // Tìm bằng tên

    public boolean existsByTenLoai(String tenloai){
        List<Loaithucung> listAll = loaiThuCungRepo.findAll();
        for(Loaithucung item : listAll){
            if(item.getTenloaithucung().equals(tenloai)){
                return true;
            }
        }
        return false;
    }

    public boolean existsById(int id){
        if(loaiThuCungRepo.existsById(id)){
            return true;
        }else{
            return false;
        }
    }
}
