package com.okta.taxcalculator.Service;

import com.okta.taxcalculator.DataAccess.CustomerDao;
import com.okta.taxcalculator.Entity.CustomerAccount;
import com.okta.taxcalculator.to.CreateCustomerResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@Component
public class CustomerService {

    private CustomerDao customerDao;

    @Autowired
    public CustomerService(@Qualifier("dynamodb") CustomerDao customerDao){
        this.customerDao = customerDao;
    }

    public String addCustomer(CustomerAccount customerAccount){
       return customerDao.insertCustomer(customerAccount);
    }

    public List<CreateCustomerResponse> getAllCustomers(String id) throws Exception {
        List<CustomerAccount> customerAccounts = customerDao.selectAllCustomers(id);
        List<CreateCustomerResponse> responses = new ArrayList<>();
        customerAccounts.forEach(customerAccount -> {
            responses.add(buildCreateCustomerResponse(customerAccount));
        });
        return responses;
    }

    public CreateCustomerResponse getCustomerById(String id) throws Exception{
        CustomerAccount customerAccount = customerDao.selectCustomerById(id);
        return buildCreateCustomerResponse(customerAccount);
    }

    public CustomerAccount getCustomerAccountById(String id) throws Exception{
        return customerDao.selectCustomerById(id);
    }

    public void deleteCustomer(String id){
        customerDao.deleteCustomer(id);
    }

    private CreateCustomerResponse buildCreateCustomerResponse(CustomerAccount customerAccount) {
        CreateCustomerResponse response = new CreateCustomerResponse();
        response.setAccountId(customerAccount.getCustomerId());
        return response;
    }

    public void updateCustomer(CustomerAccount customerAccount){
        if (customerAccount != null
                && StringUtils.isNotBlank(customerAccount.getFilingStatus())
                && StringUtils.isNotBlank(customerAccount.getPhoneNumber())) {
            customerDao.updateFilingStatusAndPhoneNumber(customerAccount);
        } else if (customerAccount != null
                && StringUtils.isNotBlank(customerAccount.getFilingStatus())) {
            customerDao.updateFilingStatus(customerAccount);
        } else if (customerAccount != null
                && StringUtils.isNotBlank(customerAccount.getPhoneNumber())) {
            customerDao.updatePhoneNumber(customerAccount);
        }
    }
}
