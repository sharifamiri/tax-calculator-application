package com.okta.taxcalculator.Service;

import com.okta.taxcalculator.DataAccess.TaxDao;
import com.okta.taxcalculator.Entity.Customer;
import com.okta.taxcalculator.Entity.Tax;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaxService {

    private TaxDao taxDao;

    @Autowired
    public TaxService(@Qualifier("tax") TaxDao taxDao){
        this.taxDao = taxDao;
    }

    public String addTax(Tax tax){
       return taxDao.insertTax(tax);
    }

    public List<String> getAllCustomers(){
        return taxDao.selectAllCustomers();
    }

    public String getCustomerById(String customerId){
        return taxDao.selectCustomerById(customerId);
    }

    public void deleteCustomer(String customerId){
        taxDao.deleteCustomer(customerId);
    }

    public String updateCustomer(String customerId, Customer customer){
        return taxDao.updateCustomer(customerId, customer);
    }
}
