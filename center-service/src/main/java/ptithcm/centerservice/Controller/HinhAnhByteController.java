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

import ptithcm.centerservice.DTOResponse.HinhAnhDTO;
import ptithcm.centerservice.File.StorageService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/center/hinhanh")
public class HinhAnhByteController {
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