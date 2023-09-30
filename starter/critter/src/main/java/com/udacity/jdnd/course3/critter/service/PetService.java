package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.CustomerEntity;
import com.udacity.jdnd.course3.critter.entity.PetEntity;
import com.udacity.jdnd.course3.critter.pet.PetDTO;
import com.udacity.jdnd.course3.critter.pet.PetType;
import com.udacity.jdnd.course3.critter.repository.impl.CustomerRepositoryImpl;
import com.udacity.jdnd.course3.critter.repository.impl.PetRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class PetService {
    @Autowired
    private PetRepositoryImpl petRepository;

    @Autowired
    private CustomerRepositoryImpl customerRepository;

    public PetDTO createPet(PetDTO petDTO) {
        PetEntity petEntity = new PetEntity();
        CustomerEntity customerEntity = customerRepository.findCustomerById(petDTO.getOwnerId());

        if (!Objects.isNull(customerEntity)) {
            petEntity.setCustomer(customerEntity);
            petEntity.setName(petDTO.getName());
            petEntity.setBirthDate(petDTO.getBirthDate());
            petEntity.setType(petDTO.getType().toString());
            petEntity.setNotes(petDTO.getNotes());

            petRepository.createPet(petEntity);

            List<PetEntity> customerPetList = customerEntity.getPetList() != null ? customerEntity.getPetList() : new ArrayList<>();
            customerPetList.add(petEntity);
            customerEntity.setPetList(customerPetList);

            customerRepository.createCustomer(customerEntity);

            petDTO.setId(petEntity.getId());
        } else {
            //TODO: Throw error customer not found
        }
        return petDTO;
    }

    public List<PetDTO> getAllPet() {
        List<PetEntity> petEntities = petRepository.getAllPet();

        return petEntities.stream()
                .map(this::createPetDto)
                .collect(Collectors.toList());
    }

    public PetDTO findPetById(Long id) {
        PetEntity petEntity = petRepository.findPetById(id);

        return createPetDto(petEntity);
    }

    public List<PetDTO> getPetsByOwnerId(Long ownerId) {
        List<PetEntity> petEntities = petRepository.getPetsByOwnerId(ownerId);
        return petEntities.stream()
                .map(this::createPetDto)
                .collect(Collectors.toList());
    }

    private PetDTO createPetDto(PetEntity pet) {
        PetDTO petDTO = new PetDTO();

        petDTO.setId(pet.getId());
        petDTO.setName(pet.getName());
        petDTO.setType(PetType.valueOf(pet.getType()));
        petDTO.setNotes(pet.getNotes());
        petDTO.setBirthDate(pet.getBirthDate());
        petDTO.setOwnerId(pet.getCustomer().getId());

        return petDTO;
    }
}
