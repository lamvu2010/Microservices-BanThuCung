package com.example.apigateway.Security.Config;

import com.example.apigateway.Security.Config.JWT.JwtService;
import com.example.apigateway.Security.Config.JWT.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private RouteValidator validator;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthorizationConfig authorizationConfig;

    public AuthenticationFilter() {
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
                    String path = exchange.getRequest().getURI().getPath();
                    String method = exchange.getRequest().getMethod().toString();
                    Optional<AuthorizationConfig.AuthorizationRule> ruleOpt = authorizationConfig.getRules()
                            .stream()
                            .filter(rule -> path.matches(rule.getPath().replace("**", ".*")) && rule.getMethods().contains(method))
                            .findFirst();
                    if (ruleOpt.isPresent()) {
                        List<String> allowedRoles = ruleOpt.get().getRoles();
                        if (!allowedRoles.contains(role)) {
                            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access denied");
                        } else {
                            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access denied");
                        }
                    } else {
                        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid token");
                    }
                } catch (Exception e) {
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized access", e);
                }
            }
            return chain.filter(exchange);
        });
    }

    public static class Config {
    }
}
