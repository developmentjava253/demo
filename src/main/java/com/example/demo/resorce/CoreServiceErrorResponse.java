package com.example.demo.resorce;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CoreServiceErrorResponse {
    private String errorMessage;
    private String errorCode;
}
