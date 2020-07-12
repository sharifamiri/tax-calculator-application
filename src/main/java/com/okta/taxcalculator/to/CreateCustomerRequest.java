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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public FilingStatus getFilingStatus() {
        return filingStatus;
    }

    public void setFilingStatus(FilingStatus filingStatus) {
        this.filingStatus = filingStatus;
    }

    private String phoneNumber;
    private FilingStatus filingStatus;
}
