package com.okta.taxcalculator.Service;

import com.okta.taxcalculator.DataAccess.CustomerDao;
import com.okta.taxcalculator.Entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@DynamoDBTable(tableName = "TaxTable")
public class CustomerService {

    private CustomerDao customerDao;

    @Autowired
    public CustomerService(@Qualifier("dynamodb") CustomerDao customerDao){
        this.customerDao = customerDao;
    }

    public String addCustomer(Customer customer){
       return customerDao.insertCustomer(customer);
    }

    public List<String> getAllCustomers(String id){
        return customerDao.selectAllCustomers(id);
    }

    public String getCustomerById(String id){
        return customerDao.selectCustomerById(id);
    }

    public void deleteCustomer(String id){
        customerDao.deleteCustomer(id);
    }

    public String updateCustomer(Customer customer){
        return customerDao.updateCustomer(customer);
    }
}
