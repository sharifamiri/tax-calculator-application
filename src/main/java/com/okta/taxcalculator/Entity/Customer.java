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

//    public void setTaxAmount(Double taxAmount) {
//        Calculator calculator = new Calculator(filingStatus, grossIncome);
//        taxAmount = calculator.taxAmount();
//        this.taxAmount = taxAmount;
//    }

    @DynamoDBAttribute(attributeName = "taxAmount")
    public Double getTaxAmount(){
        Calculator calculator = new Calculator(filingStatus, grossIncome);
        taxAmount = calculator.taxAmount();
        return Double.valueOf(Math.round(taxAmount));
    }

}
