package com.udacity.jdnd.course3.critter.repository.impl;

import com.udacity.jdnd.course3.critter.entity.CustomerEntity;
import com.udacity.jdnd.course3.critter.repository.intf.CustomerRepositoryIntf;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class CustomerRepositoryImpl implements CustomerRepositoryIntf {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void createCustomer (CustomerEntity customer) {
        entityManager.persist( customer );
    }

    @Override
    public CustomerEntity findCustomerById (Long id) {
        return entityManager.find( CustomerEntity.class, id );
    }
}
