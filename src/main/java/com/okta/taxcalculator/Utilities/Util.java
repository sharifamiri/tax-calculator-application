package com.okta.taxcalculator.Utilities;

import com.okta.taxcalculator.Entity.CustomerAccount;

public class Util {

//        private final AmazonDynamoDB cli = AmazonDynamoDBClientBuilder.defaultClient();
//        public DynamoDBMapper map = new DynamoDBMapper(cli);
//        private static List<Customer> DB = new ArrayList<>();

    public void insertCustomer(CustomerAccount customerAccount) {
//        1st WAY:
//        Customer customer = new Customer();
//        DB.add(new Customer(customer.getId(), customer.getName(), customer.getFilingStatus(), customer.getGrossIncome(), customer.getTaxAmount()));
//        mapper.save(customer);
    }

    public void selectAllCustomers(String id) {
//        Customer customer = new Customer();
//
//        Customer c = mapper.load(Customer.class, customer.getId());
//        System.out.println(c);
////        return DB;
    }

    public void selectCustomerById(String id) {
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

    public void deleteCustomer(String id) {
//        Table table = dynamoDB.getTable(tableName);
//
//        DeleteItemSpec deleteItemSpec = new DeleteItemSpec().withPrimaryKey("id", id);
//                    .withConditionExpression("#ip = :val").withNameMap(new NameMap().with("#ip", "InPublication"))
//                    .withValueMap(new ValueMap().withBoolean(":val", false)).withReturnValues(ReturnValue.ALL_OLD);
//
//        DeleteItemOutcome outcome = table.deleteItem(deleteItemSpec);

    }

    public static void main(String[] args) {

//        try {
//            Item item = table.getItem("id", "6787fd82-f66a-4c52-8711-3bc851493612");
//            System.out.println(item.toJSONPretty());
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }
//
//        ItemCollection ic = table.query("id", "");
//        TableDescription t = dynamoDB.getTable(tableName).describe();


//        ScanRequest scanRequest = new ScanRequest().withTableName(tableName);
//        ScanResult result = client.scan(scanRequest);
//        List<String> list = new ArrayList<>();
//        for (Map<String, AttributeValue> item : result.getItems()){
//            System.out.println(new JSONObject(item).toString());
//            System.out.println(item);
//        }

//        QuerySpec spec = new QuerySpec()
//                .withKeyConditionExpression("id = :id")
//                .withValueMap(new ValueMap()
//                        .withString("id", "Amazon DynamoDB#DynamoDB Thread 1"));
//        ItemCollection<QueryOutcome> items = table.query(spec);
//        Iterator<Item> iterator = items.iterator();
//        Item item = null;
//        while (iterator.hasNext()){
//            item = iterator.next();
//            System.out.println(item.toJSONPretty());
//        }

//        QueryRequest scanRequest = new QueryRequest().withTableName(tableName);
//        QueryResult result = client.query(scanRequest);
//        for (Map<String, AttributeValue> item : result.getItems()){
//            System.out.println(item);
//        }
//        Iterator<ScanResult> iterator = result.iterator();
//        System.out.println(result.getItems());
    }

}
