package com.okta.taxcalculator.Entity;

import java.util.HashMap;

public class Calculator {

    private String filingStatus;
    private Double grossIncome;
    private Double taxableIncome;
    private Double taxAmount;

    private double bracket1Rate = 0.10;
    private double bracket2Rate = 0.12;
    private double bracket3Rate = 0.22;
    private double bracket4Rate = 0.24;
    private double bracket5Rate = 0.32;
    private double bracket6Rate = 0.35;
    private double bracket7Rate = 0.37;

    public Calculator(String filingStatus, Double grossIncome) {
        this.filingStatus = filingStatus;
        this.grossIncome = grossIncome;
    }

    public Double taxAmountCalc(){

        HashMap<String, Integer> brackets = new HashMap<>();

        if(filingStatus.equals("Single")) {

            Integer singleStandardDeduction = 12200;
            if(grossIncome<singleStandardDeduction) return 0.0;
            taxableIncome = grossIncome - singleStandardDeduction;

            brackets.put("bracket1Amt", 0);
            brackets.put("bracket2Amt", 9700);
            brackets.put("bracket3Amt", 39475);
            brackets.put("bracket4Amt", 84200);
            brackets.put("bracket5Amt", 160275);
            brackets.put("bracket6Amt", 204100);
            brackets.put("bracket7Amt", 510300);
        }
            return calculation(brackets);
    }

    public Double calculation(HashMap<String, Integer> brackets){

        if(taxableIncome >= brackets.get("bracket1Amt") && taxableIncome <= brackets.get("bracket2Amt")){
            taxAmount = taxableIncome * bracket1Rate;
        }
        if(taxableIncome >= brackets.get("bracket2Amt") +1 && taxableIncome <= brackets.get("bracket3Amt")){
            taxAmount = (brackets.get("bracket2Amt") * bracket1Rate)
                    + ((taxableIncome- brackets.get("bracket2Amt"))* bracket2Rate);
        }
        if(taxableIncome >= brackets.get("bracket3Amt") +1 && taxableIncome <= brackets.get("bracket4Amt")){
            taxAmount = (brackets.get("bracket2Amt") * bracket1Rate)
                    + ((brackets.get("bracket3Amt") - brackets.get("bracket2Amt")) * bracket2Rate)
                    + ((taxableIncome- brackets.get("bracket3Amt"))* bracket3Rate);
        }
        if(taxableIncome >= brackets.get("bracket4Amt") +1 && taxableIncome <= brackets.get("bracket5Amt")){
            taxAmount = (brackets.get("bracket2Amt") * bracket1Rate)
                    + ((brackets.get("bracket3Amt") - brackets.get("bracket2Amt")) * bracket2Rate)
                    + ((brackets.get("bracket4Amt") - brackets.get("bracket3Amt")) * bracket3Rate)
                    + ((taxableIncome- brackets.get("bracket4Amt"))* bracket4Rate);
        }
        if(taxableIncome >= brackets.get("bracket5Amt") +1 && taxableIncome <= brackets.get("bracket6Amt")){
            taxAmount = (brackets.get("bracket2Amt") * bracket1Rate)
                    + ((brackets.get("bracket3Amt") - brackets.get("bracket2Amt")) * bracket2Rate)
                    + ((brackets.get("bracket4Amt") - brackets.get("bracket3Amt")) * bracket3Rate)
                    + ((brackets.get("bracket5Amt") - brackets.get("bracket4Amt")) * bracket4Rate)
                    + ((taxableIncome- brackets.get("bracket5Amt"))* bracket5Rate);
        }
        if(taxableIncome >= brackets.get("bracket6Amt") +1 && taxableIncome <= brackets.get("bracket7Amt")){
            taxAmount = (brackets.get("bracket2Amt") * bracket1Rate)
                    + ((brackets.get("bracket3Amt") - brackets.get("bracket2Amt")) * bracket2Rate)
                    + ((brackets.get("bracket4Amt") - brackets.get("bracket3Amt")) * bracket3Rate)
                    + ((brackets.get("bracket5Amt") - brackets.get("bracket4Amt")) * bracket4Rate)
                    + ((brackets.get("bracket6Amt") - brackets.get("bracket5Amt")) * bracket5Rate)
                    + ((taxableIncome- brackets.get("bracket6Amt"))* bracket6Rate);
        }
        if(taxableIncome >= brackets.get("bracket7Amt") +1){
            taxAmount = (brackets.get("bracket2Amt") * bracket1Rate)
                    + ((brackets.get("bracket3Amt") - brackets.get("bracket2Amt")) * bracket2Rate)
                    + ((brackets.get("bracket4Amt") - brackets.get("bracket3Amt")) * bracket3Rate)
                    + ((brackets.get("bracket5Amt") - brackets.get("bracket4Amt")) * bracket4Rate)
                    + ((brackets.get("bracket6Amt") - brackets.get("bracket5Amt")) * bracket5Rate)
                    + ((brackets.get("bracket7Amt") - brackets.get("bracket6Amt")) * bracket6Rate)
                    + ((taxableIncome- brackets.get("bracket7Amt"))* bracket7Rate);
        }
        return taxAmount;
    }

}
