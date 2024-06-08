package com.example.order_service.Controller;

import com.example.order_service.DTORequest.*;
import com.example.order_service.DTOResponse.DonDatDTO;
import com.example.order_service.DTOResponse.HoaDonDTO;
import com.example.order_service.Entity.*;
import com.example.order_service.Service.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<Hoadon> list = hoaDonService.findAll();
        List<HoaDonDTO> dtoList = new ArrayList<>();
        for (Hoadon item : list) {
            HoaDonDTO hoaDonDTO = new HoaDonDTO();
            hoaDonDTO.setMaHoaDon(item.getSohoadon());
            hoaDonDTO.setNgayLap(item.getNgaylap());
            hoaDonDTO.setTongHoaDon(item.getTonghoadon());
            dtoList.add(hoaDonDTO);
        }
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getHoaDon(@PathVariable long id) {
        HoaDonDTO hoaDonDTO = new HoaDonDTO();
        DonDatDTO donDatDTO = new DonDatDTO();
        Dondat dondat = donDatService.findById(id).orElse(null);
        Hoadon hoadon = hoaDonService.findById(id).orElse(null);
        if (dondat != null && hoadon != null) {
            donDatDTO = donDatService.convertToDTO(dondat);
            hoaDonDTO.setDonDat(donDatDTO);
            hoaDonDTO.setTenNhanVien(hoadon.getManhanvien());
            hoaDonDTO.setNgayLap(hoadon.getNgaylap());
            hoaDonDTO.setTongHoaDon(hoadon.getTonghoadon());
            return new ResponseEntity<>(hoaDonDTO, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Không tìm thấy hóa đơn, đơn đặt",HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/sp")
    public ResponseEntity<?> themSP(@RequestBody List<DonDatSanPhamRequest> list) {
        try {
            for (DonDatSanPhamRequest item : list) {
                System.out.println(item.getMaChiNhanh());
                System.out.println(item.getMaDonDat());
                System.out.println(item.getMaSanPham());
                System.out.println(item.getSoLuong());
                System.out.println(item.getDonGia());
                Ctmuasanpham ctmuasanpham = new Ctmuasanpham();
                CtmuasanphamPK ctmuasanphamPK = new CtmuasanphamPK();
                ctmuasanphamPK.setSodondat(item.getMaDonDat());
                ctmuasanphamPK.setMachinhanh(item.getMaChiNhanh());
                ctmuasanphamPK.setMasanpham(item.getMaSanPham());
                ctmuasanpham.setId(ctmuasanphamPK);
                ctmuasanpham.setSoluong(item.getSoLuong());
                ctmuasanpham.setDongia(item.getDonGia());
                Optional<Dondat> dondat =donDatService.findById(item.getMaDonDat());
                if(dondat.isPresent()){
                Dondat dondat1 = dondat.get();
                System.out.println(dondat1.getMakhachhang());
                ctmuasanpham.setDondat(dondat1);
                ctmuasanpham.setMasanpham(item.getMaSanPham());
                ctmuasanpham.setMachinhanh(item.getMaChiNhanh());
                ctmuasanpham = ctMuaSanPhamService.save(ctmuasanpham);
                }
                else{
                    System.out.println("not found");
                    return new ResponseEntity<>("Thêm thất bại", HttpStatus.BAD_REQUEST);
                }
                
            }
            return new ResponseEntity<>("Thêm thành công", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
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
                ctmuathucung.setDondat(donDatService.findById(item.getMaDonDat()).get());
                ctmuathucung = ctMuaThuCungService.save(ctmuathucung);
            }
            return new ResponseEntity<>("Thêm thành công", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
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
            hoadon.setNgaylap(Timestamp.valueOf(LocalDateTime.now()));
            hoadon.setTonghoadon(hoaDonService.tongHoaDon(hoaDonRequest.getSoHoaDon()));
            hoadon.setManhanvien(hoaDonRequest.getMaNhanVien());
            hoadon = hoaDonService.save(hoadon);
            Dondat dondat = donDatService.findById(hoaDonRequest.getSoHoaDon()).get();
            dondat.setTrangthai(Boolean.TRUE);
            dondat = donDatService.save(dondat);
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

    @GetMapping("/thanhtien/{id}")
    public String thanhtien(@PathVariable("id")long id) {
        String thanhtien = hoaDonService.tongHoaDon(id).toString();
        return thanhtien;
    }
}