package com.udacity.jdnd.course3.critter.repository.intf;

import com.udacity.jdnd.course3.critter.entity.PetEntity;

public interface PetRepositoryIntf {
    void createPet (PetEntity pet);

    PetEntity findPetById(Long id);

}
