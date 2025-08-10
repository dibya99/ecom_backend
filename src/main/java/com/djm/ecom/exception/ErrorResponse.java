package com.djm.ecom.exception;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ErrorResponse {
    private int statusCode;
    private String message;
    private Map<String, String> errors;
}
