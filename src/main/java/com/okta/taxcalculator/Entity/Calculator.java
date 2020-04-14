package com.okta.taxcalculator.Entity;

public class Calculator {

    private String filingStatus;
    private Double grossIncome;

    public Calculator(String filingStatus, Double grossIncome) {
        this.filingStatus = filingStatus;
        this.grossIncome = grossIncome;
    }

    public Double taxAmount(){
        double taxAmount = 0;
        if(filingStatus.equals("Single")){
            taxAmount = grossIncome * .20;
        }

        return taxAmount;
    }

}
