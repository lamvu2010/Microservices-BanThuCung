package com.example.identity_service.Controller;

import com.example.identity_service.DTOResponse.HinhAnhDTO;
import com.example.identity_service.File.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/identity/hinhanh")
public class HinhAnhByteController {
    @Autowired
    private StorageService storageService;

    @PostMapping
    public ResponseEntity<?> saveImage(@RequestParam("image") MultipartFile[] file,
                                       @RequestParam(value = "maNhanVien",required = false) String maNhanVien,
                                       @RequestParam(value = "maKhachHang",required = false) String maKhachHang
    ) throws IOException {
        try {
            for (MultipartFile item : file) {
                storageService.uploadImageToFileSystem(item,maNhanVien,maKhachHang);
            }
            return new ResponseEntity<>("Lưu thành công", HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Lưu thất bại", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/get")
    public ResponseEntity<?> sendImage(@RequestBody long[] id) throws IOException {
        try{
            List<HinhAnhDTO> hinhAnhDTOs = new ArrayList<>();
            for (long itemId : id) {
                byte[] image = storageService.downloadImageFromFileSystem(itemId);
                HinhAnhDTO hinhAnhDTO = new HinhAnhDTO();
                hinhAnhDTO.setMaHinhAnh(itemId);
                hinhAnhDTO.setSource(image);
                hinhAnhDTOs.add(hinhAnhDTO);
            }
            return new ResponseEntity<>(hinhAnhDTOs, HttpStatus.OK);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Lỗi lấy hình", HttpStatus.BAD_REQUEST);
        }

    }
}