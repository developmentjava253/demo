package com.example.demo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ValidationEnum {
    INTERSECTION("Intersection"),
    NOT_INTERSECTION("Not intersection"),
    CONTAINMENT("Containment"),
    NOT_CONTAINMENT("Not containment"),
    ADJACENT("Adjacent"),
    NOT_ADJACENT("Not adjacent"),
    ADJACENT_SUB_LINE("Adjacent (Sub-line)"),
    ADJACENT_PROPER("Adjacent (Proper)"),
    ADJACENT_PARTIAL("Adjacent (Partial)");

    ValidationEnum(String s) {
    }
}
