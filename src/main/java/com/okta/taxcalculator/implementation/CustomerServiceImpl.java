package com.okta.taxcalculator.implementation;

import com.okta.taxcalculator.DataAccess.CustomerDTO;
import com.okta.taxcalculator.Service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl extends AbstractMapService<CustomerDTO,String> implements CustomerService {
    @Override
    public CustomerDTO save(CustomerDTO object) {
        return super.save(object.getCustomerId(),object);
    }

    @Override
    public CustomerDTO findById(String id) {
        return super.findById(id);
    }

    @Override
    public List<CustomerDTO> findAll() {
        return super.findAll();
    }

    @Override
    public void update(CustomerDTO object) {
        super.update(object.getCustomerId(), object);
    }

    @Override
    public void deleteById(String id) {
        super.deleteById(id);
    }

    @Override
    public void delete(CustomerDTO object) {
        super.delete(object);
    }


}
