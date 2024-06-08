package ptithcm.centerservice.Configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:5173") // Thay đổi domain của bạn tại đây
                .allowedMethods("GET","POST") // Các phương thức HTTP được phép
                .allowCredentials(true) // Cho phép gửi cookie qua CORS
                .maxAge(3600); // Cache cấu hình CORS trong 1 giờ
    }
}
