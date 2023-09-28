package com.udacity.jdnd.course3.critter.repository.impl;

import com.udacity.jdnd.course3.critter.entity.ScheduleEntity;
import com.udacity.jdnd.course3.critter.repository.intf.ScheduleEntityRepositoryIntf;
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
public class ScheduleRepositoryImpl implements ScheduleEntityRepositoryIntf {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<ScheduleEntity> getScheduleEntityByPetId(
            Long petId
    ) {
        return null;
    }

    @Override
    public List<ScheduleEntity> getScheduleEntityByEmployeeId(
            Long employeeId
    ) {
        return null;
    }

    @Override
    public List<ScheduleEntity> getScheduleEntityByCustomerId(
            Long customerId
    ) {
        return null;
    }

    @Override
    public List<ScheduleEntity> getAllSchedules() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ScheduleEntity> query = cb.createQuery(ScheduleEntity.class);
        Root<ScheduleEntity> root = query.from(ScheduleEntity.class);

        query.select(root);
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public void createSchedule(ScheduleEntity scheduleEntity) {
        entityManager.persist(scheduleEntity);
    }
}
