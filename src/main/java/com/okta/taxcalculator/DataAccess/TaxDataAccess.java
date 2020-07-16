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
import com.okta.taxcalculator.Entity.Customer;
import com.okta.taxcalculator.Entity.Tax;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository("tax")
public class TaxDataAccess implements TaxDao {

    static AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withRegion("us-west-2").build();
    static DynamoDB dynamoDB = new DynamoDB(client);
    static String tableName = "TaxCalculation";
    static Table taxTable = dynamoDB.getTable(tableName);

    static Table customerTable = dynamoDB.getTable("CustomerAccount");

    @Override
    public String insertTax(Tax tax) {

        Item item1 = customerTable.getItem("customerId", tax.getCustomerId());
        String filingStatus = (String) item1.get("filingStatus");

        Item item2 = new Item().withPrimaryKey("customerId", tax.getCustomerId())
                .withString("filingStatus", filingStatus)
                .withNumber("grossIncome", tax.getGrossIncome())
                .withNumber("taxAmount", tax.getTaxAmount(filingStatus));
        taxTable.putItem(item2);
        return item2.toJSONPretty();

    }

    @Override
    public List<String> selectAllTaxes() {

        ScanRequest scanRequest = new ScanRequest().withTableName(tableName);
        ScanResult result = client.scan(scanRequest);
        List<String> listOfTaxes = new ArrayList<>();
        for (Map<String, AttributeValue> item : result.getItems()){
            listOfTaxes.add(item.toString());
        }
        return listOfTaxes;
    }

    @Override
    public String selectTaxByCustomerId(String customerId) {

        Item item = taxTable.getItem("customerId", customerId);
        return item.toJSONPretty();
    }

    @Override
    public void deleteTaxByCustomerId(String customerId) {

        DeleteItemSpec deleteItemSpec = new DeleteItemSpec().withPrimaryKey("customerId", customerId);
        DeleteItemOutcome outcome = taxTable.deleteItem(deleteItemSpec);
    }

    @Override
    public String updateGrossIncome(String customerId, Tax tax) {

        Item item = taxTable.getItem("customerId", customerId);
        String filingStatus = (String) item.get("filingStatus");
        try {
            UpdateItemSpec updateItemSpec = new UpdateItemSpec().withPrimaryKey("customerId", customerId)
                    .withUpdateExpression("set #na1=:val1, #na2=:val2")
                    .withNameMap(new NameMap().with("#na1", "grossIncome").with("#na2", "taxAmount"))
                    .withValueMap(
                            new ValueMap().withNumber(":val1", tax.getGrossIncome())
                                          .withNumber(":val2", tax.getTaxAmount(filingStatus)))
                    .withReturnValues(ReturnValue.ALL_NEW);

            UpdateItemOutcome outcome = taxTable.updateItem(updateItemSpec);
            return outcome.getItem().toJSONPretty();
        }catch (Exception e){
            return e.getMessage();
        }
    }

}
