package com.okta.taxcalculator.Entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBTable(tableName = "TaxAmountTable")
public class Customer {

    private String id;
    private String name;
    private String filingStatus;
    private Double grossIncome;
    private Double taxAmount;

    @DynamoDBHashKey(attributeName = "id")
    public String getId() {
        id = ""+UUID.randomUUID();
        return id;
    }

    @DynamoDBAttribute(attributeName = "taxAmount")
    public Double getTaxAmount(){
        TaxCalculator calculator = new TaxCalculator(filingStatus, grossIncome);
        taxAmount = calculator.taxAmountCalc();
        return Double.valueOf(Math.round(taxAmount));
    }

}
