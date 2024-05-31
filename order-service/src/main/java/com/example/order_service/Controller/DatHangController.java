package com.example.order_service.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dat-hang")
public class DatHangController {

    @GetMapping
    public String dathang(){
        return "chay dc";
    }
}