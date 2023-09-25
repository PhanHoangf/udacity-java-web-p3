package com.udacity.jdnd.course3.critter.repository.intf;

import com.udacity.jdnd.course3.critter.entity.CustomerEntity;

import java.util.List;

public interface CustomerRepositoryIntf {
    void createCustomer(CustomerEntity customer);

    CustomerEntity findCustomerById(Long id);

    CustomerEntity findCustomerByPetId(Long id);

    List<CustomerEntity> getAllCustomer();
}
