package com.example.demo.resorce;

import com.example.demo.model.Rectangle;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ValidationResponse implements Serializable {
    private String message;
    private Rectangle rectangle;
}
