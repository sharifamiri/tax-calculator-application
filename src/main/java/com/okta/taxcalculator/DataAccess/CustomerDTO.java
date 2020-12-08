package com.okta.taxcalculator.DataAccess;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.okta.taxcalculator.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@DynamoDBTable(tableName = "CustomerAccount")
public class CustomerDTO {

    private String customerId;
    private String firstName;
    private String lastName;
    private Gender filingStatus;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createDate;

}
