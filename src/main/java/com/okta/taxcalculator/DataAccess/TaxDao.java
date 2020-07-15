package com.okta.taxcalculator.DataAccess;

import com.okta.taxcalculator.Entity.Customer;
import com.okta.taxcalculator.Entity.Tax;

import java.util.List;

public interface TaxDao {

    String insertTax(Tax tax);

    List<String> selectAllTaxes();

    String selectTaxByCustomerId(String customerId);

    void deleteTaxByCustomerId(String customerId);

    String updateGrossIncome(String customerId, Tax tax);
}
