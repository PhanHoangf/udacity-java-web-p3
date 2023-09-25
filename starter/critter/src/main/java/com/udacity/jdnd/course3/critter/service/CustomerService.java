package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.CustomerEntity;
import com.udacity.jdnd.course3.critter.repository.impl.CustomerRepositoryImpl;
import com.udacity.jdnd.course3.critter.user.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepositoryImpl customerRepository;

    public CustomerDTO createCustomer (CustomerDTO customer) {
        CustomerEntity customerEntity = new CustomerEntity();

        customerEntity.setName( customer.getName() );
        customerEntity.setPhoneNumber( customer.getPhoneNumber() );
        customerEntity.setNotes( customer.getNotes() );

        customerRepository.createCustomer( customerEntity );
        return customer;
    }


    private CustomerDTO createCustomerDto (CustomerEntity customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId( customer.getId() );
        customerDTO.setName( customer.getName() );
        customerDTO.setPhoneNumber( customer.getPhoneNumber() );
        customerDTO.setNotes( customer.getNotes() );
        return customerDTO;
    }
}
