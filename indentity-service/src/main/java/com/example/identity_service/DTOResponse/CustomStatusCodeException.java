package com.example.identity_service.DTOResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

public class CustomStatusCodeException extends HttpStatusCodeException {
    protected CustomStatusCodeException(HttpStatus statusCode) {
        super(statusCode);
    }

    public static CustomStatusCodeException createCustomStatus(int statusCode, String statusText) {
        return new CustomStatusCodeException(HttpStatus.valueOf(statusCode)) {
            @Override
            public String getStatusText() {
                return statusText;
            }
        };
    }
}