package com.okta.taxcalculator.DataAccess;

import com.okta.taxcalculator.Entity.Customer;
import com.okta.taxcalculator.Entity.Tax;

import java.util.List;

public interface TaxDao {

    String insertTax(Tax tax);

    List<String> selectAllCustomers();

    String selectCustomerById(String customerId);

    void deleteCustomer(String customerId);

    String updateCustomer(String customerId, Customer customer);
}
