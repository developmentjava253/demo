package com.example.demo;


import com.example.demo.enums.ValidationEnum;
import com.example.demo.exception.ValidationTypeException;
import com.example.demo.model.Point;
import com.example.demo.model.Rectangle;
import com.example.demo.resorce.CoreServiceResponse;
import com.example.demo.resorce.ValidationRequest;
import com.example.demo.resorce.ValidationResponse;
import com.example.demo.service.ValidatorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class ValidatorServiceTest {

    @Autowired
    private ValidatorService validatorService;

    private ValidationRequest intersectedRectangles;
    private ValidationRequest independents;
    private ValidationRequest equalRectangles;
    private ValidationRequest containment;
    private ValidationRequest leftInsideAdjacentSubLine;
    private ValidationRequest rightInsideAdjacentSubLine;
    private ValidationRequest topInsideAdjacentSubLine;
    private ValidationRequest bottomInsideAdjacentSubLine;
    private ValidationRequest adjacentSubLine_y;
    private ValidationRequest adjacentProper_y;
    private ValidationRequest adjacentPartial_y;
    private ValidationRequest adjacentSubLine_x;
    private ValidationRequest adjacentProper_x;
    private ValidationRequest adjacentPartial_x;
    private ValidationRequest notAdjacentPartial;
    private Rectangle rectangle;

    @Test
    public void shouldValidateIntersectionSuccess() {
        CoreServiceResponse<String,ValidationResponse> response = validatorService.invoke(intersectedRectangles);
        Assertions.assertNotNull(response.getResponseObject());
        Assertions.assertEquals(ValidationEnum.INTERSECTION.name(),response.getResponseObject().getMessage());
        Assertions.assertNotNull(response.getResponseObject().getRectangle());
        Assertions.assertEquals(rectangle.getTop_left(),response.getResponseObject().getRectangle().getTop_left());
        Assertions.assertEquals(rectangle.getWidth(),response.getResponseObject().getRectangle().getWidth());
        Assertions.assertEquals(rectangle.getHeight(),response.getResponseObject().getRectangle().getHeight());
    }

    @Test
    public void shouldValidateIntersection_Return_NOT_INTERSECTION_On_Independents() {
        CoreServiceResponse<String,ValidationResponse> response = validatorService.invoke(independents);
        Assertions.assertNotNull(response.getResponseObject());
        Assertions.assertEquals(ValidationEnum.NOT_INTERSECTION.name(),response.getResponseObject().getMessage());
    }


    @Test
    public void shouldValidateInvoke_throw_ValidationTypeException_On_Null() {
        intersectedRectangles.setRectangleTwo(null);
        try{
            validatorService.invoke(intersectedRectangles);
        }catch (RuntimeException e){
            Assertions.assertTrue(e instanceof ValidationTypeException);
        }
    }

    @Test
    public void shouldValidateInvoke_throw_ValidationTypeException_On_Equals() {
        intersectedRectangles.setRectangleTwo(null);
        try{
            validatorService.invoke(equalRectangles);
        }catch (RuntimeException e){
            Assertions.assertTrue(e instanceof ValidationTypeException);
        }
    }

    @Test
    public void shouldValidateIntersection_Return_NOT_INTERSECTION_On_Containment() {
        containment.setValidationType("intersection");
        CoreServiceResponse<String,ValidationResponse> response = validatorService.invoke(containment);
        Assertions.assertNotNull(response.getResponseObject());
        Assertions.assertEquals(ValidationEnum.NOT_INTERSECTION.name(),response.getResponseObject().getMessage());
    }

    @Test
    public void shouldValidateIntersection_Return_NOT_INTERSECTION_On_Adjacent() {
        adjacentSubLine_y.setValidationType("intersection");
        CoreServiceResponse<String,ValidationResponse> response = validatorService.invoke(adjacentSubLine_y);
        Assertions.assertNotNull(response.getResponseObject());
        Assertions.assertEquals(ValidationEnum.NOT_INTERSECTION.name(),response.getResponseObject().getMessage());
    }

    @Test
    public void shouldValidateContainment_Success() {
        CoreServiceResponse<String,ValidationResponse> response = validatorService.invoke(containment);
        Assertions.assertNotNull(response.getResponseObject());
        Assertions.assertEquals(ValidationEnum.CONTAINMENT.name(),response.getResponseObject().getMessage());
    }

    @Test
    public void shouldValidateContainment_Return_NOT_CONTAINMENT_On_Independents() {
        independents.setValidationType("containment");
        CoreServiceResponse<String,ValidationResponse> response = validatorService.invoke(independents);
        Assertions.assertNotNull(response.getResponseObject());
        Assertions.assertEquals(ValidationEnum.NOT_CONTAINMENT.name(),response.getResponseObject().getMessage());
    }

    @Test
    public void shouldValidateContainment_Return_NOT_CONTAINMENT_On_Left_Inside_Adjacent_Sub_line() {
        leftInsideAdjacentSubLine.setValidationType("containment");
        CoreServiceResponse<String,ValidationResponse> response = validatorService.invoke(leftInsideAdjacentSubLine);
        Assertions.assertNotNull(response.getResponseObject());
        Assertions.assertEquals(ValidationEnum.NOT_CONTAINMENT.name(),response.getResponseObject().getMessage());
    }

    @Test
    public void shouldValidateContainment_Return_NOT_CONTAINMENT_On_Right_Inside_Adjacent_Sub_line() {
        rightInsideAdjacentSubLine.setValidationType("containment");
        CoreServiceResponse<String,ValidationResponse> response = validatorService.invoke(rightInsideAdjacentSubLine);
        Assertions.assertNotNull(response.getResponseObject());
        Assertions.assertEquals(ValidationEnum.NOT_CONTAINMENT.name(),response.getResponseObject().getMessage());
    }

    @Test
    public void shouldValidateContainment_Return_NOT_CONTAINMENT_On_Top_Inside_Adjacent_Sub_line() {
        topInsideAdjacentSubLine.setValidationType("containment");
        CoreServiceResponse<String,ValidationResponse> response = validatorService.invoke(topInsideAdjacentSubLine);
        Assertions.assertNotNull(response.getResponseObject());
        Assertions.assertEquals(ValidationEnum.NOT_CONTAINMENT.name(),response.getResponseObject().getMessage());
    }

    @Test
    public void shouldValidateContainment_Return_NOT_CONTAINMENT_On_Bottom_Inside_Adjacent_Sub_line() {
        bottomInsideAdjacentSubLine.setValidationType("containment");
        CoreServiceResponse<String,ValidationResponse> response = validatorService.invoke(bottomInsideAdjacentSubLine);
        Assertions.assertNotNull(response.getResponseObject());
        Assertions.assertEquals(ValidationEnum.NOT_CONTAINMENT.name(),response.getResponseObject().getMessage());
    }

    @Test
    public void shouldValidateAdjacentSubLine_Y_Success() {
        adjacentSubLine_y.setValidationType("adjacent");
        CoreServiceResponse<String,ValidationResponse> response = validatorService.invoke(adjacentSubLine_y);
        Assertions.assertNotNull(response.getResponseObject());
        Assertions.assertEquals(ValidationEnum.ADJACENT_SUB_LINE.name(),response.getResponseObject().getMessage());
    }

    @Test
    public void shouldValidateAdjacentProper_Y_Success() {
        CoreServiceResponse<String,ValidationResponse> response = validatorService.invoke(adjacentProper_y);
        Assertions.assertNotNull(response.getResponseObject());
        Assertions.assertEquals(ValidationEnum.ADJACENT_PROPER.name(),response.getResponseObject().getMessage());
    }

    @Test
    public void shouldValidateAdjacentPartial_Y_Success() {
        CoreServiceResponse<String,ValidationResponse> response = validatorService.invoke(adjacentPartial_y);
        Assertions.assertNotNull(response.getResponseObject());
        Assertions.assertEquals(ValidationEnum.ADJACENT_PARTIAL.name(),response.getResponseObject().getMessage());
    }

    @Test
    public void shouldValidateAdjacentSubLine_X_Success() {
        CoreServiceResponse<String,ValidationResponse> response = validatorService.invoke(adjacentSubLine_x);
        Assertions.assertNotNull(response.getResponseObject());
        Assertions.assertEquals(ValidationEnum.ADJACENT_SUB_LINE.name(),response.getResponseObject().getMessage());
    }

    @Test
    public void shouldValidateAdjacentProper_X_Success() {
        CoreServiceResponse<String,ValidationResponse> response = validatorService.invoke(adjacentProper_x);
        Assertions.assertNotNull(response.getResponseObject());
        Assertions.assertEquals(ValidationEnum.ADJACENT_PROPER.name(),response.getResponseObject().getMessage());
    }

    @Test
    public void shouldValidateAdjacentPartial_X_Success() {
        CoreServiceResponse<String,ValidationResponse> response = validatorService.invoke(adjacentPartial_x);
        Assertions.assertNotNull(response.getResponseObject());
        Assertions.assertEquals(ValidationEnum.ADJACENT_PARTIAL.name(),response.getResponseObject().getMessage());
    }

    @Test
    public void shouldValidateNotAdjacentPartial_Success() {
        CoreServiceResponse<String,ValidationResponse> response = validatorService.invoke(notAdjacentPartial);
        Assertions.assertNotNull(response.getResponseObject());
        Assertions.assertEquals(ValidationEnum.NOT_ADJACENT.name(),response.getResponseObject().getMessage());
    }

    @BeforeAll
    public void init(){
        intersectedRectangles = ValidationRequest.builder()
                .validationType("intersection")
                .rectangleOne(Rectangle.builder()
                        .top_left(Point.builder()
                                .x(1)
                                .y(4)
                                .build())
                        .width(3)
                        .height(2)
                        .build())
                .rectangleTwo( Rectangle.builder()
                        .top_left(Point.builder()
                                .x(2)
                                .y(3)
                                .build())
                        .width(3)
                        .height(2)
                        .build())
                .build();

        rectangle = Rectangle.builder()
                .top_left(Point.builder()
                        .x(2)
                        .y(3)
                        .build())
                .width(2)
                .height(1)
                .build();

        independents = ValidationRequest.builder()
                .validationType("intersection")
                .rectangleOne(Rectangle.builder()
                        .top_left(Point.builder()
                                .x(1)
                                .y(3)
                                .build())
                        .width(3).height(2).build())
                .rectangleTwo( Rectangle.builder()
                        .top_left(Point.builder()
                                .x(6)
                                .y(3)
                                .build())
                        .width(3)
                        .height(3)
                        .build())
                .build();

        equalRectangles = ValidationRequest.builder()
                .rectangleOne(Rectangle.builder()
                        .top_left(Point.builder()
                                .x(1)
                                .y(3)
                                .build())
                        .width(3)
                        .height(2)
                        .build())
                .rectangleTwo( Rectangle.builder()
                        .top_left(Point.builder()
                                .x(1)
                                .y(3)
                                .build())
                        .width(3)
                        .height(2)
                        .build())
                .build();

        containment = ValidationRequest.builder()
                .validationType("containment")
                .rectangleOne(Rectangle.builder()
                        .top_left(Point.builder()
                                .x(1)
                                .y(4)
                                .build())
                        .width(4)
                        .height(4)
                        .build())
                .rectangleTwo( Rectangle.builder()
                        .top_left(Point.builder()
                                .x(2)
                                .y(3)
                                .build())
                        .width(2)
                        .height(2)
                        .build())
                .build();

        leftInsideAdjacentSubLine = ValidationRequest.builder()
                .rectangleOne(Rectangle.builder()
                        .top_left(Point.builder()
                                .x(1)
                                .y(4)
                                .build())
                        .width(4)
                        .height(4)
                        .build())
                .rectangleTwo( Rectangle.builder()
                        .top_left(Point.builder()
                                .x(1)
                                .y(3)
                                .build())
                        .width(2)
                        .height(2)
                        .build())
                .build();

        rightInsideAdjacentSubLine = ValidationRequest.builder()
                .rectangleOne(Rectangle.builder()
                        .top_left(Point.builder()
                                .x(1)
                                .y(4)
                                .build())
                        .width(4)
                        .height(4)
                        .build())
                .rectangleTwo( Rectangle.builder()
                        .top_left(Point.builder()
                                .x(2)
                                .y(3)
                                .build())
                        .width(3)
                        .height(2)
                        .build())
                .build();

        topInsideAdjacentSubLine = ValidationRequest.builder()
                .rectangleOne(Rectangle.builder()
                        .top_left(Point.builder()
                                .x(1)
                                .y(4)
                                .build())
                        .width(4)
                        .height(4)
                        .build())
                .rectangleTwo( Rectangle.builder()
                        .top_left(Point.builder()
                                .x(2)
                                .y(4)
                                .build())
                        .width(2)
                        .height(2)
                        .build())
                .build();

        bottomInsideAdjacentSubLine = ValidationRequest.builder()
                .rectangleOne(Rectangle.builder()
                        .top_left(Point.builder()
                                .x(1)
                                .y(4)
                                .build())
                        .width(4)
                        .height(4)
                        .build())
                .rectangleTwo( Rectangle.builder()
                        .top_left(Point.builder()
                                .x(2)
                                .y(3)
                                .build())
                        .width(2)
                        .height(3)
                        .build())
                .build();

        adjacentSubLine_y = ValidationRequest.builder()
                .validationType("adjacent")
                .rectangleOne(Rectangle.builder()
                        .top_left(Point.builder()
                                .x(1)
                                .y(4)
                                .build())
                        .width(3)
                        .height(3)
                        .build())
                .rectangleTwo( Rectangle.builder()
                        .top_left(Point.builder()
                                .x(4)
                                .y(3)
                                .build())
                        .width(2)
                        .height(1)
                        .build())
                .build();

        adjacentProper_y =  ValidationRequest.builder()
                .validationType("adjacent")
                .rectangleOne(Rectangle.builder()
                        .top_left(Point.builder()
                                .x(1)
                                .y(4)
                                .build())
                        .width(3)
                        .height(3)
                        .build())
                .rectangleTwo( Rectangle.builder()
                        .top_left(Point.builder()
                                .x(4)
                                .y(4)
                                .build())
                        .width(3)
                        .height(3)
                        .build())
                .build();

        adjacentPartial_y = ValidationRequest.builder()
                .validationType("adjacent")
                .rectangleOne(Rectangle.builder()
                        .top_left(Point.builder()
                                .x(1)
                                .y(4)
                                .build())
                        .width(3)
                        .height(3)
                        .build())
                .rectangleTwo( Rectangle.builder()
                        .top_left(Point.builder()
                                .x(4)
                                .y(5)
                                .build())
                        .width(3)
                        .height(3)
                        .build())
                .build();

        adjacentSubLine_x = ValidationRequest.builder()
                .validationType("adjacent")
                .rectangleOne(Rectangle.builder()
                        .top_left(Point.builder()
                                .x(1)
                                .y(3)
                                .build())
                        .width(3)
                        .height(2)
                        .build())
                .rectangleTwo( Rectangle.builder()
                        .top_left(Point.builder()
                                .x(2)
                                .y(4)
                                .build())
                        .width(1)
                        .height(1)
                        .build())
                .build();

        adjacentProper_x = ValidationRequest.builder()
                .validationType("adjacent")
                .rectangleOne(Rectangle.builder()
                        .top_left(Point.builder()
                                .x(1)
                                .y(3)
                                .build())
                        .width(3)
                        .height(2)
                        .build())
                .rectangleTwo( Rectangle.builder()
                        .top_left(Point.builder()
                                .x(1)
                                .y(4)
                                .build())
                        .width(3)
                        .height(1)
                        .build())
                .build();
        adjacentPartial_x = ValidationRequest.builder()
                .validationType("adjacent")
                .rectangleOne(Rectangle.builder()
                        .top_left(Point.builder()
                                .x(1)
                                .y(3)
                                .build())
                        .width(2)
                        .height(2)
                        .build())
                .rectangleTwo( Rectangle.builder()
                        .top_left(Point.builder()
                                .x(2)
                                .y(4)
                                .build())
                        .width(2)
                        .height(1)
                        .build())
                .build();

        notAdjacentPartial = ValidationRequest.builder()
                .validationType("adjacent")
                .rectangleOne(Rectangle.builder()
                        .top_left(Point.builder()
                                .x(1)
                                .y(2)
                                .build())
                        .width(1)
                        .height(2)
                        .build())
                .rectangleTwo( Rectangle.builder()
                        .top_left(Point.builder()
                                .x(2)
                                .y(4)
                                .build())
                        .width(1)
                        .height(1)
                        .build())
                .build();

    }
}
