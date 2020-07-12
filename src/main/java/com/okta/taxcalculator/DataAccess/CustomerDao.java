package com.okta.taxcalculator.DataAccess;

import com.okta.taxcalculator.Entity.CustomerAccount;

import java.util.List;

public interface CustomerDao {

    String insertCustomer(CustomerAccount customerAccount);

    List<CustomerAccount> selectAllCustomers(String id) throws Exception;

    CustomerAccount selectCustomerById(String id) throws Exception;

    void deleteCustomer(String id);

    String updateFilingStatus(CustomerAccount customerAccount);
    String updatePhoneNumber(CustomerAccount customerAccount);
    String updateFilingStatusAndPhoneNumber(CustomerAccount customerAccount);
}
