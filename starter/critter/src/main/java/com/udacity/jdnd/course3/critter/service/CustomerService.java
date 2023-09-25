package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.CustomerEntity;
import com.udacity.jdnd.course3.critter.entity.PetEntity;
import com.udacity.jdnd.course3.critter.repository.impl.CustomerRepositoryImpl;
import com.udacity.jdnd.course3.critter.repository.impl.PetRepositoryImpl;
import com.udacity.jdnd.course3.critter.user.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepositoryImpl customerRepository;

    @Autowired
    private PetRepositoryImpl petRepository;

    public CustomerDTO createCustomer(CustomerDTO customer) {
        CustomerEntity customerEntity = new CustomerEntity();

        customerEntity.setName(customer.getName());
        customerEntity.setPhoneNumber(customer.getPhoneNumber());
        customerEntity.setNotes(customer.getNotes());

        customerRepository.createCustomer(customerEntity);
        return customer;
    }

    public List<CustomerDTO> getAllCustomer() {
        List<CustomerEntity> customerEntities = customerRepository.getAllCustomer();
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        for (CustomerEntity entity : customerEntities) {
            CustomerDTO customerDTO = createCustomerDto(entity);
            customerDTOList.add(customerDTO);
        }

        return customerDTOList;
    }


    private CustomerDTO createCustomerDto(CustomerEntity customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setName(customer.getName());
        customerDTO.setPhoneNumber(customer.getPhoneNumber());
        customerDTO.setNotes(customer.getNotes());
        customerDTO.setPetIds(customer.getPetList().stream().map(PetEntity::getId).collect(Collectors.toList()));
        return customerDTO;
    }

    public CustomerDTO getCustomerByPetId(Long petId) throws Exception {
        PetEntity petEntity = petRepository.findPetById(petId);
        if (!Objects.isNull(petEntity)) {
            CustomerEntity customerEntity = customerRepository.findCustomerById(petEntity.getCustomer().getId());
            return createCustomerDto(customerEntity);
        } else {
            throw new Exception("Not found");
        }
    }
}
