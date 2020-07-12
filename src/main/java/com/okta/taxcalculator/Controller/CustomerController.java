package com.okta.taxcalculator.Controller;

import com.okta.taxcalculator.Entity.Customer;
import com.okta.taxcalculator.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RequestMapping("api/taxdata")
@RestController
public class CustomerController {

//    private final AmazonDynamoDB client = AmazonDynamoDBClientBuilder.defaultClient();
//    public DynamoDBMapper mapper = new DynamoDBMapper(client);

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public String  addCustomer(@Valid @NonNull @RequestBody Customer customer){
        return customerService.addCustomer(customer);
//        mapper.save(customerService);
    }

    @GetMapping
    public List<String> getAllCustomers(String id){
        return customerService.getAllCustomers(id);
    }

    @GetMapping(path = "{id}")
    public String getCustomerById(@PathVariable("id") String id){
        return customerService.getCustomerById(id);
    }

    @DeleteMapping(path = "{id}")
    public void deleteCustomerById(@PathVariable("id") String id){
        customerService.deleteCustomer(id);
    }

    @PutMapping(path = "{id}")
    public String updateCustomer(@PathVariable("id") String id, @RequestBody Customer customer){
        return customerService.updateCustomer(customer);
    }
}
