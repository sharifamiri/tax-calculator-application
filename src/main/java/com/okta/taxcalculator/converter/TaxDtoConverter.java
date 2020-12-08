package com.okta.taxcalculator.converter;

import com.okta.taxcalculator.DataAccess.TaxDTO;
import com.okta.taxcalculator.Service.TaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@ConfigurationPropertiesBinding
public class TaxDtoConverter implements Converter<String, TaxDTO> {

    @Autowired
    TaxService taxService;

    @Override
    public TaxDTO convert(String source) {
        return taxService.findById(source);
    }
}
