package com.okta.taxcalculator.to;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.okta.taxcalculator.Enum.FilingStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateCustomerRequest {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private FilingStatus filingStatus;
}
