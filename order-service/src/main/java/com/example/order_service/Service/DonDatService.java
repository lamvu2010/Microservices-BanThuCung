package com.example.order_service.Service;

import com.example.order_service.DTOResponse.DonDatDTO;
import com.example.order_service.Entity.Dondat;
import com.example.order_service.Repository.DonDatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DonDatService {

    @Autowired
    private DonDatRepo donDatRepo;

    public List<Dondat> finAll() {
        return donDatRepo.findAll();
    }
    public Optional<Dondat> findById(Long id){
        return donDatRepo.findById(id);
    }

    public Dondat save(Dondat dondat) {
        return donDatRepo.save(dondat);
    }

    public void delete(Dondat dondat) {
        donDatRepo.delete(dondat);
    }

    public void deleteById(Long id) {
        donDatRepo.deleteById(id);
    }

    public boolean isExistsById(long id) {
        return donDatRepo.existsById(id);
    }

    public DonDatDTO convertToDTO(Dondat dondat){
        DonDatDTO donDatDTO = new DonDatDTO();
        donDatDTO.setSoDonDat(dondat.getSodondat());
        donDatDTO.setDiaChi(dondat.getDiachidat());
        donDatDTO.setNgayLap(dondat.getNgaylap());
        donDatDTO.setMaKhachhang(dondat.getMakhachhang());
        donDatDTO.setSoDienThoai(dondat.getSodienthoai());
        donDatDTO.setMaChiNhanh(dondat.getMachinhanh());
        return donDatDTO;
    }
}
