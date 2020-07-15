package com.okta.taxcalculator.Service;

import com.okta.taxcalculator.DataAccess.CustomerDao;
import com.okta.taxcalculator.Entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private CustomerDao customerDao;

    @Autowired
    public CustomerService(@Qualifier("customer") CustomerDao customerDao){
        this.customerDao = customerDao;
    }

    public String addCustomer(Customer customer){
       return customerDao.insertCustomer(customer);
    }

    public List<String> getAllCustomers(){
        return customerDao.selectAllCustomers();
    }

    public String getCustomerByCustomerId(String customerId){ return customerDao.selectCustomerByCustomerId(customerId);
    }

    public void deleteCustomerByCustomerId(String customerId){
        customerDao.deleteCustomerByCustomerId(customerId);
    }

    public String updateCustomerNameAndFilingStatus(String customerId, Customer customer){
        return customerDao.updateCustomerNameAndFilingStatus(customerId, customer);
    }

    public String updateCustomerFilingStatus(String customerId, Customer customer){
        return customerDao.updateCustomerFilingStatus(customerId, customer);
    }
}
