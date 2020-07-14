package com.okta.taxcalculator.Entity;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.okta.taxcalculator.Utilities.TaxCalculator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBTable(tableName = "TaxCalculation")
public class Tax {

    private String customerId;
    private Double grossIncome;
    private Double taxAmount;

    @DynamoDBAttribute(attributeName = "taxAmount")
    public Double getTaxAmount(String filingStatus){
        TaxCalculator calculator = new TaxCalculator(filingStatus, grossIncome);
        taxAmount = calculator.taxAmountCalc();
        return Double.valueOf(Math.round(taxAmount));
    }


}
