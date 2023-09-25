package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.CustomerEntity;
import com.udacity.jdnd.course3.critter.entity.PetEntity;
import com.udacity.jdnd.course3.critter.pet.PetDTO;
import com.udacity.jdnd.course3.critter.repository.impl.CustomerRepositoryImpl;
import com.udacity.jdnd.course3.critter.repository.impl.PetRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class PetService {
    @Autowired
    private PetRepositoryImpl petRepository;

    @Autowired
    private CustomerRepositoryImpl customerRepository;

    public PetDTO createPet (PetDTO petDTO) {
        PetEntity petEntity = new PetEntity();
        CustomerEntity customerEntity = customerRepository.findCustomerById( petDTO.getOwnerId() );

        if ( !Objects.isNull( customerEntity ) ) {
            petEntity.setCustomer( customerEntity );
            petEntity.setName( petDTO.getName() );
            petEntity.setBirthDate( petDTO.getBirthDate() );
            petEntity.setType( Integer.parseInt( petDTO.getType().toString() ) );
            petEntity.setNotes( petDTO.getNotes() );

            petRepository.createPet( petEntity );
        } else {

        }
        return petDTO;
    }
}
