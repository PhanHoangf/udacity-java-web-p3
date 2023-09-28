package com.udacity.jdnd.course3.critter.repository.impl;

import com.udacity.jdnd.course3.critter.entity.DayEntity;
import com.udacity.jdnd.course3.critter.entity.EmployeeEntity;
import com.udacity.jdnd.course3.critter.entity.SkillEntity;
import com.udacity.jdnd.course3.critter.repository.intf.DayRepository;
import com.udacity.jdnd.course3.critter.repository.intf.EmployeeRepositoryIntf;
import com.udacity.jdnd.course3.critter.repository.intf.SkillRepository;
import com.udacity.jdnd.course3.critter.user.EmployeeRequestDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
@Transactional
public class EmployeeRepositoryImpl implements EmployeeRepositoryIntf {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    DayRepository dayRepository;

    @Autowired
    SkillRepository skillRepository;

    public void createEmployee(EmployeeEntity employeeEntity) {
        entityManager.persist(employeeEntity);
    }

    @Override
    public EmployeeEntity findEmployeeById(Long id) {
        return entityManager.find(EmployeeEntity.class, id);
    }

    @Override
    public void setAvailability(EmployeeEntity employeeEntity) {
        entityManager.merge(employeeEntity);
    }

    @Override
    public List<EmployeeEntity> findEmployeesForService(EmployeeRequestDTO employeeDTO) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<EmployeeEntity> query = cb.createQuery(EmployeeEntity.class);

        Root<EmployeeEntity> root = query.from(EmployeeEntity.class);

        DayEntity dayEntity = dayRepository.findDayEntityByDayOfWeek(employeeDTO.getDate()
                .getDayOfWeek());

        List<SkillEntity> skillEntities = new ArrayList<>();
        for (EmployeeSkill skill : employeeDTO.getSkills()) {
            SkillEntity skillEntity = skillRepository.findFirstBySkillName(skill);
            skillEntities.add(skillEntity);
        }

        List<Predicate> predicateList = new ArrayList<>();
        for (SkillEntity skill : skillEntities) {
            predicateList.add(cb.isMember(skill, root.get("skills")));
        }
        predicateList.add(cb.isMember(dayEntity, root.get("daysAvailable")));

        query.select(root).where(cb.and(
                predicateList.toArray(new Predicate[0])
        ));

        return entityManager.createQuery(query).getResultList();
    }
}
