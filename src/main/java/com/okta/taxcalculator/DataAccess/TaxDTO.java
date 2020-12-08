package com.okta.taxcalculator.DataAccess;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.okta.taxcalculator.Utilities.TaxCalculator;
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
@DynamoDBTable(tableName = "TaxCalculation")
public class TaxDTO {

    private String customerID;
    private String customerName;
    private Double grossIncome;
    private Gender filingStatus;
    private Double taxAmount;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createDate;

    public Double getTaxAmount(TaxCalculator taxCalculator) {
        return taxAmount;
    }
}
