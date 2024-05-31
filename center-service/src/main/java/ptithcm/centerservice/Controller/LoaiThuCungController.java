package ptithcm.centerservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ptithcm.centerservice.DTOResponse.LoaiThuCungDTO;
import ptithcm.centerservice.Entity.Loaithucung;
import ptithcm.centerservice.Services.LoaiThuCungService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/center/loaithucung")
public class LoaiThuCungController {
    @Autowired
    LoaiThuCungService loaiThuCungService;

    public LoaiThuCungDTO convertToDTO(Loaithucung loaithucung){
        LoaiThuCungDTO loaiThuCungDTO = new LoaiThuCungDTO();
        if(loaithucung == null){
            return loaiThuCungDTO;
        }
        loaiThuCungDTO.setMaLoaiThuCung(loaithucung.getMaloaithucung());
        loaiThuCungDTO.setTenLoaiThuCung(loaithucung.getTenloaithucung());
        return loaiThuCungDTO;
    }
    @GetMapping
    public ResponseEntity<List<LoaiThuCungDTO>> findAll() {
        List<Loaithucung> list = new ArrayList<>();
        List<LoaiThuCungDTO> dtoList = new ArrayList<>();
        list = loaiThuCungService.findAll();
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            for (Loaithucung item : list) {
                LoaiThuCungDTO loaiThuCungDTO = convertToDTO(item);
                dtoList.add(loaiThuCungDTO);
            }
            return new ResponseEntity<>(dtoList, HttpStatus.OK);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        Optional<Loaithucung> loaithucung = loaiThuCungService.findById(id);
        if (loaithucung.isEmpty()) {
            return new ResponseEntity<>("Entity not found", HttpStatus.NOT_FOUND);
        } else {
            LoaiThuCungDTO loaiThuCungDTO = new LoaiThuCungDTO();
            loaiThuCungDTO = convertToDTO(loaithucung.orElse(null));
            return new ResponseEntity<>(loaiThuCungDTO, HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody String tenLoaiThuCung) {
        if (loaiThuCungService.existsByTenLoai(tenLoaiThuCung)) {
            return new ResponseEntity<>("Entity is existed", HttpStatus.CONFLICT);
        }
        Loaithucung loaithucung = new Loaithucung();
        loaithucung.setTenloaithucung(tenLoaiThuCung);
        Loaithucung loaiThuCungSaved = loaiThuCungService.save(loaithucung);
        if (loaiThuCungSaved != null && loaiThuCungSaved.getMaloaithucung() > 0) {
            LoaiThuCungDTO loaiThuCungDTO = convertToDTO(loaiThuCungSaved);
            return new ResponseEntity<>(loaiThuCungDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody @Validated LoaiThuCungDTO loaiThuCungDTO) {
        Loaithucung loaithucung = new Loaithucung();
        if (!loaiThuCungService.existsById(loaiThuCungDTO.getMaLoaiThuCung())) {
            return new ResponseEntity<>("Entity not found", HttpStatus.NOT_FOUND);
        }
        loaithucung.setMaloaithucung(loaiThuCungDTO.getMaLoaiThuCung());
        loaithucung.setTenloaithucung(loaiThuCungDTO.getTenLoaiThuCung());
        loaiThuCungService.save(loaithucung);
        LoaiThuCungDTO loaiThuCungDTO1 = convertToDTO(loaithucung);
        return new ResponseEntity<>(loaiThuCungDTO1, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        boolean existsIdBeforeDelete = loaiThuCungService.existsById(id);
        if (!existsIdBeforeDelete) {
            return new ResponseEntity<>("Entity not found", HttpStatus.NOT_FOUND);
        }

        loaiThuCungService.deleteById(id);

        boolean stillExistsAfterDelete = loaiThuCungService.existsById(id);
        if (stillExistsAfterDelete) {
            return new ResponseEntity<>("Failed to delete entity", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>("Success to delete entity", HttpStatus.OK);
        }
    }
}