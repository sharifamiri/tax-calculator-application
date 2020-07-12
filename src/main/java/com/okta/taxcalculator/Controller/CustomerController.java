package com.okta.taxcalculator.Controller;

import com.okta.taxcalculator.Entity.CustomerAccount;
import com.okta.taxcalculator.Service.CustomerService;
import com.okta.taxcalculator.Service.TaxService;
import com.okta.taxcalculator.to.CreateCustomerResponse;
import com.okta.taxcalculator.to.TaxCalculateRequest;
import com.okta.taxcalculator.to.TaxCalculateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RequestMapping("api/taxdata")
@RestController
public class CustomerController {

    private final CustomerService customerService;
    private final TaxService taxService;


    @Autowired
    public CustomerController(CustomerService customerService, TaxService taxService) {
        this.customerService = customerService;
        this.taxService = taxService;
    }

    @PostMapping
    public String  addCustomer(@Valid @NonNull @RequestBody CustomerAccount customerAccount){
        return customerService.addCustomer(customerAccount);
//        mapper.save(customerService);
    }

    // products
    @PostMapping
    public TaxCalculateResponse taxCalculate(@Valid @NonNull @RequestBody TaxCalculateRequest taxCalculateRequest) throws Exception {
        return taxService.calculateTax(taxCalculateRequest);
    }

    @GetMapping
    public List<CreateCustomerResponse> getAllCustomers(String id) throws Exception {
        return customerService.getAllCustomers(id);
    }

    @GetMapping(path = "{id}")
    public CreateCustomerResponse getCustomerById(@PathVariable("id") String id) throws Exception {
        return customerService.getCustomerById(id);
    }

    @DeleteMapping(path = "{id}")
    public void deleteCustomerById(@PathVariable("id") String id){
        customerService.deleteCustomer(id);
    }

    @PutMapping()
    public void updateCustomer(@RequestBody CustomerAccount customerAccount){
        customerService.updateCustomer(customerAccount);
    }
}
