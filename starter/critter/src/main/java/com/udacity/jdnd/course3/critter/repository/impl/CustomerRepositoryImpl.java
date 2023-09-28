package com.udacity.jdnd.course3.critter.repository.impl;

import com.udacity.jdnd.course3.critter.entity.CustomerEntity;
import com.udacity.jdnd.course3.critter.repository.intf.CustomerRepositoryIntf;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CustomerRepositoryImpl implements CustomerRepositoryIntf {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void createCustomer(CustomerEntity customer) {
        if (customer.getId() != null) {
            entityManager.merge(customer);
        } else {
            entityManager.persist(customer);
        }
    }

    @Override
    public CustomerEntity findCustomerById(Long id) {
        return entityManager.find(CustomerEntity.class, id);
    }

    @Override
    public CustomerEntity findCustomerByPetId(Long id) {
        return null;
    }

    @Override
    public List<CustomerEntity> getAllCustomer() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<CustomerEntity> query = cb.createQuery(CustomerEntity.class);

        Root<CustomerEntity> root = query.from(CustomerEntity.class);

        query.select(root);
        return entityManager.createQuery(query).getResultList();
    }
}
