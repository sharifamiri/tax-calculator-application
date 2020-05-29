package com.okta.taxcalculator.DataAccess;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.NameMap;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.ReturnValue;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.okta.taxcalculator.Entity.Customer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("dynamodb")
public class CustomerDataAccessService implements CustomerDao {

    static AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
    static DynamoDB dynamoDB = new DynamoDB(client);
    static String tableName = "TaxAmountTable";
    static Table table = dynamoDB.getTable(tableName);
    static DynamoDBMapper mapper = new DynamoDBMapper(client);

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
    public List<Customer> selectAllCustomers() {

        ScanRequest scanRequest = new ScanRequest().withTableName(tableName);
        ScanResult result = client.scan(scanRequest);
        List<Customer> listOfCustomers = new ArrayList<>();
        result.getItems().forEach(item -> {
            listOfCustomers.add(Customer.builder()
                    .name(item.get("name").getS())
                    .id(item.get("id").getS())
                    .build());
        });
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
                    .withUpdateExpression("set #a=:val1, #b=:val2")
                    .withNameMap(new NameMap().with("#a", "name").with("#b", "filingStatus"))
                    .withValueMap(
                            new ValueMap().withString(":val1", customer.getName()).withString(":val2", customer.getFilingStatus()))
                    .withReturnValues(ReturnValue.ALL_NEW);

            UpdateItemOutcome outcome = table.updateItem(updateItemSpec);
            return outcome.getItem().toJSONPretty();
        }catch (Exception e){
            return e.getMessage();
        }
    }

//        try {
//            Customer cust = mapper.load(Customer.class, id);
//            cust = customer;
//            mapper.save(cust);
//            System.out.println(cust);
//            return cust.toString();
//        }catch (Exception e){
//            return e.getMessage();
//        }

    //3 things i need
    /*
    - attribute name, value and type
     */



}
