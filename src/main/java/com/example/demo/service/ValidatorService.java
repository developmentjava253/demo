package com.example.demo.service;

import com.example.demo.resorce.CoreServiceResponse;
import com.example.demo.resorce.ValidationRequest;
import com.example.demo.resorce.ValidationResponse;

public interface ValidatorService {
    /**
     *
     * @param validationRequest
     * @return
     */
    CoreServiceResponse<String, ValidationResponse> invoke(ValidationRequest validationRequest);

    /**
     * Determine whether two rectangles have one or more intersecting lines and produce a result identifying the points of intersection.
     * @param validationRequest
     * @return  Intersection, Not intersection
     */
    CoreServiceResponse<String, ValidationResponse> intersection(ValidationRequest validationRequest);

    /**
     * Determine whether a rectangle is wholly contained within another rectangle.
     * @param validationRequest
     * @return  Containment, Not containment
     */
    CoreServiceResponse<String,ValidationResponse> containment(ValidationRequest validationRequest);

    /**
     * Determine whether two rectangles are adjacent.
     * @param validationRequest
     * @return Adjacent, Not adjacent, Adjacent (Sub-line), Adjacent (Proper), Adjacent (Partial)
     */
    CoreServiceResponse<String,ValidationResponse> adjacent(ValidationRequest validationRequest);
}
