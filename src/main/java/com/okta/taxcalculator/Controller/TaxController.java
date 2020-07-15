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
    public List<String> getAllTaxes(){
        return taxService.getAllTaxes();
    }

    @GetMapping(path = "{customerId}")
    public String getTaxByCustomerId(@PathVariable("customerId") String customerId){
        return taxService.getTaxByCustomerId(customerId);
    }

    @DeleteMapping(path = "{customerId}")
    public void deleteTaxByCustomerId(@PathVariable("customerId") String customerId){
        taxService.deleteTaxByCustomerId(customerId);
    }

    @PutMapping(path = "{customerId}")
    public String updateGrossIncome(@PathVariable("customerId") String customerId, @RequestBody Tax tax){
        return taxService.updateGrossIncome(customerId, tax);
    }
}
