package com.okta.taxcalculator.DataAccess;

import com.okta.taxcalculator.Entity.Customer;

import java.util.List;

public interface CustomerDao {

    String insertCustomer(Customer customer);

    List<String> selectAllCustomers(String id);

    String selectCustomerById(String id);

    void deleteCustomer(String id);

    String updateCustomer(Customer customer);
}
