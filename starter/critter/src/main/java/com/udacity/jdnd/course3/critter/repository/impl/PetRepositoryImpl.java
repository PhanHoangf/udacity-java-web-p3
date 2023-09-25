package com.udacity.jdnd.course3.critter.repository.impl;

import com.udacity.jdnd.course3.critter.entity.PetEntity;
import com.udacity.jdnd.course3.critter.repository.intf.PetRepositoryIntf;
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
public class PetRepositoryImpl implements PetRepositoryIntf {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void createPet(PetEntity pet) {
        entityManager.persist(pet);
    }

    @Override
    public PetEntity findPetById(Long id) {
        return entityManager.find(PetEntity.class, id);
    }

    @Override
    public List<PetEntity> getAllPet() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<PetEntity> query = cb.createQuery(PetEntity.class);
        Root<PetEntity> root = query.from(PetEntity.class);

        query.select(root);
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public List<PetEntity> getPetsByOwnerId(Long ownerId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<PetEntity> query = cb.createQuery(PetEntity.class);
        Root<PetEntity> root = query.from(PetEntity.class);

        query.where(cb.equal(root.get("customer").get("id"), ownerId));

        return entityManager.createQuery(query).getResultList();
    }
}
