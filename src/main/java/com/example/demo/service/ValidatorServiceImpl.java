package com.example.demo.service;

import com.example.demo.enums.ValidationEnum;
import com.example.demo.exception.ValidationTypeException;
import com.example.demo.model.Point;
import com.example.demo.model.Rectangle;
import com.example.demo.resorce.CoreServiceResponse;
import com.example.demo.resorce.ValidationRequest;
import com.example.demo.resorce.ValidationResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ValidatorServiceImpl implements ValidatorService{

    @Override
    public CoreServiceResponse<String,ValidationResponse> invoke(ValidationRequest validationRequest){
        this.validate(validationRequest);
        switch (validationRequest.getValidationType()) {
            case "intersection":
                return intersection(validationRequest);
            case "containment":
                return containment(validationRequest);
            case "adjacent":
                return adjacent(validationRequest);
            default:
                throw new ValidationTypeException("Invalid validation type "+ validationRequest.getValidationType());
        }
    }

    @Override
    public CoreServiceResponse<String,ValidationResponse> intersection(ValidationRequest validationRequest) {

        Rectangle rOne = validationRequest.getRectangleOne();
        Rectangle rTwo = validationRequest.getRectangleTwo();

        CoreServiceResponse<String,ValidationResponse> containment = this.containment(validationRequest);
        if(containment != null && containment.getResponseObject() != null){
            if(StringUtils.pathEquals(ValidationEnum.CONTAINMENT.name(),containment.getResponseObject().getMessage())){
                return new CoreServiceResponse<>(validationRequest.toString(),
                        ValidationResponse.builder()
                                .message(ValidationEnum.NOT_INTERSECTION.name())
                                .build());
            }
        }

        int xL = Math.max(rOne.getTop_left().getX(),  rTwo.getTop_left().getX());
        int xR = Math.min(rOne.getTop_left().getX() + rOne.getWidth(), rTwo.getTop_left().getX() + rTwo.getWidth());
        if (xL >= xR ){
            return new CoreServiceResponse<>(validationRequest.toString(),
                    ValidationResponse.builder()
                            .message(ValidationEnum.NOT_INTERSECTION.name())
                            .build());
        }else {
            int yT = Math.min(rTwo.getTop_left().getY(), rTwo.getTop_left().getY());
            int yB = Math.max(rOne.getTop_left().getY() - rOne.getHeight(), rTwo.getTop_left().getY() - rTwo.getHeight());
            if (yT <= yB ){
                return new CoreServiceResponse<>(validationRequest.toString(),
                        ValidationResponse.builder()
                                .message(ValidationEnum.NOT_INTERSECTION.name())
                                .build());
            }else {
                //new Rectangle(new Point(xL, yT), xR - xL, yT - yB);
                return new CoreServiceResponse<>(validationRequest.toString(),
                        ValidationResponse.builder()
                                .message(ValidationEnum.INTERSECTION.name())
                                .rectangle(Rectangle.builder()
                                        .top_left(Point.builder()
                                                .x(xL)
                                                .y(yT)
                                                .build())
                                        .width(xR - xL)
                                        .height(yT - yB)
                                        .build())
                                .build());
            }
        }
    }


    @Override
    public CoreServiceResponse<String,ValidationResponse> containment(ValidationRequest validationRequest) {

        Rectangle rOne = validationRequest.getRectangleOne();
        Rectangle rTwo = validationRequest.getRectangleTwo();
        boolean validate = rOne.hasContainment(rTwo) || rTwo.hasContainment(rOne);
        if(validate){
            return new CoreServiceResponse<>(validationRequest.toString(),
                    ValidationResponse.builder()
                            .message(ValidationEnum.CONTAINMENT.name())
                            .build());
        }else{
            return new CoreServiceResponse<>(validationRequest.toString(),
                    ValidationResponse.builder()
                            .message(ValidationEnum.NOT_CONTAINMENT.name())
                            .build());
        }
    }

    @Override
    public CoreServiceResponse<String,ValidationResponse> adjacent(ValidationRequest validationRequest) {

        Rectangle rOne = validationRequest.getRectangleOne();
        Rectangle rTwo = validationRequest.getRectangleTwo();

        int xMax = Math.max(rOne.getTop_left().getX() + rOne.getWidth(),  rTwo.getTop_left().getX());
        int xMin = Math.min(rOne.getTop_left().getX() + rOne.getWidth(),  rTwo.getTop_left().getX());
        if(xMax == xMin){
            if(rOne.hasYSubline(rTwo) || rTwo.hasYSubline(rOne)){
                return new CoreServiceResponse<>(validationRequest.toString(),
                        ValidationResponse.builder()
                                .message(ValidationEnum.ADJACENT_SUB_LINE.name())
                                .build());
            }
            if(rOne.hasYProper(rTwo) || rTwo.hasYProper(rOne)){
                return new CoreServiceResponse<>(validationRequest.toString(),
                        ValidationResponse.builder()
                                .message(ValidationEnum.ADJACENT_PROPER.name())
                                .build());
            }
            if(rOne.hasYPartial(rTwo)){
                return new CoreServiceResponse<>(validationRequest.toString(),
                        ValidationResponse.builder()
                                .message(ValidationEnum.ADJACENT_PARTIAL.name())
                                .build());
            }
        }else{
            int yMax = Math.max(rOne.getTop_left().getY() ,  rTwo.getTop_left().getY() - rTwo.getHeight());
            int yMin = Math.min(rOne.getTop_left().getY() ,  rTwo.getTop_left().getY() - rTwo.getHeight());
            if(yMax == yMin){
                if(rOne.hasXSubline(rTwo) || rTwo.hasXSubline(rOne)){
                    return new CoreServiceResponse<>(validationRequest.toString(),
                            ValidationResponse.builder()
                                    .message(ValidationEnum.ADJACENT_SUB_LINE.name())
                                    .build());
                }
                if(rOne.hasXProper(rTwo) || rTwo.hasXProper(rOne)){
                    return new CoreServiceResponse<>(validationRequest.toString(),
                            ValidationResponse.builder()
                                    .message(ValidationEnum.ADJACENT_PROPER.name())
                                    .build());
                }
                if(rOne.hasXPartial(rTwo) || rTwo.hasYProper(rOne)){
                    return new CoreServiceResponse<>(validationRequest.toString(),
                            ValidationResponse.builder()
                                    .message(ValidationEnum.ADJACENT_PARTIAL.name())
                                    .build());
                }
            }
        }
        return new CoreServiceResponse<>(validationRequest.toString(),
                ValidationResponse.builder()
                        .message(ValidationEnum.NOT_ADJACENT.name())
                        .build());
    }

    private void validate(ValidationRequest validationRequest){
        if (validateNulls(validationRequest)){
            throw new ValidationTypeException("Null values "+ validationRequest.toString());
        }
        Rectangle rOne = validationRequest.getRectangleOne();
        Rectangle rTwo = validationRequest.getRectangleTwo();
        if(rOne.equals(rTwo)){
            throw new ValidationTypeException("Rectangles are equals "+ validationRequest.toString());
        }
    }

    private boolean validateNulls(ValidationRequest rectanglesTuples) {
        if (rectanglesTuples == null || rectanglesTuples.getRectangleOne() == null || rectanglesTuples.getRectangleTwo() == null){
            return true;
        }
        return false;
    }
}
