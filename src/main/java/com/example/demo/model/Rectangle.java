package com.example.demo.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@ToString
@Getter
@Builder
@AllArgsConstructor
@ApiModel(description = "Represents a rectangle. The parameters of the constructor are: x, y, width, height, in which x, y are the top-left corner of the rectangle")
public class Rectangle {

    @NotNull(message = "Top Left Point is mandatory")
    @Valid
    @ApiModelProperty(notes = "The top left corner point, is mandatory")
    private final Point top_left;
    @NotNull(message = "With is mandatory")
    @ApiModelProperty(notes = "The width, it is require.")
    private final Integer width;
    @NotNull(message = "Height is mandatory")
    @ApiModelProperty(notes = "The height, it is require.")
    private final Integer height;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return Objects.equals(this.width,rectangle.width) && Objects.equals(height, rectangle.height) && Objects.equals(top_left, rectangle.top_left);
    }

    public boolean hasContainment(Rectangle o){
        if(o == null ){
            return false;
        }

        if(this.getTop_left().equals(o.getTop_left())){
            return false;
        }

        if(this.getTop_left().getX() < o.getTop_left().getX() && this.getTop_left().getY() > o.getTop_left().getY()){
            if(this.getTop_left().getX() + this.getWidth() > o.getTop_left().getX() + o.getWidth()
            && this.getTop_left().getY() - this.getHeight() < o.getTop_left().getY() - o.getHeight()){
                return true;
            }
        }
        return false;
    }

    public boolean hasYSubline(Rectangle o){
        if(o == null ){
            return false;
        }
        if(this.getTop_left().getY() > o.getTop_left().getY()){
            return this.getTop_left().getY() - this.getHeight() < o.getTop_left().getY() - o.getHeight();
        }
        return false;
    }

    public boolean hasXSubline(Rectangle o){
        if(o == null ){
            return false;
        }
        if(this.getTop_left().getX() < o.getTop_left().getX()){
            return this.getTop_left().getX() + this.getWidth() > o.getTop_left().getX() + o.getWidth();
        }
        return false;
    }

    public boolean hasYProper(Rectangle o){
        if(o == null ){
            return false;
        }
        if(this.getTop_left().getY() == o.getTop_left().getY()){
            return this.getHeight() == o.getHeight();
        }
        return false;
    }

    public boolean hasXProper(Rectangle o){
        if(o == null ){
            return false;
        }
        if(this.getTop_left().getX() == o.getTop_left().getX()){
            return  this.getWidth() == o.getWidth();
        }
        return false;
    }

    public boolean hasYPartial(Rectangle o){
        if(o == null ){
            return false;
        }
        else if(this.hasYSubline(o)||this.hasYProper(o)){
            return false;
        }else{
            boolean topPartial = this.getTop_left().getY() < o.getTop_left().getY() &&
                    this.getTop_left().getY() > o.getTop_left().getY() - o.getHeight() &&
                    this.getTop_left().getY() - this.getHeight() < o.getTop_left().getY() - o.getHeight();
            boolean bottomPartial = this.getTop_left().getY() > o.getTop_left().getY() &&
                    this.getTop_left().getY() - this.getHeight() < o.getTop_left().getY() &&
                    this.getTop_left().getY() - this.getHeight() > o.getTop_left().getY() - o.getHeight();
            if(topPartial || bottomPartial ){
                return true;
            }
        }
        return false;
    }

    public boolean hasXPartial(Rectangle o){
        if(o == null ){
            return false;
        }
        else if(this.hasXSubline(o)||this.hasXProper(o)){
            return false;
        }else{
            boolean leftPartial = this.getTop_left().getX() > o.getTop_left().getX() &&
                    this.getTop_left().getX() < o.getTop_left().getX() + o.getWidth() &&
                    this.getTop_left().getX() + this.getWidth() > o.getTop_left().getX() + o.getWidth();
                    ;
            boolean rightPartial = this.getTop_left().getX() < o.getTop_left().getX() &&
                    this.getTop_left().getX() + this.getWidth() > o.getTop_left().getX() &&
                    this.getTop_left().getX() + this.getWidth() < o.getTop_left().getX() + o.getWidth();
            if(leftPartial || rightPartial ){
                return true;
            }
        }
        return false;
    }
}
