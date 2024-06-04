package com.example.order_service.Controller;

import com.example.order_service.DTORequest.*;
import com.example.order_service.Entity.*;
import com.example.order_service.Repository.CtMuaThuCungRepo;
import com.example.order_service.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order/dat-hang")
public class DatHangController {

    @Autowired
    private CtMuaSanPhamService ctMuaSanPhamService;

    @Autowired
    private CtMuaThuCungService ctMuaThuCungService;

    @Autowired
    private HoaDonService hoaDonService;

    @Autowired
    private DonDatService donDatService;

    @Autowired
    private ApiService apiService;

    @PostMapping("/sp")
    public ResponseEntity<?> themSP(@RequestBody List<DonDatSanPhamRequest> list) {
        try {
            for (DonDatSanPhamRequest item : list) {
                Ctmuasanpham ctmuasanpham = new Ctmuasanpham();
                CtmuasanphamPK ctmuasanphamPK = new CtmuasanphamPK();
                ctmuasanphamPK.setSodondat(item.getMaDonDat());
                ctmuasanphamPK.setMachinhanh(item.getMaChiNhanh());
                ctmuasanphamPK.setMasanpham(item.getMaSanPham());
                ctmuasanpham.setId(ctmuasanphamPK);
                ctmuasanpham.setSoluong(item.getSoLuong());
                ctmuasanpham.setDongia(item.getDonGia());
                ctmuasanpham = ctMuaSanPhamService.save(ctmuasanpham);
            }
            return new ResponseEntity<>("Thêm thành công", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Thêm thất bại", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/tc")
    public ResponseEntity<?> themTC(@RequestBody List<DonDatThuCungRequest> list) {
        try {
            for (DonDatThuCungRequest item : list) {
                Ctmuathucung ctmuathucung = new Ctmuathucung();
                CtmuathucungPK ctmuathucungPK = new CtmuathucungPK();
                ctmuathucungPK.setMadondat(item.getMaDonDat());
                ctmuathucungPK.setMathucung(item.getMaThuCung());
                ctmuathucung.setId(ctmuathucungPK);
                ctmuathucung.setDongia(item.getDonGia());
                ctmuathucung.setSoluong(item.getSoLuong());
                ctmuathucung = ctMuaThuCungService.save(ctmuathucung);
            }
            return new ResponseEntity<>("Thêm thành công", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Thêm thất bại", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/hoa-don")
    public ResponseEntity<?> themHoaDon(@RequestBody HoaDonRequest hoaDonRequest) {
        if (!donDatService.isExistsById(hoaDonRequest.getSoHoaDon())) {
            return new ResponseEntity<>("Đơn đặt không tồn tai", HttpStatus.NOT_FOUND);
        }
        try {
            Hoadon hoadon = new Hoadon();
            hoadon.setSohoadon(hoaDonRequest.getSoHoaDon());
            hoadon.setNgaylap(hoaDonRequest.getNgayLap());
            hoadon.setTonghoadon(hoaDonService.tongHoaDon(hoaDonRequest.getSoHoaDon()));
            hoadon.setManhanvien(hoaDonRequest.getMaNhanVien());
            hoadon = hoaDonService.save(hoadon);
            Dondat dondat = donDatService.findById(hoaDonRequest.getSoHoaDon()).get();
            System.out.println("Chạy vào try");
            for (Ctmuasanpham item : dondat.getCtmuasanpham()) {
                SoLuongSanPhamRequest soLuongSanPhamRequest = new SoLuongSanPhamRequest();
                soLuongSanPhamRequest.setMaChiNhanh(item.getId().getMachinhanh());
                soLuongSanPhamRequest.setMaSanPham(item.getId().getMasanpham());
                soLuongSanPhamRequest.setSoLuongTon(-item.getSoluong());
                System.out.println(apiService.updateSoLuongTon(soLuongSanPhamRequest));
                System.out.println("Gửi request");
            }
            for (Ctmuathucung item : dondat.getCtmuathucung()) {
                SoLuongThuCungRequest soLuongThuCungRequest = new SoLuongThuCungRequest();
                soLuongThuCungRequest.setMaThuCung(item.getId().getMathucung());
                soLuongThuCungRequest.setSoLuongTon(-item.getSoluong());
                apiService.updateSoLuongTon(soLuongThuCungRequest);
            }
            return new ResponseEntity<>("Thêm thành công", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Thêm thất bại", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/thanhtien")
    public String thanhtien() {
        String thanhtien = hoaDonService.tongHoaDon(1L).toString();
        return thanhtien;
    }
}