package com.example.identity_service.Service;

import com.example.identity_service.DTORequest.ConfirmEmailRequest;
import com.example.identity_service.Entity.Taikhoan;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class QuenMatKhauService {
    @Autowired
    private TaiKhoanService taiKhoanService;

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
//        try {
//            emailService.send(khachhang.getEmail(), "MÃ XÁC NHẬN", taikhoan.getMaxacnhan());
//        } catch (MessagingException e) {
//            return "Gửi mail thất bại!";
//        }
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
        if (taikhoan.getThoigianxacnhan() != null) {
            throw new IllegalStateException("Tài khoản đã được xác nhận");
        }
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
