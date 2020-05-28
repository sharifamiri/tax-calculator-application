package com.okta.taxcalculator.Utilities;

import java.util.HashMap;

public class Utilities {

    private Double taxAmount;

    private double bracket1Rate = 0.10;
    private double bracket2Rate = 0.12;
    private double bracket3Rate = 0.22;
    private double bracket4Rate = 0.24;
    private double bracket5Rate = 0.32;
    private double bracket6Rate = 0.35;
    private double bracket7Rate = 0.37;

    public Double calculation(HashMap<String, Integer> brackets){

        Double taxableIncome = 0.0;
        if(taxableIncome >= brackets.get("bracket1Amt") && taxableIncome <= brackets.get("bracket2Amt")){
            taxAmount = taxableIncome * bracket1Rate;
        }
        if(taxableIncome >= brackets.get("bracket2Amt") +1 && taxableIncome <= brackets.get("bracket3Amt")){
            taxAmount = (brackets.get("bracket1Amt") * bracket1Rate)
                    + ((taxableIncome- brackets.get("bracket2Amt"))* bracket2Rate);
        }
        if(taxableIncome >= brackets.get("bracket3Amt") +1 && taxableIncome <= brackets.get("bracket4Amt")){
            taxAmount = (brackets.get("bracket1Amt") * bracket1Rate)
                    + (brackets.get("bracket2Amt") * bracket2Rate)
                    + ((taxableIncome- brackets.get("bracket3Amt"))* bracket3Rate);
        }
        if(taxableIncome >= brackets.get("bracket4Amt") +1 && taxableIncome <= brackets.get("bracket5Amt")){
            taxAmount = (brackets.get("bracket1Amt") * bracket1Rate)
                    + (brackets.get("bracket2Amt") * bracket2Rate)
                    + (brackets.get("bracket3Amt") * bracket3Rate)
                    + ((taxableIncome- brackets.get("bracket4Amt"))* bracket4Rate);
        }
        if(taxableIncome >= brackets.get("bracket5Amt") +1 && taxableIncome <= brackets.get("bracket6Amt")){
            taxAmount = (brackets.get("bracket1Amt") * bracket1Rate)
                    + (brackets.get("bracket2Amt") * bracket2Rate)
                    + (brackets.get("bracket3Amt") * bracket3Rate)
                    + (brackets.get("bracket4Amt") * bracket4Rate)
                    + ((taxableIncome- brackets.get("bracket5Amt"))* bracket5Rate);
        }
        if(taxableIncome >= brackets.get("bracket6Amt") +1 && taxableIncome <= brackets.get("bracket7Amt")){
            taxAmount = (brackets.get("bracket1Amt") * bracket1Rate)
                    + (brackets.get("bracket2Amt") * bracket2Rate)
                    + (brackets.get("bracket3Amt") * bracket3Rate)
                    + (brackets.get("bracket4Amt") * bracket4Rate)
                    + (brackets.get("bracket5Amt") * bracket5Rate)
                    + ((taxableIncome- brackets.get("bracket6Amt"))* bracket6Rate);
        }
        if(taxableIncome >= brackets.get("bracket7Amt") +1){
            taxAmount = (brackets.get("bracket1Amt") * bracket1Rate)
                    + (brackets.get("bracket2Amt") * bracket2Rate)
                    + (brackets.get("bracket3Amt") * bracket3Rate)
                    + (brackets.get("bracket4Amt") * bracket4Rate)
                    + (brackets.get("bracket5Amt") * bracket5Rate)
                    + (brackets.get("bracket6Amt") * bracket6Rate)
                    + ((taxableIncome- brackets.get("bracket7Amt"))* bracket7Rate);
        }
        return taxAmount;
    }

    //    public Customer(String id, String name, String filingStatus, Double grossIncome, Double taxAmount) {
//        this.id = id;
//        this.name = name;
//        this.filingStatus = filingStatus;
//        this.grossIncome = grossIncome;
//        this.taxAmount = taxAmount;
//    }

//    public void setId() {
//        UUID id = UUID.randomUUID();
//        this.id = id;
//    }
}
