package ptithcm.centerservice.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ptithcm.centerservice.Entity.Loaisanpham;
import ptithcm.centerservice.Entity.Loaithucung;
import ptithcm.centerservice.Repositories.LoaiSanPhamRepo;

import java.util.List;
import java.util.Optional;

@Service
public class LoaiSanPhamService {
    @Autowired
    LoaiSanPhamRepo loaiSanPhamRepo;

    // Lấy danh sách các bản ghi
    public List<Loaisanpham> findAll(){
        return loaiSanPhamRepo.findAll();
    }

    // Lấy sản phẩm bằng id
    public Optional<Loaisanpham> findById(int id){
        return loaiSanPhamRepo.findById(id);
    }

    // Lưu v cập nhật
    public Loaisanpham save(Loaisanpham loaisanpham){
        return loaiSanPhamRepo.save(loaisanpham);
    }

    // Xoá sản phẩm
    public void deleteById(int id){
        loaiSanPhamRepo.deleteById(id);
    }

    // Đếm số sản phẩm
    public long count(){
        return loaiSanPhamRepo.count();
    }

    // Kiểm tra tồn tại
    public boolean existsByTenLoai(String tenloai){
        List<Loaisanpham> listAll = loaiSanPhamRepo.findAll();
        for(Loaisanpham item : listAll){
            if(item.getTenloaisanpham().equals(tenloai)){
                return true;
            }
        }
        return false;
    }

    public boolean existsById(int id){
        if(loaiSanPhamRepo.existsById(id)){
            return true;
        }else{
            return false;
        }
    }
}
