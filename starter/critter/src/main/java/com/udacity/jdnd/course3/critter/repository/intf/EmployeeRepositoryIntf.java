package com.udacity.jdnd.course3.critter.repository.intf;

import com.udacity.jdnd.course3.critter.entity.EmployeeEntity;
import com.udacity.jdnd.course3.critter.user.EmployeeRequestDTO;

import java.util.List;

public interface EmployeeRepositoryIntf {
    public void createEmployee(EmployeeEntity employeeEntity);

    public EmployeeEntity findEmployeeById(Long id);

    public void setAvailability(EmployeeEntity employeeEntity);

    public List<EmployeeEntity> findEmployeesForService(EmployeeRequestDTO employeeDTO);

}
