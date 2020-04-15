package com.okta.taxcalculator.DataAccess;

import com.okta.taxcalculator.Entity.Customer;

import java.util.List;

public interface CustomerDao {

//    int insertCustomer(UUID id, Customer customer, Double taxAmount);

//    default int insertCustomer(Customer customer){
//        UUID id = UUID.randomUUID();
//        Calculator calculator = new Calculator(customer.getFilingStatus(),customer.getGrossIncome());
//        Double taxAmount = calculator.taxAmount();
//        return insertCustomer(id, customer, taxAmount);
//    }

    String insertCustomer(Customer customer);

    List<String> selectAllCustomers(String id);

    String selectCustomerById(String id);

    void deleteCustomer(String id);

    String updateCustomer(String id, Customer customer);
}
