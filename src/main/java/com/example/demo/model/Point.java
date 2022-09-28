package com.example.demo.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.util.Objects;

@Getter
@Builder
@AllArgsConstructor
@ApiModel(description = "Represents a point. The parameters of the constructor are: x, y.")
public class Point {

    @NotNull(message = "X is mandatory")
    @ApiModelProperty(notes = "The x, it is require.")
    private final Integer  x;
    @NotNull(message = "The Y is mandatory")
    @ApiModelProperty(notes = "The y, it is require.")
    private final Integer  y;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x && y == point.y;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
