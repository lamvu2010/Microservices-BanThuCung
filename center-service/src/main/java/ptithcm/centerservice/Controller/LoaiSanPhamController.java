package ptithcm.centerservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ptithcm.centerservice.DTOResponse.LoaiSanPhamDTO;
import ptithcm.centerservice.Entity.Loaisanpham;
import ptithcm.centerservice.Services.LoaiSanPhamService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/center/loaisanpham")
public class LoaiSanPhamController {
    @Autowired
    LoaiSanPhamService loaiSanPhamService;

    public LoaiSanPhamDTO convertToDTO(Loaisanpham loaisanpham){
        LoaiSanPhamDTO loaiSanPhamDTO = new LoaiSanPhamDTO();
        if(loaisanpham == null){
            return loaiSanPhamDTO;
        }
        loaiSanPhamDTO.setMaLoaiSanPham(loaisanpham.getMaloaisanpham());
        loaiSanPhamDTO.setTenLoaiSanPham(loaisanpham.getTenloaisanpham());
        return loaiSanPhamDTO;
    }
    @GetMapping
    public ResponseEntity<List<LoaiSanPhamDTO>> getAll() {
        List<Loaisanpham> list = new ArrayList<>();
        List<LoaiSanPhamDTO> dtoList = new ArrayList<>();
        list = loaiSanPhamService.findAll();
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            for(Loaisanpham item : list){
                LoaiSanPhamDTO loaiSanPhamDTO = convertToDTO(item);
                dtoList.add(loaiSanPhamDTO);
            }
            return new ResponseEntity<>(dtoList, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        Optional<Loaisanpham> loaisanpham = loaiSanPhamService.findById(id);
        if (loaisanpham.isEmpty()) {
            return new ResponseEntity<>("Entity not found", HttpStatus.NOT_FOUND);
        } else {
            LoaiSanPhamDTO loaiSanPhamDTO = convertToDTO(loaisanpham.orElse(null));
            return new ResponseEntity<>(loaiSanPhamDTO, HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody String tenLoaiSanPham) {
        if (loaiSanPhamService.existsByTenLoai(tenLoaiSanPham)) {
            return new ResponseEntity<>("Entity is existed", HttpStatus.BAD_REQUEST);
        }
        Loaisanpham loaisanpham = new Loaisanpham();
        loaisanpham.setTenloaisanpham(tenLoaiSanPham);
        Loaisanpham loaisanphamSaved = loaiSanPhamService.save(loaisanpham);
        if (loaisanphamSaved != null && loaisanphamSaved.getMaloaisanpham() > 0) {
            LoaiSanPhamDTO loaiSanPhamDTO = convertToDTO(loaisanphamSaved);
            return new ResponseEntity<>(loaiSanPhamDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cannot save entity. Try again", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody @Validated LoaiSanPhamDTO loaiSanPhamDTO) {
        Loaisanpham loaisanpham = new Loaisanpham();
        if (!loaiSanPhamService.existsById(loaiSanPhamDTO.getMaLoaiSanPham())) {
            return new ResponseEntity<>("Entity not found", HttpStatus.NOT_FOUND);
        }
        loaisanpham.setMaloaisanpham(loaiSanPhamDTO.getMaLoaiSanPham());
        loaisanpham.setTenloaisanpham(loaiSanPhamDTO.getTenLoaiSanPham());
        loaiSanPhamService.save(loaisanpham);
        LoaiSanPhamDTO loaiSanPhamDTO1 = convertToDTO(loaisanpham);
        return new ResponseEntity<>(loaiSanPhamDTO1, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        boolean existsIdBeforeDelete = loaiSanPhamService.existsById(id);
        if (!existsIdBeforeDelete) {
            return new ResponseEntity<>("Entity not found", HttpStatus.NOT_FOUND);
        }
        loaiSanPhamService.deleteById(id);
        boolean existsIdAfterDelete = loaiSanPhamService.existsById(id);
        if (existsIdAfterDelete) {
            return new ResponseEntity<>("Failed to delete entity", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>("Success to delete entity", HttpStatus.OK);
        }
    }
}
