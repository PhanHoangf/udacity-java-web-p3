package com.udacity.jdnd.course3.critter.repository.intf;

import com.udacity.jdnd.course3.critter.entity.CustomerEntity;

public interface CustomerRepositoryIntf {
    void createCustomer (CustomerEntity customer);
    CustomerEntity findCustomerById(Long id);
}
