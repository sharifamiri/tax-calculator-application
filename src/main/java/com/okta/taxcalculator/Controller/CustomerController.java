package com.okta.taxcalculator.Controller;

import com.okta.taxcalculator.Entity.Customer;
import com.okta.taxcalculator.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RequestMapping("api/customer")
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
    }

    @GetMapping
    public List<String> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    @GetMapping(path = "{customerId}")
    public String getCustomerByCustomerId(@PathVariable("customerId") String customerId){
        return customerService.getCustomerByCustomerId(customerId);
    }

    @DeleteMapping(path = "{customerId}")
    public void deleteCustomerByCustomerId(@PathVariable("customerId") String customerId){
        customerService.deleteCustomerByCustomerId(customerId);
    }

    @PutMapping(path = "{customerId}")
    public String updateCustomerNameAndFilingStatus(@PathVariable("customerId") String customerId, @RequestBody Customer customer){
        return customerService.updateCustomerNameAndFilingStatus(customerId, customer);
    }

    //localhost:8080/api/customer?customerId=137292c9-2681-4e3d-ab25-786f892d2033
    @PutMapping
    public String updateCustomerFilingStatus(@RequestParam(value="customerId") String customerId, @RequestBody Customer customer){
        return customerService.updateCustomerFilingStatus(customerId, customer);
    }
}
