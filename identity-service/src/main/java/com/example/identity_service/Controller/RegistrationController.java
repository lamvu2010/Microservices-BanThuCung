package com.example.identity_service.Controller;

import com.example.identity_service.Service.TaoTaiKhoanService;
import com.example.identity_service.DTORequest.ConfirmEmailRequest;
import com.example.identity_service.DTORequest.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/identity/register")
public class RegistrationController {
    @Autowired
    private TaoTaiKhoanService taoTaiKhoanService;

    @PostMapping
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        if (registerRequest.getTenDangNhap() == null) {
            return new ResponseEntity<>("ten dang nhap null", HttpStatus.OK);
        }
        try {
            String result = taoTaiKhoanService.register(registerRequest);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/confirm")
    public ResponseEntity<?> confirm(@RequestBody ConfirmEmailRequest confirmEmailRequest) {
        try {
            String result = taoTaiKhoanService.confirmEmail(confirmEmailRequest);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/getVerifiedCode")
    public ResponseEntity<?> getAgain(@RequestBody RegisterRequest registerRequest){
        try {
            String result = taoTaiKhoanService.getVerifiedCodeAgain(registerRequest);
            return  new ResponseEntity<>(result,HttpStatus.OK);
        }catch (IllegalStateException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
