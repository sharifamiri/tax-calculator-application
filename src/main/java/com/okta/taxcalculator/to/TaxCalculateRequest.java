package com.okta.taxcalculator.to;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.okta.taxcalculator.Enum.FilingStatus;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaxCalculateRequest {
    @NonNull
    private String accountId;
    @NonNull
    private double grossIncome;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public double getGrossIncome() {
        return grossIncome;
    }

    public void setGrossIncome(double grossIncome) {
        this.grossIncome = grossIncome;
    }
}
