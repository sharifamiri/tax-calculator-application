package com.okta.taxcalculator.DataAccess;

import com.okta.taxcalculator.Entity.Customer;

import java.util.List;

public interface CustomerDao {

    String insertCustomer(Customer customer);

    List<String> selectAllCustomers();

    String selectCustomerByCustomerId(String customerId);

    void deleteCustomerByCustomerId(String customerId);

    String updateCustomerNameAndFilingStatus(String customerId, Customer customer);

    String updateCustomerFilingStatus(String customerId, Customer customer);
}
