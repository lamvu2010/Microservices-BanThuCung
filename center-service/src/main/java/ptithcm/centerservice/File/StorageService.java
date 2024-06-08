package ptithcm.centerservice.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ptithcm.centerservice.Entity.Hinhanh;
import ptithcm.centerservice.Repositories.*;
//import ptithcm.centerservice.Services.KhachHangService;
//import ptithcm.centerservice.Services.NhanVienService;
import ptithcm.centerservice.Services.SanPhamService;
import ptithcm.centerservice.Services.ThuCungService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;
import java.util.UUID;

@Service
public class StorageService {

    @Autowired
    private HinhAnhRepo hinhAnhRepo;
//    @Autowired
////    private NhanVienRepo nhanVienRepo;
//    @Autowired
//    private KhachHangRepo khachHangRepo;
    @Autowired
    private ThuCungRepo thuCungRepo;
    @Autowired
    private SanPhamRepo sanPhamRepo;


    private final String FOLDER_PATH = "D:/Project/Microservices-BanThuCung/center-service/src//main/java/ptithcm/centerservice/HinhAnh/";

    // lưu ảnh từ multipart về file system
    @Transactional
    public String uploadImageToFileSystem(MultipartFile file, String maNhanVien, String maKhachHang, Long maThuCung, Long maSanPham) throws IOException {
        String uuid = UUID.randomUUID().toString();
        String filePath = FOLDER_PATH + uuid + ".jpg";
        Hinhanh hinhanh = new Hinhanh();
        hinhanh.setTenhinhanh(file.getOriginalFilename());
        hinhanh.setLoaihinhanh(file.getContentType());
        hinhanh.setTenduynhat(uuid);
        hinhanh.setPath(filePath);
        if(maThuCung!= null){
            hinhanh.setThucung(thuCungRepo.findById(maThuCung).orElse(null));
        }
        if(maSanPham!= null){
            hinhanh.setSanpham(sanPhamRepo.findById(maSanPham).orElse(null));
        }
        hinhanh = hinhAnhRepo.save(hinhanh);

        file.transferTo(new File(filePath));

        if (hinhanh != null) {
            return "File uploaded successfully : " + filePath;
        }
        return null;
    }

    // lấy ảnh từ file system để gửi đi
    @Transactional
    public byte[] downloadImageFromFileSystem(long id) throws IOException {
        Hinhanh hinhanh = new Hinhanh();
        Hinhanh fileData = hinhAnhRepo.findById(id).orElse(null);
        String filePath = fileData.getPath();
        byte[] images = Files.readAllBytes(new File(filePath).toPath());
        return images;
    }
}