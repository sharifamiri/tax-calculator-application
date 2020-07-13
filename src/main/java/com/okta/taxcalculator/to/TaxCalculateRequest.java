package com.okta.taxcalculator.to;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.okta.taxcalculator.Enum.FilingStatus;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaxCalculateRequest {

    @NonNull
    private String accountId;
    @NonNull
    private double grossIncome;
}
