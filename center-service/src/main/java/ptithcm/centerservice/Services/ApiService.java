package ptithcm.centerservice.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import ptithcm.centerservice.DTORequest.SoLuongSanPhamRequest;
import ptithcm.centerservice.DTORequest.SoLuongThuCungRequest;

@Service
public class ApiService {
    private final WebClient.Builder webClientBuilder;

    @Autowired
    public ApiService(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    // Định nghĩa các hàm gọi api đến service khác

    public String updateSoLuongTon(SoLuongSanPhamRequest soLuongSanPhamRequest){
        String result =  webClientBuilder
                .build()
                .put()
                .uri("http://center-service/center/sanpham/soluongton")
                .bodyValue(soLuongSanPhamRequest)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return result;
    }

    public String updateSoLuongTon(SoLuongThuCungRequest soLuongThuCungRequest){
        String result =  webClientBuilder
                .build()
                .put()
                .uri("http://center-service/center/thucung/soluongton")
                .bodyValue(soLuongThuCungRequest)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return result;
    }

}
