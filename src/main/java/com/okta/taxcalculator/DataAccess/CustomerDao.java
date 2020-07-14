package com.okta.taxcalculator.DataAccess;

import com.okta.taxcalculator.Entity.Customer;

import java.util.List;

public interface CustomerDao {

    String insertCustomer(Customer customer);

    List<String> selectAllCustomers();

    String selectCustomerById(String customerId);

    void deleteCustomer(String customerId);

    String updateCustomerNameAndFilingStatus(String customerId, Customer customer);

    String updateCustomerFilingStatus(String customerId, Customer customer);
}
