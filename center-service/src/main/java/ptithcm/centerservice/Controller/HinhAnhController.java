package ptithcm.centerservice.Controller;

import jdk.jfr.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import ptithcm.centerservice.File.StorageService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/center/image")
public class HinhAnhController {
    @Autowired
    private StorageService storageService;

    @PostMapping
    public ResponseEntity<?> saveImage(@RequestParam("image") MultipartFile[] file,
                                       @RequestParam(value = "maNhanVien",required = false) String maNhanVien,
                                       @RequestParam(value = "maKhachHang",required = false) String maKhachHang,
                                       @RequestParam(value = "maThuCung",required = false) Long maThuCung,
                                       @RequestParam(value = "maSanPham",required = false) Long maSanPham
    ) throws IOException {
        try {
            for (MultipartFile item : file) {
                storageService.uploadImageToFileSystem(item,maNhanVien,maKhachHang,maThuCung,maSanPham);
            }
            return new ResponseEntity<>("Lưu thành công", HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Lưu thất bại", HttpStatus.BAD_REQUEST);
        }
    }

    //    @PostMapping("/xem")
//    public ResponseEntity<?> sendImage(@RequestBody long[] id) throws IOException {
//        List<byte[]> images = new ArrayList<>();
//        for(long item : id) {
//            byte[] image = storageService.downloadImageFromFileSystem(item);
//            images.add(image);
//        }
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
//        return new ResponseEntity<>(images,headers,HttpStatus.OK);
//    }
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
//    @GetMapping(value = "/xem", produces = MediaType.IMAGE_JPEG_VALUE)
//    public ResponseEntity<List<byte[]>> getImages(@RequestParam("image") List<Long> ids) throws IOException {
//        List<byte[]> images = new ArrayList<>();
//        for (Long id : ids) {
//            byte[] image = storageService.downloadImageFromFileSystem(id);
//            if (image != null) {
//                images.add(image);
//            }
//        }
//        HttpHeaders headers = new HttpHeaders();
//        headers.setCacheControl(CacheControl.noCache().getHeaderValue());
//
//        return new ResponseEntity<>(images, headers, HttpStatus.OK);
//    }
}