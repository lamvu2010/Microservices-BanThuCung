package com.example.identity_service.File;

import com.example.identity_service.Entity.Hinhanh;
import com.example.identity_service.Repository.HinhAnhRepo;
import com.example.identity_service.Repository.KhachHangRepo;
import com.example.identity_service.Repository.NhanVienRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;

@Service
public class StorageService {

    @Autowired
    private HinhAnhRepo hinhAnhRepo;
    @Autowired
    private NhanVienRepo nhanVienRepo;
    @Autowired
    private KhachHangRepo khachHangRepo;


    private final String FOLDER_PATH = "/path/in/container/";

    // lưu ảnh từ multipart về file system
    @Transactional
    public String uploadImageToFileSystem(MultipartFile file, String maNhanVien, String maKhachHang) throws IOException {
        String uuid = UUID.randomUUID().toString();
        String filePath = FOLDER_PATH + uuid + ".jpg";
        Hinhanh hinhanh = new Hinhanh();
        hinhanh.setTenhinhanh(file.getOriginalFilename());
        hinhanh.setLoaihinhanh(file.getContentType());
        hinhanh.setTenduynhat(uuid);
        hinhanh.setPath(filePath);
        if (maNhanVien != null) {
            hinhanh.setNhanvien(nhanVienRepo.findById(maNhanVien).orElse(null));
        }
        if (maKhachHang != null) {
            hinhanh.setKhachhang(khachHangRepo.findById(maKhachHang).orElse(null));
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