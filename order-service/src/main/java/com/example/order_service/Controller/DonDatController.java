package com.example.order_service.Controller;

import com.example.order_service.DTOResponse.DonDatDTO;
import com.example.order_service.Entity.Dondat;
import com.example.order_service.Repository.DonDatRepo;
import com.example.order_service.Service.DonDatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/order/don-dat")
public class DonDatController {

    @Autowired
    private DonDatService donDatService;

    @GetMapping
    public ResponseEntity<List<DonDatDTO>> findAll() {
        List<Dondat> list = new ArrayList<>();
        List<DonDatDTO> dtoList = new ArrayList<>();
        list = donDatService.finAll();
        for (Dondat item : list) {
            DonDatDTO donDatDTO = new DonDatDTO();
            donDatDTO.setSoDonDat(item.getSodondat());
            donDatDTO.setDiaChi(item.getDiachidat());
            donDatDTO.setNgayLap(item.getNgaylap());
            donDatDTO.setSoDienThoai(item.getSodienthoai());
            donDatDTO.setMaKhachhang(item.getMakhachhang());
            donDatDTO.setMaChiNhanh(item.getMachinhanh());
            dtoList.add(donDatDTO);
        }
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    // nhớ thêm tự động tăng so don dat trong db
    @PostMapping
    public ResponseEntity<?> insert(@RequestBody DonDatDTO donDatDTO) {
        Dondat dondat = new Dondat();
        dondat.setDiachidat(donDatDTO.getDiaChi());
        dondat.setMachinhanh(donDatDTO.getMaChiNhanh());
        dondat.setNgaylap(donDatDTO.getNgayLap());
        dondat.setMakhachhang(donDatDTO.getMaKhachhang());
        dondat.setSodienthoai(donDatDTO.getSoDienThoai());
        dondat.setTrangthai(Boolean.FALSE);
        try {
            dondat = donDatService.save(dondat);
            return new ResponseEntity<>(dondat, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Thêm thất bại", HttpStatus.BAD_REQUEST);
        }
    }
}
