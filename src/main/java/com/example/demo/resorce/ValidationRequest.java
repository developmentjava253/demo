package com.example.demo.resorce;

import com.example.demo.model.Rectangle;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@ApiModel(description = "Request with two rectangles and the validation type.")
public class ValidationRequest {

    @NotNull(message = "Validation type is require.")
    @ApiModelProperty(notes = "Validation type, it is require and the accepted values are intersection, adjacent and containment.")
    private String validationType;
    @NotNull(message = "Rectangle One is require.")
    @Valid
    @ApiModelProperty(notes = "Rectangle, it is require.")
    private Rectangle rectangleOne;
    @NotNull(message = "Rectangle Two is require.")
    @Valid
    @ApiModelProperty(notes = "Rectangle, it is require.")
    private Rectangle rectangleTwo;

}
