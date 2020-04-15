package com.okta.taxcalculator.Entity;

public class Calculator {

    private String filingStatus;
    private Double grossIncome;
    private Double taxableIncome;
    private Double taxAmount;

    static int singleStandardDeduction = 12200;
    private double firstBracketRate = 0.10, firstBracketAmt;
    private double secondBracketRate = 0.12, secondBracketAmt;
    private double thirdBracketRate = 0.22, thirdBracketAmt;
    private double fourthBracketRate = 0.24, fourthBracketAmt;
    private double fifthBracketRate = 0.32, fifthBracketAmt;
    private double sixthBracketRate = 0.35, sixthBracketAmt;
    private double seventhBracketRate = 0.37, seventhBracketAmt;



    public Calculator(String filingStatus, Double grossIncome) {
        this.filingStatus = filingStatus;
        this.grossIncome = grossIncome;
    }

    public Double taxAmount(){
        if(filingStatus.equals("Single")){
            fifthBracketAmt = 0;
            secondBracketAmt = 9700;
            thirdBracketAmt = 39475;
            fourthBracketAmt = 84200;
            fifthBracketAmt = 160275;
            sixthBracketAmt = 204100;
            seventhBracketAmt = 510300;

            if(grossIncome<singleStandardDeduction) return 0.0;

            taxableIncome = grossIncome - singleStandardDeduction;

            if(taxableIncome >= firstBracketAmt && taxableIncome <= secondBracketAmt){
                taxAmount = taxableIncome * firstBracketRate;
            }
            if(taxableIncome >= secondBracketAmt+1 && taxableIncome <= thirdBracketAmt){
                taxAmount = (firstBracketAmt * firstBracketRate)
                        + ((taxableIncome-secondBracketAmt)*secondBracketRate);
            }
            if(taxableIncome >= thirdBracketAmt+1 && taxableIncome <= fourthBracketAmt){
                taxAmount = (firstBracketAmt * firstBracketRate)
                        + (secondBracketAmt * secondBracketRate)
                        + ((taxableIncome-thirdBracketAmt)*thirdBracketRate);
            }
            if(taxableIncome >= fourthBracketAmt+1 && taxableIncome <= fifthBracketAmt){
                taxAmount = (firstBracketAmt * firstBracketRate)
                        + (secondBracketAmt * secondBracketRate)
                        + (thirdBracketAmt * thirdBracketRate)
                        + ((taxableIncome-fourthBracketAmt)*fourthBracketRate);
            }
            if(taxableIncome >= fifthBracketAmt+1 && taxableIncome <= sixthBracketAmt){
                taxAmount = (firstBracketAmt * firstBracketRate)
                        + (secondBracketAmt * secondBracketRate)
                        + (thirdBracketAmt * thirdBracketRate)
                        + (fourthBracketAmt * fourthBracketRate)
                        + ((taxableIncome-fifthBracketAmt)*fifthBracketRate);
            }
            if(taxableIncome >= sixthBracketAmt+1 && taxableIncome <= seventhBracketAmt){
                taxAmount = (firstBracketAmt * firstBracketRate)
                        + (secondBracketAmt * secondBracketRate)
                        + (thirdBracketAmt * thirdBracketRate)
                        + (fourthBracketAmt * fourthBracketRate)
                        + (fifthBracketAmt * fifthBracketRate)
                        + ((taxableIncome-sixthBracketAmt)*sixthBracketRate);
            }
            if(taxableIncome >= sixthBracketAmt+1){
                taxAmount = (firstBracketAmt * firstBracketRate)
                        + (secondBracketAmt * secondBracketRate)
                        + (thirdBracketAmt * thirdBracketRate)
                        + (fourthBracketAmt * fourthBracketRate)
                        + (fifthBracketAmt * fifthBracketRate)
                        + (sixthBracketAmt * sixthBracketRate)
                        + ((taxableIncome-seventhBracketAmt)*seventhBracketRate);
            }
        }

        return taxAmount;
    }

    public Double calculate(Double taxableIncome, int i){
        return 0.0;
    }

}
