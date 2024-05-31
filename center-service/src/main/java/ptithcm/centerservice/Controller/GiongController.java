package ptithcm.centerservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ptithcm.centerservice.DTOResponse.GiongDTO;
import ptithcm.centerservice.DTOResponse.LoaiThuCungDTO;
import ptithcm.centerservice.Entity.Giong;
import ptithcm.centerservice.Services.GiongService;
import ptithcm.centerservice.Services.LoaiThuCungService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/center/giong")
public class GiongController {
    @Autowired
    GiongService giongService;
    @Autowired
    LoaiThuCungService loaiThuCungService;

    public GiongDTO convertToDTO(Giong giong){
        GiongDTO giongDTO = new GiongDTO();
        if(giong == null){
            return giongDTO;
        }
        giongDTO.setMaGiong(giong.getMagiong());
        giongDTO.setTengiong(giong.getTengiong());
        giongDTO.setLoaiThuCung(new LoaiThuCungDTO());
        if(giong.getLoaithucung()!= null){
            giongDTO.getLoaiThuCung().setMaLoaiThuCung(giong.getLoaithucung().getMaloaithucung());
            giongDTO.getLoaiThuCung().setTenLoaiThuCung(giong.getLoaithucung().getTenloaithucung());
        }
        return giongDTO;
    }
    @GetMapping
    public ResponseEntity<List<GiongDTO>> findAll(){
        List<Giong> list = new ArrayList<>();
        List<GiongDTO> dtoList = new ArrayList<>();
        list = giongService.findAll();
        for(Giong item : list){
            GiongDTO giongDTO = convertToDTO(item);
            dtoList.add(giongDTO);
        }
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody GiongDTO giongDTO){
        Giong giong = new Giong();
        giong.setTengiong(giongDTO.getTengiong());
        giong.setLoaithucung(loaiThuCungService.findById(giongDTO.getLoaiThuCung().getMaLoaiThuCung()).orElse(null));
        giong = giongService.save(giong);
        if (giongService.isExistsById(giong.getMagiong())){
            GiongDTO giongDTO1 = convertToDTO(giong);
            return new ResponseEntity<>(giongDTO1,HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Thêm thất bại",HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody GiongDTO giongDTO){
        if(!giongService.isExistsById(giongDTO.getMaGiong())){
            return new ResponseEntity<>("Giống không tồn tại",HttpStatus.BAD_REQUEST);
        }
        Giong giong = new Giong();
        giong.setMagiong(giongDTO.getMaGiong());
        giong.setTengiong(giongDTO.getTengiong());
        giong.setLoaithucung(loaiThuCungService.findById(giongDTO.getLoaiThuCung().getMaLoaiThuCung()).orElse(null));
        giongService.save(giong);
        GiongDTO giongDTO1 = convertToDTO(giong);
        return new ResponseEntity<>(giongDTO1,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        boolean tontai = giongService.isExistsById(id);
        if(!tontai){
            return new ResponseEntity<>("Giống không tồn tại",HttpStatus.BAD_REQUEST);
        }
        else{
            giongService.delete(giongService.findById(id).orElse(null));
            return new ResponseEntity<>("Xóa thành công", HttpStatus.OK);
        }
    }
}
