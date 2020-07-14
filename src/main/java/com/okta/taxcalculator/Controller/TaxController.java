package com.okta.taxcalculator.Controller;

import com.okta.taxcalculator.Entity.Customer;
import com.okta.taxcalculator.Entity.Tax;
import com.okta.taxcalculator.Service.CustomerService;
import com.okta.taxcalculator.Service.TaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RequestMapping("api/tax")
@RestController
public class TaxController {

    private final TaxService taxService;

    @Autowired
    public TaxController(TaxService taxService) {
        this.taxService = taxService;
    }

    @PostMapping
    public String  addTax(@Valid @NonNull @RequestBody Tax tax){
        return taxService.addTax(tax);
    }

    @GetMapping
    public List<String> getAllCustomers(){
        return taxService.getAllCustomers();
    }

    @GetMapping(path = "{customerId}")
    public String getCustomerById(@PathVariable("customerId") String customerId){
        return taxService.getCustomerById(customerId);
    }

    @DeleteMapping(path = "{customerId}")
    public void deleteCustomerById(@PathVariable("customerId") String customerId){
        taxService.deleteCustomer(customerId);
    }

    @PutMapping(path = "{customerId}")
    public String updateCustomer(@PathVariable("customerId") String customerId, @RequestBody Customer customer){
        return taxService.updateCustomer(customerId, customer);
    }
}
