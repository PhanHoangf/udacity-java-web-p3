package com.udacity.jdnd.course3.critter.repository.impl;

import com.udacity.jdnd.course3.critter.entity.CustomerEntity;
import com.udacity.jdnd.course3.critter.entity.PetEntity;
import com.udacity.jdnd.course3.critter.repository.intf.PetRepositoryIntf;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class PetRepositoryImpl implements PetRepositoryIntf {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public PetEntity createPet (PetEntity pet) {
        entityManager.persist( pet );
        return pet;
    }
}