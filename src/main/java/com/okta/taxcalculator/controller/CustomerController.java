package com.okta.taxcalculator.controller;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.okta.taxcalculator.DataAccess.CustomerDTO;
import com.okta.taxcalculator.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

//    static AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withRegion("us-west-2").build();
//    static DynamoDB dynamoDB = new DynamoDB(client);
//    static String tableName = "CustomerAccount";
//    static Table table = dynamoDB.getTable(tableName);

    @GetMapping({"/create"})
    public String createCustomer(Model model){
        model.addAttribute("customer", new CustomerDTO());
        model.addAttribute("customers", customerService.findAll());
        return "/customer/create";
    }

    @PostMapping("/create")
    public String insertCustomer(CustomerDTO customer){

        customer.setCustomerId(UUID.randomUUID().toString());
        customerService.save(customer);
        return "redirect:/customer/create";
    }

    @GetMapping("/update/{id}")
    public String editUser(@PathVariable("id") String id, Model model){

        model.addAttribute("customer",customerService.findById(id));
        model.addAttribute("customers",customerService.findAll());

        return "/customer/update";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") String id, CustomerDTO customer, Model model){

        customer.setCustomerId(id);
        customerService.update(customer);
        return "redirect:/customer/create";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") String id){
        customerService.deleteById(id);
        return "redirect:/customer/create";
    }


}
