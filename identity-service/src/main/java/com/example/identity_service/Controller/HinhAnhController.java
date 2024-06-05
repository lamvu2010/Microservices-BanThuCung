package com.example.identity_service.Controller;

import com.example.identity_service.File.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/identity/image")
public class HinhAnhController {
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
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        for (long itemId : id) {
            byte[] image = storageService.downloadImageFromFileSystem(itemId);
            ByteArrayResource imageResource = new ByteArrayResource(image) {
                @Override
                public String getFilename() {
                    return "image-" + itemId; // Cung cấp một tên duy nhất cho mỗi file
                }
            };
            body.add("images", imageResource);
        }
        return new ResponseEntity<>(body, headers, HttpStatus.OK);
    }
}