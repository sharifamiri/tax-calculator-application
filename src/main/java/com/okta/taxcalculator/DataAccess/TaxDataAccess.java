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
    public List<String> selectAllCustomers() {

        ScanRequest scanRequest = new ScanRequest().withTableName(tableName);
        ScanResult result = client.scan(scanRequest);
        List<String> listOfCustomers = new ArrayList<>();
        for (Map<String, AttributeValue> item : result.getItems()){
            listOfCustomers.add(item.toString());
        }
        return listOfCustomers;
    }

    @Override
    public String selectCustomerById(String id) {

        Item item = taxTable.getItem("id", id);
        return item.toJSONPretty();
    }

    @Override
    public void deleteCustomer(String id) {

        DeleteItemSpec deleteItemSpec = new DeleteItemSpec().withPrimaryKey("id", id);
        DeleteItemOutcome outcome = taxTable.deleteItem(deleteItemSpec);
    }

    @Override
    public String updateCustomer(String id, Customer customer) {

        try {
            UpdateItemSpec updateItemSpec = new UpdateItemSpec().withPrimaryKey("id", id)
                    .withUpdateExpression("set #na1=:val1, #na2=:val2")
                    .withNameMap(new NameMap().with("#na1", "name").with("#na2", "filingStatus"))
                    .withValueMap(
                            new ValueMap().withString(":val1", customer.getName())
                                    .withString(":val2", customer.getFilingStatus()))
                    .withReturnValues(ReturnValue.ALL_NEW);

            UpdateItemOutcome outcome = taxTable.updateItem(updateItemSpec);
            return outcome.getItem().toJSONPretty();
        }catch (Exception e){
            return e.getMessage();
        }
    }

}
