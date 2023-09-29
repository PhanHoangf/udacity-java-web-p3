package com.udacity.jdnd.course3.critter.repository.impl;

import com.udacity.jdnd.course3.critter.entity.CustomerEntity;
import com.udacity.jdnd.course3.critter.entity.EmployeeEntity;
import com.udacity.jdnd.course3.critter.entity.PetEntity;
import com.udacity.jdnd.course3.critter.entity.ScheduleEntity;
import com.udacity.jdnd.course3.critter.repository.intf.ScheduleEntityRepositoryIntf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Repository
@Transactional
public class ScheduleRepositoryImpl implements ScheduleEntityRepositoryIntf {
    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    PetRepositoryImpl petRepository;

    @Autowired
    EmployeeRepositoryImpl employeeRepository;

    @Autowired
    CustomerRepositoryImpl customerRepository;

    @Override
    public List<ScheduleEntity> getScheduleEntityByPetId (
            Long id
    ) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ScheduleEntity> query = cb.createQuery( ScheduleEntity.class );
        Root<ScheduleEntity> root = query.from( ScheduleEntity.class );


        PetEntity petEntity = petRepository.findPetById( id );


        if ( !Objects.isNull( petEntity ) ) {
            query.select( root ).where( cb.isMember( petEntity, root.get( "petEntities" ) ) );
        } else {
            throw new RuntimeException( "Not found" );
        }

        return entityManager.createQuery( query ).getResultList();
    }

    @Override
    public List<ScheduleEntity> getScheduleEntityByEmployeeId (
            Long employeeId
    ) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ScheduleEntity> query = cb.createQuery( ScheduleEntity.class );
        Root<ScheduleEntity> root = query.from( ScheduleEntity.class );


        EmployeeEntity employeeEntity = employeeRepository.findEmployeeById( employeeId );


        if ( !Objects.isNull( employeeEntity ) ) {
            query.select( root ).where( cb.isMember( employeeEntity, root.get( "employeeEntities" ) ) );
        } else {
            throw new RuntimeException( "Not found" );
        }

        return entityManager.createQuery( query ).getResultList();
    }

    @Override
    public List<ScheduleEntity> getScheduleEntityByCustomerId (
            Long customerId
    ) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ScheduleEntity> query = cb.createQuery( ScheduleEntity.class );
        Root<ScheduleEntity> root = query.from( ScheduleEntity.class );

        CustomerEntity customerEntity = customerRepository.findCustomerById( customerId );
        List<PetEntity> petEntities = petRepository.getPetsByOwnerId( customerId );

        if ( !Objects.isNull( customerEntity ) ) {
            query.select( root ).where( cb.isMember( petEntities, root.get( "petEntities" ) ) );
        } else {
            throw new RuntimeException( "Not found" );
        }

        return entityManager.createQuery( query ).getResultList();
    }

    @Override
    public List<ScheduleEntity> getAllSchedules () {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ScheduleEntity> query = cb.createQuery( ScheduleEntity.class );
        Root<ScheduleEntity> root = query.from( ScheduleEntity.class );

        query.select( root );
        return entityManager.createQuery( query ).getResultList();
    }

    @Override
    public void createSchedule (ScheduleEntity scheduleEntity) {
        entityManager.persist( scheduleEntity );
    }
}
