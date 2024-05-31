package ptithcm.centerservice.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ptithcm.centerservice.Entity.Ctbanggiathucung;
import ptithcm.centerservice.Repositories.CtBangGiaThuCungRepo;

import java.util.List;
import java.util.Map;
@Service
public class BangGiaThuCungService {
    @Autowired
    CtBangGiaThuCungRepo ctBangGiaThuCungRepo;
    public List<Map<?,?>> danhSachThuCungBan(){
        return ctBangGiaThuCungRepo.danhSachThuCungBan();
    }

    public Ctbanggiathucung save(Ctbanggiathucung ctbanggiathucung){
        return ctBangGiaThuCungRepo.save(ctbanggiathucung);
    }
}
