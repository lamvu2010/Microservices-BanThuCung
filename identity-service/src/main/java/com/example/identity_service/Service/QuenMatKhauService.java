package com.example.identity_service.Service;

import com.example.identity_service.DTORequest.ConfirmEmailRequest;
import com.example.identity_service.Email.EmailService;
import com.example.identity_service.Entity.Taikhoan;
import com.netflix.discovery.converters.Auto;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class QuenMatKhauService {
    @Autowired
    private TaiKhoanService taiKhoanService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private NhanVienService nhanVienService;
    @Autowired
    private KhachHangService khachHangService;

    @Transactional
    public String sendCode(String username) {
        if (username==null||!taiKhoanService.isExistsById(username)) {
            throw new IllegalStateException("Tên đăng nhập không tồn tại");
        }
        Taikhoan taikhoan = taiKhoanService.findBytendangnhap(username);
        String uuid = UUID.randomUUID().toString().substring(0, 10);
        taikhoan.setMaxacnhan(uuid);
        taikhoan.setThoigiantaoma(LocalDateTime.now());
        taikhoan.setThoigianhethan(LocalDateTime.now().plusMinutes(5));
        taikhoan = taiKhoanService.save(taikhoan);
        //Doan gui email
    //    try { 
    //     if(taikhoan.getQuyen().equals("khachhang")){
    //     emailService.send(khachHangService.findById(taikhoan.getTendangnhap()).get().getEmail(), "Ma xac nhan", uuid);
    //     }else{
    //     emailService.send(nhanVienService.findById(taikhoan.getTendangnhap()).get().getEmail(), "Ma xac nhan", uuid);
    //     }
    //    } catch (MessagingException e) {
    //        return "Gửi mail thất bại!";
    //    }
        System.out.println(taikhoan.getMaxacnhan());
        return taikhoan.getMaxacnhan();
    }

    @Transactional
    public String confirmEmail(ConfirmEmailRequest confirmEmailRequest) {
        if (!taiKhoanService.isExistsById(confirmEmailRequest.getTenDangNhap())) {
            throw new IllegalStateException("Tài khoản không tồn tại, vui lòng thử lại");
        }
        Taikhoan taikhoan = taiKhoanService.findBytendangnhap(confirmEmailRequest.getTenDangNhap());
        String verifiedCode = confirmEmailRequest.getMaXacNhan();
        LocalDateTime thoiGianHetHan = taikhoan.getThoigianhethan();
        if (thoiGianHetHan.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("Mã xác nhận hết hạn");
        }
        if (taikhoan.getMaxacnhan().equals(confirmEmailRequest.getMaXacNhan())) {
            taikhoan.setThoigianxacnhan(LocalDateTime.now());
            taiKhoanService.save(taikhoan);
            return "Xác nhận thành công";
        } else {
            return "Mã xác nhận không chính xác";
        }
    }

}
