package com.okta.taxcalculator.DataAccess;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.NameMap;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ReturnValue;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.okta.taxcalculator.Entity.Customer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Repository("fakeDao")
public class CustomerDataAccessService implements CustomerDao {

//    private final AmazonDynamoDB cli = AmazonDynamoDBClientBuilder.defaultClient();
//    public DynamoDBMapper map = new DynamoDBMapper(cli);

    static AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
    static DynamoDBMapper mapper = new DynamoDBMapper(client);
    static DynamoDB dynamoDB = new DynamoDB(client);
    static String tableName = "TaxAmountTable";
    static Table table = dynamoDB.getTable(tableName);

    private static List<Customer> DB = new ArrayList<>();

    @Override
    public int insertCustomer(Customer customer) {
//        1st WAY:
//        Customer customer = new Customer();
//        DB.add(new Customer(customer.getId(), customer.getName(), customer.getFilingStatus(), customer.getGrossIncome(), customer.getTaxAmount()));
//        mapper.save(customer);

//        2nd WAY:
        Item item = new Item().withPrimaryKey("id",customer.getId())
                .withString("name", customer.getName())
                .withString("filingStatus", customer.getFilingStatus())
                .withNumber("grossIncome", customer.getGrossIncome())
                .withNumber("taxAmount", customer.getTaxAmount());
        table.putItem(item);
        return 1;
    }

    @Override
    public List<String> selectAllCustomers(String id) {
//        Customer customer = new Customer();
//
//        Customer c = mapper.load(Customer.class, customer.getId());
//        System.out.println(c);
////        return DB;
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

        //2nd WAY:
//        Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
//        eav.put(":id", new AttributeValue().withS(id));
//
//        DynamoDBQueryExpression<Customer> queryExpression = new DynamoDBQueryExpression<Customer>()
//                .withKeyConditionExpression("parentKey = :id")
//                .withExpressionAttributeValues(eav);
//
//        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
//        List<Customer> requestedLogs = mapper.query(Customer.class, queryExpression);
//        return requestedLogs;
    }

    @Override
    public void deleteCustomer(String id) {
        Table table = dynamoDB.getTable(tableName);

            DeleteItemSpec deleteItemSpec = new DeleteItemSpec().withPrimaryKey("id", id);
//                    .withConditionExpression("#ip = :val").withNameMap(new NameMap().with("#ip", "InPublication"))
//                    .withValueMap(new ValueMap().withBoolean(":val", false)).withReturnValues(ReturnValue.ALL_OLD);

            DeleteItemOutcome outcome = table.deleteItem(deleteItemSpec);
    }

    @Override
    public String updateCustomer(Customer customer) {

        UpdateItemSpec updateItemSpec = new UpdateItemSpec().withPrimaryKey("id", customer.getId().toString())
                .withUpdateExpression("add #a :val1 set #na=:val2")
                .withNameMap(new NameMap().with("#a", "name").with("#na", "filingStatus"))
                .withValueMap(
                        new ValueMap().withStringSet(":val1", customer.getName().toString()).withString(":val2", customer.getFilingStatus().toString()))
                .withReturnValues(ReturnValue.ALL_NEW);

        UpdateItemOutcome outcome = table.updateItem(updateItemSpec);
        return outcome.getItem().toJSONPretty();
    }

    public static void main(String[] args) {
        Table table = dynamoDB.getTable(tableName);

//        try {
//            Item item = table.getItem("id", "6787fd82-f66a-4c52-8711-3bc851493612");
//            System.out.println(item.toJSONPretty());
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }
//
//        ItemCollection ic = table.query("id", "");
//        TableDescription t = dynamoDB.getTable(tableName).describe();

        System.out.println("-----------------");

//        ScanRequest scanRequest = new ScanRequest().withTableName(tableName);
//        ScanResult result = client.scan(scanRequest);
//        List<String> list = new ArrayList<>();
//        for (Map<String, AttributeValue> item : result.getItems()){
//            System.out.println(new JSONObject(item).toString());
//            System.out.println(item);
//        }

        QuerySpec spec = new QuerySpec()
                .withKeyConditionExpression("id = :id")
                .withValueMap(new ValueMap()
                        .withString("id", "Amazon DynamoDB#DynamoDB Thread 1"));
        ItemCollection<QueryOutcome> items = table.query(spec);
        Iterator<Item> iterator = items.iterator();
        Item item = null;
        while (iterator.hasNext()){
            item = iterator.next();
            System.out.println(item.toJSONPretty());
        }

//        QueryRequest scanRequest = new QueryRequest().withTableName(tableName);
//        QueryResult result = client.query(scanRequest);
//        for (Map<String, AttributeValue> item : result.getItems()){
//            System.out.println(item);
//        }
//        Iterator<ScanResult> iterator = result.iterator();
//        System.out.println(result.getItems());

    }

}
