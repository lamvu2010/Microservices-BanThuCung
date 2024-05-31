package com.example.apigateway.Security.Config;

import com.example.apigateway.Security.Config.JWT.JwtService;
import com.example.apigateway.Security.Config.JWT.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class NhanVienFilter extends AbstractGatewayFilterFactory<NhanVienFilter.Config> {

    @Autowired
    private JwtService jwtService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private RouteValidator validator;
    public NhanVienFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            if (validator.isSecured.test(exchange.getRequest())) {
                //header contains token or not
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new RuntimeException("Missing authorization header");
                }
                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7);
                }
                try {
                    jwtUtil.validateToken(authHeader);
                    String role = jwtService.getRole(authHeader);
                    if (!role.equals("nhanvien")){
                        System.out.println("Tài khoản không có quyền nhân viên");
                        throw new RuntimeException("Un authorized access NV");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid access...!");
                    throw new RuntimeException("Un authorized access to application");
                }
            }
            return chain.filter(exchange);
        });
    }
    public static class Config{
    }
}
