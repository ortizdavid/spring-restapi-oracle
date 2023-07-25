package com.ortizdavid.restapi.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

public class Response {

    public static ResponseEntity<Map<String, Object>> build(String message, Object data, int count, HttpStatusCode statusCode) {
        Map<String, Object> response = new HashMap<String, Object>();
        response.put("status", statusCode.value());
        response.put("message", message);
        response.put("count", count);
        response.put("data", data);
        return new ResponseEntity<>(response, statusCode);
    }
    
}
