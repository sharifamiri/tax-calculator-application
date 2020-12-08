package com.okta.taxcalculator.implementation;

import com.okta.taxcalculator.DataAccess.CustomerDTO;
import com.okta.taxcalculator.DataAccess.TaxDTO;
import com.okta.taxcalculator.Service.TaxService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaxServiceImpl extends AbstractMapService<TaxDTO, String> implements TaxService {
    @Override
    public TaxDTO save(TaxDTO object) {
        return super.save(object.getCustomerID(),object);
    }

    @Override
    public TaxDTO findById(String id) {
        return super.findById(id);
    }

    @Override
    public List<TaxDTO> findAll() {
        return super.findAll();
    }

    @Override
    public void update(TaxDTO object) {
        super.update(object.getCustomerID(), object);
    }

    @Override
    public void deleteById(String id) {
        super.deleteById(id);
    }

    @Override
    public void delete(TaxDTO object) {
        super.delete(object);
    }
}
