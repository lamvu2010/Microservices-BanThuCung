package com.example.identity_service.DTOResponse;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;

public class CodeMapping {
    private static Map<Integer,String> codeStatus= new HashMap();
    static{
        codeStatus.put(HttpStatus.OK.value(), "OK");
        codeStatus.put(HttpStatus.CREATED.value(), "Created");
        codeStatus.put(HttpStatus.BAD_REQUEST.value(), "Bad Request");
        codeStatus.put(HttpStatus.NOT_FOUND.value(), "Not Found");
        codeStatus.put(608, "Chuoxoi oi");
    }
}
