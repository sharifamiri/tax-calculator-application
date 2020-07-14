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
@DynamoDBTable(tableName = "CustomerAccount")
public class Customer {

    private String customerId;
    private String name;
    private String filingStatus;

    @DynamoDBHashKey(attributeName = "customerId")
    public String getCustomerId() {
        customerId = ""+UUID.randomUUID();
        return customerId;
    }
}
