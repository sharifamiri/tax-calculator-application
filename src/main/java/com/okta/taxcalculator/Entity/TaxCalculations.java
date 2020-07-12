package com.okta.taxcalculator.Entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@DynamoDBTable(tableName = "TaxCalculation")
public class TaxCalculations {
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public double getGrossIncome() {
        return grossIncome;
    }

    public void setGrossIncome(double grossIncome) {
        this.grossIncome = grossIncome;
    }

    public double getCalculatedTaxAmount() {
        return calculatedTaxAmount;
    }

    public void setCalculatedTaxAmount(double calculatedTaxAmount) {
        this.calculatedTaxAmount = calculatedTaxAmount;
    }

    @DynamoDBHashKey
    private String customerId;
    private double grossIncome;
    private double calculatedTaxAmount;
}
