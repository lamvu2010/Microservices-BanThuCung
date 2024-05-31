package com.example.identity_service.Controller;

import com.example.identity_service.DTORequest.LoginDTO;
import com.example.identity_service.DTOResponse.LoginResponse;
import com.example.identity_service.Entity.Taikhoan;
import com.example.identity_service.Security.Config.JWT.JwtService;
import com.example.identity_service.Service.TaiKhoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/identity/login")
public class LoginController {

    @Autowired
    private TaiKhoanService taiKhoanService;

    @Autowired
    private JwtService jwtService;

    @PostMapping
    public ResponseEntity<?> checkLogin(@RequestBody LoginDTO loginDTO) {
        if (loginDTO.getTenDangNhap() == null || loginDTO.getMatKhau() == null) {
            return new ResponseEntity<>("Vui lòng điền đầy đủ thông tin", HttpStatus.BAD_REQUEST);
        }
        Taikhoan taikhoan = taiKhoanService.findBytendangnhap(loginDTO.getTenDangNhap());
        if(taikhoan==null){
            return new ResponseEntity<>("Tài khoản không tồn tại",HttpStatus.NOT_FOUND);
        }
        if(taikhoan.getTrangthai()==Boolean.FALSE || taikhoan.getTrangthai() == null){
            return new ResponseEntity<>("Tài khoản bị khóa!",HttpStatus.BAD_REQUEST);
        }
        if(taikhoan.getMatkhau().equals(loginDTO.getMatKhau())){
            String role = taikhoan.getQuyen();
            String token = jwtService.createToken(new HashMap<>(),taikhoan.getTendangnhap(),role);
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setTenDangNhap(taikhoan.getTendangnhap());
            loginResponse.setQuyen(role);
            loginResponse.setToken(token);
            return new ResponseEntity<>(loginResponse, HttpStatus.OK); 
        }else{
            return new ResponseEntity<>("Mật khẩu không đúng!",HttpStatus.BAD_REQUEST);
        }
    }
}
