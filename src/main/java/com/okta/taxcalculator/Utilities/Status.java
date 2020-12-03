package com.okta.taxcalculator.Utilities;

public enum Status {
    SINGLE("Single"),MARRIED("Married");

    private final String value;

    Status(String value) {
        this.value = value;
    }
}
