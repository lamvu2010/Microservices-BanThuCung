package ptithcm.centerservice.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ptithcm.centerservice.Entity.Loaisanpham;
import ptithcm.centerservice.Entity.Sanpham;
import ptithcm.centerservice.Repositories.SanPhamRepo;

import java.util.List;
import java.util.Optional;

@Service
public class SanPhamService {
    @Autowired
    SanPhamRepo sanPhamRepo;

    // Lấy danh sách tất cả sản phẩm
    public List<Sanpham> findAll(){
        List<Sanpham> list = sanPhamRepo.findAll();
        return list;
    }
    //Lay san pham bang id
    public Optional<Sanpham> findById(long id){
        Optional<Sanpham> sanpham = sanPhamRepo.findById(id);
        return sanpham;
    }

    //Lay danh sach cac san pham cung loai san pham
    public List<Sanpham> findByIdLoai(Loaisanpham loaisanpham){
        List<Sanpham> list = sanPhamRepo.findByloaisanpham(loaisanpham);
        return list;
    }
    // Them san pham va cap nhat san pham
    public Sanpham save(Sanpham sanpham){
        return sanPhamRepo.save(sanpham);
    }
    //Xoa san pham
    public void delete(Sanpham sanpham){
        sanPhamRepo.delete(sanpham);
    }
    // Kiem tra ton tai
    public boolean existsById(long id){
        if (sanPhamRepo.existsById(id)){
            return true;
        }
        else{
            return false;
        }
    }
}
