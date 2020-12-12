package com.okta.taxcalculator.controller;

import com.okta.taxcalculator.DataAccess.TaxDTO;
import com.okta.taxcalculator.Service.CustomerService;
import com.okta.taxcalculator.Service.TaxService;
import com.okta.taxcalculator.Utilities.TaxCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tax")
public class TaxController {

    @Autowired
    CustomerService customerService;
    @Autowired
    TaxService taxService;

    @GetMapping({"/create"})
    public String createTax( String id, TaxDTO tax, Model model){
        model.addAttribute("tax", new TaxDTO());
        model.addAttribute("customers", customerService.findAll());
        model.addAttribute("taxes", taxService.findAll());
        return "/tax/create";
    }

    @PostMapping("/create")
    public String insertTax(Double income,TaxDTO tax){

        tax.setFilingStatus(customerService.findById(tax.getCustomerID()).getFilingStatus());
        tax.setCustomerName(customerService.findById(tax.getCustomerID()).getFirstName()+' '+customerService.findById(tax.getCustomerID()).getLastName());

        TaxCalculator taxCalculator = new TaxCalculator(customerService.findById(tax.getCustomerID()).getFilingStatus().getValue(), tax.getGrossIncome());
        tax.setTaxAmount(taxCalculator.taxAmountCalc());
        taxService.save(tax);
        return "redirect:/tax/create";
    }

    @GetMapping("/update/{id}")
    public String editTax(@PathVariable("id") String id, Model model){

        model.addAttribute("tax", taxService.findById(id));
        model.addAttribute("customers",customerService.findAll());
        model.addAttribute("taxes", taxService.findAll());

        return "/tax/update";
    }

    @PostMapping("/update/{id}")
    public String updateTax(@PathVariable("id") String id, TaxDTO tax, Model model){

        tax.setCustomerID(id);
        taxService.update(tax);
        return "redirect:/tax/create";
    }

    @GetMapping("/delete/{id}")
    public String deleteTax(@PathVariable("id") String id){
        taxService.deleteById(id);
        return "redirect:/tax/create";
    }
}
