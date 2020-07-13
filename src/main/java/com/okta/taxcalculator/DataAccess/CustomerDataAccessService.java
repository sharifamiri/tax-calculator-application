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
import com.okta.taxcalculator.Helper.JsonHelper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository("dynamodb1")
public class CustomerDataAccessService implements CustomerDao {

    static AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withRegion("us-west-2").build();
    static DynamoDB dynamoDB = new DynamoDB(client);
    static String tableName = "CustomerAccount";
    static Table table = dynamoDB.getTable(tableName);

    @Override
    public String insertCustomer(CustomerAccount customerAccount) {

        Item item = new Item().withPrimaryKey("customerId", customerAccount.getCustomerId())
                .withString("firstName", customerAccount.getFirstName())
                .withString("lastName", customerAccount.getLastName())
                .withString("phoneNumber", customerAccount.getPhoneNumber());
        table.putItem(item);
        return item.toJSONPretty();
    }

    @Override
    public List<CustomerAccount> selectAllCustomers(String id) throws Exception {

        ScanRequest scanRequest = new ScanRequest().withTableName(tableName);
        ScanResult result = client.scan(scanRequest);
        List<CustomerAccount> listOfCustomers = new ArrayList<>();
        for (Map<String, AttributeValue> item : result.getItems()){
            listOfCustomers.add(JsonHelper.fromJson(item.toString(), CustomerAccount.class));
        }
        return listOfCustomers;
    }

    @Override
    public CustomerAccount selectCustomerById(String id) throws Exception {

        Item item = table.getItem("customerId", id);
        String dataString = item.toJSONPretty();
        return JsonHelper.fromJson(dataString, CustomerAccount.class);
    }

    @Override
    public void deleteCustomer(String id) {

        DeleteItemSpec deleteItemSpec = new DeleteItemSpec().withPrimaryKey("customerId", id);
        DeleteItemOutcome outcome = table.deleteItem(deleteItemSpec);
    }

    @Override
    public String updateFilingStatus(CustomerAccount customerAccount) {

        try {
            UpdateItemSpec updateItemSpec = new UpdateItemSpec().withPrimaryKey("customerId", customerAccount.getCustomerId())
                    .withUpdateExpression("set #na=:val2")
                    .withNameMap(new NameMap().with("#na", "filingStatus"))
                    .withValueMap(
                            new ValueMap().withString(":val2", customerAccount.getFilingStatus()))
                    .withReturnValues(ReturnValue.ALL_NEW);

            UpdateItemOutcome outcome = table.updateItem(updateItemSpec);
            return outcome.getItem().toJSONPretty();
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @Override
    public String updatePhoneNumber(CustomerAccount customerAccount) {

        try {
            UpdateItemSpec updateItemSpec = new UpdateItemSpec().withPrimaryKey("customerId", customerAccount.getCustomerId())
                    .withUpdateExpression("set #na=:val2")
                    .withNameMap(new NameMap().with("#na", "phoneNumber"))
                    .withValueMap(
                            new ValueMap().withString(":val2", customerAccount.getPhoneNumber()))
                    .withReturnValues(ReturnValue.ALL_NEW);

            UpdateItemOutcome outcome = table.updateItem(updateItemSpec);
            return outcome.getItem().toJSONPretty();
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @Override
    public String updateFilingStatusAndPhoneNumber(CustomerAccount customerAccount) {

        try {
            UpdateItemSpec updateItemSpec = new UpdateItemSpec().withPrimaryKey("customerId", customerAccount.getCustomerId())
                    .withUpdateExpression("set #na1=:val1, #na2=:val2")
                    .withNameMap(new NameMap().with("#na1", "phoneNumber").with("#na2", "filingStatus"))
                    .withValueMap(
                            new ValueMap().withString(":val1", customerAccount.getPhoneNumber())
                                    .withString(":val2", customerAccount.getFilingStatus()))
                    .withReturnValues(ReturnValue.ALL_NEW);

            UpdateItemOutcome outcome = table.updateItem(updateItemSpec);
            return outcome.getItem().toJSONPretty();
        }catch (Exception e){
            return e.getMessage();
        }
    }

}
