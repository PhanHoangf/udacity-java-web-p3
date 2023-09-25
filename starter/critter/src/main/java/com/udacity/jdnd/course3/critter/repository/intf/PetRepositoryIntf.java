package com.udacity.jdnd.course3.critter.repository.intf;

import com.udacity.jdnd.course3.critter.entity.PetEntity;

import java.util.List;

public interface PetRepositoryIntf {
    void createPet(PetEntity pet);

    PetEntity findPetById(Long id);

    List<PetEntity> getAllPet();
    List<PetEntity> getPetsByOwnerId(Long ownerId);
}
