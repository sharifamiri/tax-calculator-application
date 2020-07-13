package com.okta.taxcalculator.DataAccess;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.NameMap;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ReturnValue;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.okta.taxcalculator.Entity.CustomerAccount;
import com.okta.taxcalculator.Entity.TaxCalculations;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository("dynamodb2")
public class TaxCalculationsAccessService implements TaxCalculationDao {

    static AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withRegion("us-west-2").build();
    static DynamoDB dynamoDB = new DynamoDB(client);
    static String tableName = "TaxCalculation";
    static Table table = dynamoDB.getTable(tableName);

    @Override
    public void insertTax(TaxCalculations taxCalculations) {

        Item item = new Item().withPrimaryKey("customerId", taxCalculations.getCustomerId())
                .withDouble("grossIncome", taxCalculations.getGrossIncome())
                .withDouble("calculatedTaxAmount", taxCalculations.getCalculatedTaxAmount());
        table.putItem(item);
    }
}
