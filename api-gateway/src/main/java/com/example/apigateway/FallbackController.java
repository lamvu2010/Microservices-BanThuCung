package com.example.apigateway;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class FallbackController {
    // request fall back
    @RequestMapping("/orderFallback")
    public Mono<String> orderServiceFallback(){
        return Mono.just("Order service chiếm nhiều thời gian phản hồi hoặc đã tắt");
    }

    @RequestMapping("/centerFallback")
    public Mono<String> centerServiceFallback(){
        return Mono.just("Center service chiếm nhiều thời gian phản hồi hoặc đã tắt");
    }
}
