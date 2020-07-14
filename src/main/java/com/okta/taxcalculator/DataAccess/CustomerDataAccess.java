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
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository("dynamodb")
public class CustomerDataAccess implements CustomerDao {

    static AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withRegion("us-west-2").build();
    static DynamoDB dynamoDB = new DynamoDB(client);
    static String tableName = "TaxAmountTable";
    static Table table = dynamoDB.getTable(tableName);

    @Override
    public String insertCustomer(Customer customer) {

        Item item = new Item().withPrimaryKey("id",customer.getId())
                .withString("name", customer.getName())
                .withString("filingStatus", customer.getFilingStatus())
                .withNumber("grossIncome", customer.getGrossIncome())
                .withNumber("taxAmount", customer.getTaxAmount());
        table.putItem(item);
        return item.toJSONPretty();
    }

    @Override
    public List<String> selectAllCustomers(String id) {

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

        Item item = table.getItem("id", id);
        return item.toJSONPretty();
    }

    @Override
    public void deleteCustomer(String id) {

        DeleteItemSpec deleteItemSpec = new DeleteItemSpec().withPrimaryKey("id", id);
        DeleteItemOutcome outcome = table.deleteItem(deleteItemSpec);
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

            UpdateItemOutcome outcome = table.updateItem(updateItemSpec);
            return outcome.getItem().toJSONPretty();
        }catch (Exception e){
            return e.getMessage();
        }
    }

}
