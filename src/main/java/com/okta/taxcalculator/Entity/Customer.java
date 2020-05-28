package com.okta.taxcalculator.Entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.UUID;

@DynamoDBTable(tableName = "TaxAmountTable")
public class Customer {

    private String id;
    private String name;
    private String filingStatus;
    private Double grossIncome;
    private Double taxAmount;

    public Customer() {
    }

    @DynamoDBHashKey(attributeName = "id")
    public String getId() {
        id = ""+UUID.randomUUID();
        return id;
    }

    @DynamoDBAttribute(attributeName = "name")
//    @DynamoDBRangeKey(attributeName = "name")
    public String getName() {
        return name;
    }

    @DynamoDBAttribute(attributeName = "filingStatus")
    public String getFilingStatus() {
        return filingStatus;
    }

    @DynamoDBAttribute(attributeName = "grossIncome")
    public Double getGrossIncome() {
        return Double.valueOf(Math.round(grossIncome));
    }

    @DynamoDBAttribute(attributeName = "taxAmount")
    public Double getTaxAmount(){
        Calculator calculator = new Calculator(filingStatus, grossIncome);
        taxAmount = calculator.taxAmountCalc();
        return Double.valueOf(Math.round(taxAmount));
    }

}
