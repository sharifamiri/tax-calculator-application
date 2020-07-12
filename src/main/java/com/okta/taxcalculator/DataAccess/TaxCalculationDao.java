package com.okta.taxcalculator.DataAccess;

import com.okta.taxcalculator.Entity.TaxCalculations;

public interface TaxCalculationDao {
    void insertTax(TaxCalculations taxCalculations);
}
