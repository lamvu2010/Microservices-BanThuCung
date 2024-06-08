package com.example.identity_service.Controller;
import com.example.identity_service.DTORequest.ConfirmEmailRequest;
import com.example.identity_service.DTORequest.RegisterRequest;
import com.example.identity_service.DTOResponse.TaiKhoanDTO;
import com.example.identity_service.Entity.Taikhoan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.identity_service.Service.QuenMatKhauService;
import com.example.identity_service.Service.TaiKhoanService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/identity/forget")
public class FPController {
    @Autowired
    private QuenMatKhauService quenMatKhauService;
    @Autowired
    private TaiKhoanService taiKhoanService;

    @GetMapping()
    public ResponseEntity<?> getMethodName() {
        return new ResponseEntity<>("test", HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> sendCode(@RequestBody String username) {
        if (username == null) {
            return new ResponseEntity<>("ten dang nhap null", HttpStatus.BAD_REQUEST);
        }
        try {
            String result = quenMatKhauService.sendCode(username);
            return new ResponseEntity<>(result, HttpStatus.OK);
            //lay ma code xac nhan de so trung trong chuong trinh client
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    //hoac dung ham cofirm de xac nhan ma
    @PostMapping("/confirm")
    public ResponseEntity<?> confirm(@RequestBody ConfirmEmailRequest confirmEmailRequest) {
        try {
            String result = quenMatKhauService.confirmEmail(confirmEmailRequest);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody TaiKhoanDTO taiKhoanDTO){
        if(!taiKhoanService.isExistsById(taiKhoanDTO.getTenDangNhap())){
            return new ResponseEntity<>("Tài khoản không tồn tại",HttpStatus.BAD_REQUEST);
        }
        Taikhoan taikhoan = taiKhoanService.findById(taiKhoanDTO.getTenDangNhap()).get();
        taikhoan.setMatkhau(taiKhoanDTO.getMatKhau());
        try{
            taikhoan = taiKhoanService.save(taikhoan);
            return new ResponseEntity<>("Cập nhật thành công", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Cập nhật thất bại", HttpStatus.BAD_REQUEST);
        }
    }
}
