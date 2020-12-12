package com.okta.taxcalculator.Utilities;

import com.okta.taxcalculator.enums.Status;

public class TaxCalculator {

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

    private int bracket1Amt = 0;
    private int bracket2Amt = 9700;
    private int bracket3Amt = 39475;
    private int bracket4Amt = 84200;
    private int bracket5Amt = 160275;
    private int bracket6Amt = 214100;
    private int bracket7Amt = 510300;

    public TaxCalculator(String filingStatus, Double grossIncome) {
        this.filingStatus = filingStatus;
        this.grossIncome = grossIncome;
    }

    public Double taxAmountCalc(){

        if(filingStatus.equals(Status.SINGLE.getValue())) {
            Integer singleStandardDeduction = 12200;
            if(grossIncome < singleStandardDeduction) return 0.0;
            taxableIncome = grossIncome - singleStandardDeduction;

        }else if (filingStatus.equals(Status.MARRIED.getValue())) {
            int marriedStandardDeduction = 19400;
            if(grossIncome < marriedStandardDeduction) return 0.0;
            taxableIncome = grossIncome - marriedStandardDeduction;
        }
            return calculation();
    }

    public Double calculation(){

        if(taxableIncome >= bracket1Amt && taxableIncome <= bracket2Amt){
            taxAmount = taxableIncome * bracket1Rate;
        }
        if(taxableIncome >= bracket2Amt +1 && taxableIncome <= bracket3Amt){
            taxAmount = (bracket2Amt * bracket1Rate)
                    + ((taxableIncome- bracket2Amt)* bracket2Rate);
        }
        if(taxableIncome >= bracket3Amt +1 && taxableIncome <= bracket4Amt){
            taxAmount = (bracket2Amt * bracket1Rate)
                    + ((bracket3Amt - bracket2Amt) * bracket2Rate)
                    + ((taxableIncome- bracket3Amt)* bracket3Rate);
        }
        if(taxableIncome >= bracket4Amt +1 && taxableIncome <= bracket5Amt){
            taxAmount = (bracket2Amt * bracket1Rate)
                    + ((bracket3Amt - bracket2Amt) * bracket2Rate)
                    + ((bracket4Amt - bracket3Amt) * bracket3Rate)
                    + ((taxableIncome- bracket4Amt)* bracket4Rate);
        }
        if(taxableIncome >= bracket5Amt +1 && taxableIncome <= bracket6Amt){
            taxAmount = (bracket2Amt * bracket1Rate)
                    + ((bracket3Amt - bracket2Amt) * bracket2Rate)
                    + ((bracket4Amt - bracket3Amt) * bracket3Rate)
                    + ((bracket5Amt - bracket4Amt) * bracket4Rate)
                    + ((taxableIncome- bracket5Amt)* bracket5Rate);
        }
        if(taxableIncome >= bracket6Amt +1 && taxableIncome <= bracket7Amt){
            taxAmount = (bracket2Amt * bracket1Rate)
                    + ((bracket3Amt - bracket2Amt) * bracket2Rate)
                    + ((bracket4Amt - bracket3Amt) * bracket3Rate)
                    + ((bracket5Amt - bracket4Amt) * bracket4Rate)
                    + ((bracket6Amt - bracket5Amt) * bracket5Rate)
                    + ((taxableIncome- bracket6Amt)* bracket6Rate);
        }
        if(taxableIncome >= bracket7Amt +1){
            taxAmount = (bracket2Amt * bracket1Rate)
                    + ((bracket3Amt - bracket2Amt) * bracket2Rate)
                    + ((bracket4Amt - bracket3Amt) * bracket3Rate)
                    + ((bracket5Amt - bracket4Amt) * bracket4Rate)
                    + ((bracket6Amt - bracket5Amt) * bracket5Rate)
                    + ((bracket7Amt - bracket6Amt) * bracket6Rate)
                    + ((taxableIncome- bracket7Amt)* bracket7Rate);
        }
        return taxAmount;
    }

}
