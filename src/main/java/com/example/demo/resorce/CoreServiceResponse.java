package com.example.demo.resorce;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
public class CoreServiceResponse <R, T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private int statusCode;
    private String hasErrorResponse;
    private CoreServiceErrorResponse errorResponse;
    private T responseObject;
    private ZonedDateTime timestamp;
    private R requestObject;

    public CoreServiceResponse(R requestObject, T responseObject) {
        this.responseObject = responseObject;
        this.requestObject = requestObject;
        this.hasErrorResponse = Boolean.toString(false);
        this.timestamp = ZonedDateTime.now(ZoneId.of("UTC"));
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(" statusCode=").append(statusCode)
                .append(" responseObject=").append(responseObject != null ? responseObject.toString() : "null")
                .append(" requestObject=").append(requestObject != null ? requestObject.toString() : "null")
                .append(" timestamp=").append(timestamp);
        return sb.toString();
    }
}
