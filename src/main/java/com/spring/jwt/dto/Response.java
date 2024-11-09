package com.spring.jwt.dto;

import lombok.Data;


@Data
public class Response {
    private String message;
    private Object object;
     private Boolean hasError;

    public Response(String message, Object object, Boolean hasError)
    {
        this.message = message;
        this.object = object;
        this.hasError = hasError;
    }
}

