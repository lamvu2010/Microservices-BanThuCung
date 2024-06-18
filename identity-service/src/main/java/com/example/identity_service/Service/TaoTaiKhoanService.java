package com.example.identity_service.Service;

import com.example.identity_service.DTORequest.ConfirmEmailRequest;
import com.example.identity_service.DTORequest.RegisterRequest;
import com.example.identity_service.Email.EmailService;
import com.example.identity_service.Entity.Khachhang;
import com.example.identity_service.Entity.Taikhoan;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.validator.routines.EmailValidator;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class TaoTaiKhoanService {
    @Autowired
    private TaiKhoanService taiKhoanService;
    @Autowired
    private KhachHangService khachHangService;
    @Autowired
    private EmailService emailService;

    @Transactional
    public String register(RegisterRequest registerRequest) {
        if (taiKhoanService.isExistsById(registerRequest.getTenDangNhap())) {
            throw new IllegalStateException("Tên đăng nhập đã tồn tại");
        }
        EmailValidator emailValidator = EmailValidator.getInstance();
        if (!emailValidator.isValid(registerRequest.getEmail())) {
            throw new IllegalStateException("Email không đúng");
        }
        if (registerRequest.getTenDangNhap() == null) {
            return "Tên đăng nhập null";
        }
        Taikhoan taikhoan = new Taikhoan();
        taikhoan.setTendangnhap(registerRequest.getTenDangNhap());
        taikhoan.setMatkhau(registerRequest.getMatKhau());
        taikhoan.setQuyen("khachhang");
        String uuid = UUID.randomUUID().toString().substring(0, 10);
        taikhoan.setMaxacnhan(uuid);
        taikhoan.setTrangthai(Boolean.FALSE);
        taikhoan.setThoigiantaoma(LocalDateTime.now());
        taikhoan.setThoigianhethan(LocalDateTime.now().plusMinutes(5));
        Khachhang khachhang = new Khachhang();
        khachhang.setMakhachhang(registerRequest.getTenDangNhap());
        khachhang.setEmail(registerRequest.getEmail());
        khachhang.setCccd(registerRequest.getCccd());
        khachhang.setSodienthoai(registerRequest.getSoDienThoai());
        khachhang.setHo(registerRequest.getHo());
        khachhang.setTen(registerRequest.getTen());
        khachHangService.save(khachhang);
        taikhoan = taiKhoanService.save(taikhoan);
        //Doan gui email
    //    try {
    //        emailService.send(khachhang.getEmail(), "MÃ XÁC NHẬN", taikhoan.getMaxacnhan());
    //    } catch (MessagingException e) {
    //        return "Gửi mail thất bại!";
    //    }
        System.out.println("Ma xac nhan: "+ taikhoan.getMaxacnhan());
        return taikhoan.getMaxacnhan();
    }

    @Transactional
    public String confirmEmail(ConfirmEmailRequest confirmEmailRequest) {
        if (!taiKhoanService.isExistsById(confirmEmailRequest.getTenDangNhap())) {
            throw new IllegalStateException("Tài khoản không tồn tại, vui lòng thử lại");
        }
        Taikhoan taikhoan = new Taikhoan();
        taikhoan = taiKhoanService.findBytendangnhap(confirmEmailRequest.getTenDangNhap());
        String verifiedCode = confirmEmailRequest.getMaXacNhan();
        if (taikhoan.getThoigianxacnhan() != null) {
            throw new IllegalStateException("Tài khoản đã được xác nhận");
        }
        LocalDateTime thoiGianHetHan = taikhoan.getThoigianhethan();
        if (thoiGianHetHan.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("Mã xác nhận hết hạn");
        }
        if (taikhoan.getMaxacnhan().equals(confirmEmailRequest.getMaXacNhan())) {
            taikhoan.setTrangthai(Boolean.TRUE);
            taikhoan.setThoigianxacnhan(LocalDateTime.now());
            taiKhoanService.save(taikhoan);
            return "Xác nhận thành công, đăng nhập để tiếp tục";
        } else {
            return "Mã xác nhận không chính xác";
        }
    }

    @Transactional
    public String getVerifiedCodeAgain(RegisterRequest registerRequest) {
        Taikhoan taikhoan = new Taikhoan();
        taikhoan.setTendangnhap(registerRequest.getTenDangNhap());
        taikhoan.setMatkhau(registerRequest.getMatKhau());
        taikhoan.setQuyen("khachhang");
        String uuid = UUID.randomUUID().toString().substring(0, 10);
        taikhoan.setMaxacnhan(uuid);
        taikhoan.setTrangthai(Boolean.FALSE);
        taikhoan.setThoigiantaoma(LocalDateTime.now());
        taikhoan.setThoigianhethan(LocalDateTime.now().plusMinutes(5));
        Khachhang khachhang = new Khachhang();
        khachhang.setMakhachhang(registerRequest.getTenDangNhap());
        khachhang.setEmail(registerRequest.getEmail());
        khachhang.setCccd(registerRequest.getCccd());
        khachhang.setSodienthoai(registerRequest.getSoDienThoai());
        khachhang.setHo(registerRequest.getHo());
        khachhang.setTen(registerRequest.getTen());
        khachHangService.save(khachhang);
        taikhoan = taiKhoanService.save(taikhoan);
    //    try {
    //        emailService.send(khachhang.getEmail(), "MÃ XÁC NHẬN", taikhoan.getMaxacnhan());
    //    } catch (MessagingException e) {
    //        return "Gửi mail thất bại!";
    //    }
        System.out.println("Ma xac nhan: "+ taikhoan.getMaxacnhan());    
        return taikhoan.getMaxacnhan();
    }
}