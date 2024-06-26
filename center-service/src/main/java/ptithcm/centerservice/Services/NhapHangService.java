package ptithcm.centerservice.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ptithcm.centerservice.DTORequest.DonNhapSanPhamRequest;
import ptithcm.centerservice.DTORequest.DonNhapThuCungRequest;
import ptithcm.centerservice.DTORequest.SoLuongSanPhamRequest;
import ptithcm.centerservice.DTORequest.SoLuongThuCungRequest;
import ptithcm.centerservice.DTOResponse.ChiNhanhDTO;
import ptithcm.centerservice.DTOResponse.DonNhapHangDTO;
import ptithcm.centerservice.Entity.*;
import ptithcm.centerservice.Repositories.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class NhapHangService {

    @Autowired
    private DonNhapHangRepo donNhapHangRepo;
    @Autowired
    private ChiNhanhRepo chiNhanhRepo;
    @Autowired
    private CtNhapThuCungRepo ctNhapThuCungRepo;
    @Autowired
    private CtNhapSanPhamRepo ctNhapSanPhamRepo;
    @Autowired
    private ApiService apiService;
    @Autowired
    private ThuCungRepo thuCungRepo;
    @Autowired
    private CtSanPhamRepo ctSanPhamRepo;
    @Autowired
    private SanPhamRepo sanPhamRepo;

    public DonNhapHangDTO convertTODTO(Donnhaphang donnhaphang) {
        DonNhapHangDTO donNhapHangDTO = new DonNhapHangDTO();
        donNhapHangDTO.setMaDonNhapHang(donnhaphang.getMadonnhaphang());
        donNhapHangDTO.setNgayLap(donnhaphang.getNgaylap());
        donNhapHangDTO.setMaNhanVien(donnhaphang.getManhanvien());
        donNhapHangDTO.setChiNhanhDTO(new ChiNhanhDTO());
        if (donnhaphang.getChinhanh() != null) {
            donNhapHangDTO.getChiNhanhDTO().setMaChiNhanh(donnhaphang.getChinhanh().getMachinhanh());
            donNhapHangDTO.getChiNhanhDTO().setTenChiNhanh(donnhaphang.getChinhanh().getTenchinhanh());
        }
        return donNhapHangDTO;
    }

    // Lấy danh sách các bản ghi
    public List<Donnhaphang> findAll(){
        return donNhapHangRepo.findAll();
    }

    @Transactional
    public DonNhapHangDTO taoDonNhapHang(DonNhapHangDTO donNhapHangDTO) {
        Donnhaphang donnhaphang = new Donnhaphang();
        donnhaphang.setManhanvien(donNhapHangDTO.getMaNhanVien());
        donnhaphang.setNgaylap(Timestamp.valueOf(LocalDateTime.now()));
        if (donNhapHangDTO.getChiNhanhDTO() != null) {
            donnhaphang.setChinhanh(chiNhanhRepo.findById(donNhapHangDTO.getChiNhanhDTO().getMaChiNhanh()).orElse(null));
        }
        try {
            donnhaphang = donNhapHangRepo.save(donnhaphang);
            DonNhapHangDTO donNhapHangDTO1 = convertTODTO(donnhaphang);
            return donNhapHangDTO1;
        } catch (Exception e) {
            throw new IllegalStateException("Tạo đơn hàng thất bại" + e.getMessage());
        }
    }

    @Transactional
    public void nhapThuCung(List<DonNhapThuCungRequest> list) {
        for (DonNhapThuCungRequest donNhapThuCungRequest : list) {
            Ctnhapthucung ctnhapthucung = new Ctnhapthucung();
            CtnhapthucungPK ctnhapthucungPK = new CtnhapthucungPK();
            Donnhaphang donnhaphang = donNhapHangRepo.findById(donNhapThuCungRequest.getMaDonNhap()).get();
            Thucung thucung = thuCungRepo.findById(donNhapThuCungRequest.getMaThuCung()).get();
            ctnhapthucungPK.setMadonnhap(donNhapThuCungRequest.getMaDonNhap());
            ctnhapthucungPK.setMathucung(donNhapThuCungRequest.getMaThuCung());
            ctnhapthucung.setId(ctnhapthucungPK);
            ctnhapthucung.setGianhap(donNhapThuCungRequest.getGiaNhap());
            ctnhapthucung.setSoluong(donNhapThuCungRequest.getSoLuong());
            ctnhapthucung.setDonnhaphang(donnhaphang);
            ctnhapthucung.setThucung(thucung);
            try {
                ctnhapthucung = ctNhapThuCungRepo.save(ctnhapthucung);
//                SoLuongThuCungRequest soLuongThuCungRequest = new SoLuongThuCungRequest();
//                soLuongThuCungRequest.setMaThuCung(ctnhapthucung.getId().getMathucung());
//                soLuongThuCungRequest.setSoLuongTon(ctnhapthucung.getSoluong());
//                apiService.updateSoLuongTon(soLuongThuCungRequest);
                Thucung thucung1 = thuCungRepo.findById(ctnhapthucung.getId().getMathucung()).get();
                thucung.setSoluongton(thucung.getSoluongton()+ctnhapthucung.getSoluong());
                thuCungRepo.save(thucung1);
            } catch (Exception e) {
                throw new IllegalStateException("Nhập thú cưng thất bại: " + e);
            }
        }
    }

    @Transactional
    public void nhapSanPham(List<DonNhapSanPhamRequest> list) {
        for (DonNhapSanPhamRequest donNhapSanPhamRequest : list) {
            Donnhaphang donnhaphang =  donNhapHangRepo.findById(donNhapSanPhamRequest.getMaDonNhap()).get();
            // CtsanphamPK ctsanphamPK = new CtsanphamPK(donnhaphang.getChinhanh().getMachinhanh(),donNhapSanPhamRequest.getMaSanPham());

            Ctnhapsanpham ctnhapsanpham = new Ctnhapsanpham();
            CtnhapsanphamPK ctnhapsanphamPK = new CtnhapsanphamPK();
            ctnhapsanphamPK.setMadonnhap(donNhapSanPhamRequest.getMaDonNhap());
            ctnhapsanphamPK.setMasanpham(donNhapSanPhamRequest.getMaSanPham());
            ctnhapsanpham.setId(ctnhapsanphamPK);
            ctnhapsanpham.setSoluong(donNhapSanPhamRequest.getSoLuong());
            ctnhapsanpham.setDongia(donNhapSanPhamRequest.getDonGia());
            ctnhapsanpham.setSanpham(sanPhamRepo.findById(donNhapSanPhamRequest.getMaSanPham()).get());
            ctnhapsanpham.setDonnhaphang(donnhaphang);
            try {
                ctnhapsanpham = ctNhapSanPhamRepo.save(ctnhapsanpham);
//                SoLuongSanPhamRequest soLuongSanPhamRequest = new SoLuongSanPhamRequest();
//                soLuongSanPhamRequest.setMaSanPham(ctnhapsanpham.getId().getMasanpham());
//                soLuongSanPhamRequest.setMaChiNhanh(ctnhapsanpham.getDonnhaphang().getChinhanh().getMachinhanh());
//                apiService.updateSoLuongTon(soLuongSanPhamRequest);
                CtsanphamPK ctsanphamPK = new CtsanphamPK();
                ctsanphamPK.setMasanpham(ctnhapsanpham.getId().getMasanpham());
                ctsanphamPK.setMachinhanh(ctnhapsanpham.getDonnhaphang().getChinhanh().getMachinhanh());
                Ctsanpham ctsanpham = ctSanPhamRepo.findById(ctsanphamPK).get();
                ctsanpham.setSoluongton(ctsanpham.getSoluongton()+ctnhapsanpham.getSoluong());
                ctSanPhamRepo.save(ctsanpham);
            } catch (Exception e) {
                throw new IllegalStateException("Nhập sản phẩm thất bại: " + e);
            }
        }
    }
}