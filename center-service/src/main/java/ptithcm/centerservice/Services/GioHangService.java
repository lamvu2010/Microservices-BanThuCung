package ptithcm.centerservice.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ptithcm.centerservice.DTORequest.GhSanPhamRequest;
import ptithcm.centerservice.DTORequest.GhThuCungRequest;
import ptithcm.centerservice.DTOResponse.*;
import ptithcm.centerservice.Entity.*;
import ptithcm.centerservice.Repositories.*;
import ptithcm.centerservice.Controller.ChiNhanhController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class GioHangService {
    @Autowired
    private GhSanPhamRepo ghSanPhamRepo;
    @Autowired
    private GhThuCungRepo ghThuCungRepo;
    @Autowired
    private ChiNhanhRepo chiNhanhRepo;
    @Autowired
    private GiongRepo giongRepo;
    @Autowired
    private CtSanPhamRepo ctSanPhamRepo;
    @Autowired
    private SanPhamRepo sanPhamRepo;
    @Autowired
    private ThuCungRepo thuCungRepo;

    @Transactional
    public Ghthucung themThuCung(GhThuCungRequest ghThuCungRequest) {
        Ghthucung ghthucung = new Ghthucung();
        GhthucungPK ghthucungPK = new GhthucungPK();
        ghthucungPK.setMakhachhang(ghThuCungRequest.getMaKhachHang());
        ghthucungPK.setMathucung(ghThuCungRequest.getMaThuCung());
        ghthucung.setId(ghthucungPK);
        ghthucung.setThucung(thuCungRepo.findById(ghThuCungRequest.getMaThuCung()).get());
        try {
            ghthucung = ghThuCungRepo.save(ghthucung);
            return ghthucung;
        } catch (Exception e) {
            throw new IllegalStateException("Thêm thất bại" + e.getMessage());
        }
    }

    @Transactional
    public Ghsanpham themSanPham(GhSanPhamRequest ghSanPhamRequest) {
        Ghsanpham ghsanpham = new Ghsanpham();
        GhsanphamPK ghsanphamPK = new GhsanphamPK();
        ghsanphamPK.setMakhachhang(ghSanPhamRequest.getMaKhachHang());
        ghsanphamPK.setMasanpham(ghSanPhamRequest.getMaSanPham());
        ghsanphamPK.setMachinhanh(ghSanPhamRequest.getMaChiNhanh());
        ghsanpham.setId(ghsanphamPK);
        ghsanpham.setChinhanh(chiNhanhRepo.findById(ghSanPhamRequest.getMaChiNhanh()).get());
        ghsanpham.setSanpham(sanPhamRepo.findById(ghSanPhamRequest.getMaSanPham()).get());
        try {
            ghsanpham = ghSanPhamRepo.save(ghsanpham);
            return ghsanpham;
        } catch (Exception e) {
            throw new IllegalStateException(("Thêm thất bại") + e.getMessage());
        }
    }

    @Transactional
    public List<ThuCungDTO> thuCungTrongGiohang(int maChiNhanh, String maKhachHang) {
        List<Map<?, ?>> list = ghThuCungRepo.sanPhamTrongGioHang(maChiNhanh, maKhachHang);
        List<ThuCungDTO> thuCungDTOList = new ArrayList<>();
        for (Map<?, ?> item : list) {
            ThuCungDTO thuCungDTO = new ThuCungDTO();
            if (item.get("MATHUCUNG") != null) {
                thuCungDTO.setMaThuCung((long) item.get("MATHUCUNG"));
                thuCungDTO.setTenThuCung((String) item.get("TENTHUCUNG"));
                thuCungDTO.setChu((String) item.get("CHU"));
                thuCungDTO.setSoLuongTon((int) item.get("SOLUONGTON"));
                thuCungDTO.setMoTa((String) item.get("MOTA"));
                thuCungDTO.setGiaHienTai((BigDecimal) item.get("GIAHIENTAI"));
                thuCungDTO.setTrangThaiBan((int) item.get("TRANGTHAIBAN"));
            }
            if (item.get("MACHINHANH") != null) {
                Chinhanh chinhanh = chiNhanhRepo.findById((int) item.get("MACHINHANH")).get();
                ChiNhanhDTO chiNhanhDTO = new ChiNhanhDTO();
                chiNhanhDTO.setMaChiNhanh(chinhanh.getMachinhanh());
                chiNhanhDTO.setTenChiNhanh(chinhanh.getTenchinhanh());
                thuCungDTO.setChiNhanh(chiNhanhDTO);
            }
            if (item.get("MAGIONG") != null) {
                Giong giong = giongRepo.findById((int) item.get("MAGIONG")).get();
                GiongDTO giongDTO = new GiongDTO();
                giongDTO.setMaGiong(giong.getMagiong());
                giongDTO.setTengiong(giong.getTengiong());
                giongDTO.setLoaiThuCung(new LoaiThuCungDTO());
                if (giong.getLoaithucung() != null) {
                    giongDTO.getLoaiThuCung().setMaLoaiThuCung(giong.getLoaithucung().getMaloaithucung());
                    giongDTO.getLoaiThuCung().setTenLoaiThuCung(giong.getLoaithucung().getTenloaithucung());
                }
                thuCungDTO.setGiong(giongDTO);
            }
            thuCungDTOList.add(thuCungDTO);
        }
        return thuCungDTOList;
    }

    @Transactional
    public List<SanPhamDTO> sanPhamTrongGioHang(int maChiNhanh, String maKhachhang) {
        List<Map<?, ?>> list = ghSanPhamRepo.sanPhamTrongGioHang(maKhachhang, maChiNhanh);
        List<SanPhamDTO> sanPhamDTOList = new ArrayList<>();
        for (Map<?, ?> item : list) {
            if (item.get("MACHINHANH") != null && item.get("MASANPHAM") != null) {
                CtsanphamPK ctsanphamPK = new CtsanphamPK();
                ctsanphamPK.setMasanpham((long) item.get("MASANPHAM"));
                ctsanphamPK.setMachinhanh((int) item.get("MACHINHANH"));
                Ctsanpham ctsanpham = ctSanPhamRepo.findById(ctsanphamPK).get();
                SanPhamDTO sanPhamDTO = new SanPhamDTO();
                sanPhamDTO.setMaSanPham(ctsanpham.getSanpham().getMasanpham());
                sanPhamDTO.setTenSanPham(ctsanpham.getSanpham().getTensanpham());
                sanPhamDTO.setGiaHienTai(ctsanpham.getSanpham().getGiahientai());
                sanPhamDTO.setLoaiSanPham(new LoaiSanPhamDTO());
                if (ctsanpham.getSanpham().getLoaisanpham() != null) {
                    sanPhamDTO.getLoaiSanPham().setMaLoaiSanPham(ctsanpham.getSanpham().getLoaisanpham().getMaloaisanpham());
                    sanPhamDTO.getLoaiSanPham().setTenLoaiSanPham(ctsanpham.getSanpham().getLoaisanpham().getTenloaisanpham());
                }
                if (ctsanpham.getSanpham().getHinhanh() != null && !ctsanpham.getSanpham().getHinhanh().isEmpty()) {
                    sanPhamDTO.setHinhAnh(new ArrayList<>());
                    for (Hinhanh itemm : ctsanpham.getSanpham().getHinhanh()) {
                        sanPhamDTO.getHinhAnh().add(itemm.getMahinhanh());
                    }
                }
                if (ctsanpham.getChinhanh() != null) {
                    sanPhamDTO.setMaChiNhanh(ctsanpham.getChinhanh().getMachinhanh());
                }
                sanPhamDTO.setSoLuongTon(ctsanpham.getSoluongton());
                sanPhamDTOList.add(sanPhamDTO);
            }
        }
        return sanPhamDTOList;
    }
}
