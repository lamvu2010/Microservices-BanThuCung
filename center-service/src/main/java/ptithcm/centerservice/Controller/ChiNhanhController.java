package ptithcm.centerservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ptithcm.centerservice.DTOResponse.ChiNhanhDTO;
import ptithcm.centerservice.Entity.Chinhanh;
import ptithcm.centerservice.Services.ChiNhanhService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/center/chinhanh")
public class ChiNhanhController {
    @Autowired
    ChiNhanhService chiNhanhService;

    public ChiNhanhDTO convertToDTO(Chinhanh chinhanh){
        ChiNhanhDTO chiNhanhDTO = new ChiNhanhDTO();
        if(chinhanh == null){
            return chiNhanhDTO;
        }
        chiNhanhDTO.setMaChiNhanh(chinhanh.getMachinhanh());
        chiNhanhDTO.setTenChiNhanh(chinhanh.getTenchinhanh());
        return chiNhanhDTO;
    }
    @GetMapping
    public ResponseEntity<List<ChiNhanhDTO>> findAll() {
        List<Chinhanh> list = new ArrayList<>();
        List<ChiNhanhDTO> dtoList = new ArrayList<>();
        list = chiNhanhService.findAll();
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            for (Chinhanh item : list) {
                ChiNhanhDTO chiNhanhDTO = convertToDTO(item);
                dtoList.add(chiNhanhDTO);
            }
            return new ResponseEntity<>(dtoList, HttpStatus.OK);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        Optional<Chinhanh> chiNhanh = chiNhanhService.findById(id);
        if (chiNhanh.isEmpty()) {
            return new ResponseEntity<>("Entity not found", HttpStatus.NOT_FOUND);
        } else {
            ChiNhanhDTO chiNhanhDTO = new ChiNhanhDTO();
            chiNhanhDTO = convertToDTO(chiNhanh.orElse(null));
            return new ResponseEntity<>(chiNhanhDTO, HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody String tenChiNhanh) {
        if (chiNhanhService.existsByTenChiNhanh(tenChiNhanh)) {
            return new ResponseEntity<>("Entity is existed", HttpStatus.CONFLICT);
        }
        Chinhanh chiNhanh = new Chinhanh();
        chiNhanh.setTenchinhanh(tenChiNhanh);
        Chinhanh chiNhanhSaved = chiNhanhService.save(chiNhanh);
        if (chiNhanhSaved != null && chiNhanhSaved.getMachinhanh() > 0) {
            ChiNhanhDTO chiNhanhDTO = convertToDTO(chiNhanhSaved);
            return new ResponseEntity<>(chiNhanhDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody @Validated ChiNhanhDTO chiNhanhDTO) {
        Chinhanh chiNhanh = new Chinhanh();
        if (!chiNhanhService.existById(chiNhanhDTO.getMaChiNhanh())) {
            return new ResponseEntity<>("Entity not found", HttpStatus.NOT_FOUND);
        }
        chiNhanh.setMachinhanh(chiNhanhDTO.getMaChiNhanh());
        chiNhanh.setTenchinhanh(chiNhanhDTO.getTenChiNhanh());
        chiNhanhService.save(chiNhanh);
        ChiNhanhDTO chiNhanhDTO1 = convertToDTO(chiNhanh);
        return new ResponseEntity<>(chiNhanhDTO1, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        boolean existsIdBeforeDelete = chiNhanhService.existById(id);
        if (!existsIdBeforeDelete) {
            return new ResponseEntity<>("Entity not found", HttpStatus.NOT_FOUND);
        }

        chiNhanhService.deleteById(id);

        boolean stillExistsAfterDelete = chiNhanhService.existById(id);
        if (stillExistsAfterDelete) {
            return new ResponseEntity<>("Failed to delete entity", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>("Success to delete entity", HttpStatus.OK);
        }
    }
}