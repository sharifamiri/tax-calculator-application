package com.okta.taxcalculator.converter;

import com.okta.taxcalculator.DataAccess.CustomerDTO;
import com.okta.taxcalculator.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@ConfigurationPropertiesBinding
public class CustomerDtoConverter implements Converter<String, CustomerDTO> {

    @Autowired
    CustomerService customerService;

    @Override
    public CustomerDTO convert(String source) {
        return customerService.findById(source);
    }
}
