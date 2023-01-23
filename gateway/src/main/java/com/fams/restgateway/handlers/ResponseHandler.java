package com.fams.restgateway.handlers;

import com.fams.core.dtos.response.ObjectWrapperResponse;
import com.fams.core.dtos.response.WrapperResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.LinkedHashMap;
import java.util.TreeMap;

@RestControllerAdvice
public class ResponseHandler implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof ObjectWrapperResponse) return body;
        if (body instanceof WrapperResponse) return body;
        if (body instanceof String && ((String) body).contains("openapi")) return body;
        if (body instanceof TreeMap) return body;
        if (body instanceof LinkedHashMap) return body;
        ObjectWrapperResponse responseDTO = new ObjectWrapperResponse();
        responseDTO.setData(body);
        responseDTO.setMessage(null);
        response.getHeaders().add("Content-Type", "application/json");
        return responseDTO;
    }
}
