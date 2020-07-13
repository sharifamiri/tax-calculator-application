package com.okta.taxcalculator.Entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@DynamoDBTable(tableName = "CustomerAccount")
public class CustomerAccount {

    @DynamoDBHashKey
    private String customerId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String filingStatus;

}
