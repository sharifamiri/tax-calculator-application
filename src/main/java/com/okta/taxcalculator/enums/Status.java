package com.okta.taxcalculator.enums;

public enum Status {

    SINGLE("Single"), MARRIED("Married");

    private final String value;

    Status(String value) {
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
