package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.DayEntity;
import com.udacity.jdnd.course3.critter.entity.EmployeeEntity;
import com.udacity.jdnd.course3.critter.entity.SkillEntity;
import com.udacity.jdnd.course3.critter.repository.impl.EmployeeRepositoryImpl;
import com.udacity.jdnd.course3.critter.repository.intf.DayRepository;
import com.udacity.jdnd.course3.critter.repository.intf.SkillRepository;
import com.udacity.jdnd.course3.critter.user.EmployeeDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeRequestDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import com.udacity.jdnd.course3.critter.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepositoryImpl employeeRepository;

    @Autowired
    SkillRepository skillRepository;

    @Autowired
    DayRepository dayRepository;

    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        Set<EmployeeSkill> skills = employeeDTO.getSkills();
        Set<DayOfWeek> dayOfWeeks = employeeDTO.getDaysAvailable();

        EmployeeEntity entity = new EmployeeEntity();

        Set<SkillEntity> skillEntities = new HashSet<>();
        Set<DayEntity> dayEntities = new HashSet<>();

        for (EmployeeSkill skill : skills) {
            SkillEntity skillEntity = skillRepository.findFirstBySkillName(skill);

            if (skillEntity == null) {
                skillEntity = new SkillEntity();
            }

            skillEntity.setSkillName(skill);

            skillEntities.add(skillEntity);
        }

        if (!dayOfWeeks.isEmpty()) {
            for (DayOfWeek dayOfWeek : dayOfWeeks) {
                DayEntity dayEntity = dayRepository.findDayEntityByDayOfWeek(dayOfWeek);

                if (dayEntity == null) {
                    dayEntity = new DayEntity();
                }

                dayEntity.setDayOfWeek(dayOfWeek);

                dayEntities.add(dayEntity);
            }
        }

        entity.setName(employeeDTO.getName());
        entity.setSkills(skillEntities);
        entity.setDaysAvailable(dayEntities);

        employeeRepository.createEmployee(entity);

        employeeDTO.setId(entity.getId());
        return employeeDTO;
    }

    public EmployeeDTO findEmployeeById(Long id) {
        EmployeeEntity employeeEntity = employeeRepository.findEmployeeById(id);

        return createEmployeeDto(employeeEntity);
    }

    public void setEmployeeAvailability(
            Set<DayOfWeek> daysAvailable,
            long employeeId
    ) {
        EmployeeEntity employeeEntity = employeeRepository.findEmployeeById(employeeId);

        employeeEntity.setDaysAvailable(daysAvailable.stream()
                .map(dayOfWeek -> {
                    DayEntity dayEntity = dayRepository.findDayEntityByDayOfWeek(dayOfWeek);

                    if (dayEntity == null) {
                        dayEntity = new DayEntity();
                    }

                    dayEntity.setDayOfWeek(dayOfWeek);

                    return dayEntity;
                })
                .collect(Collectors.toSet()));

        employeeRepository.setAvailability(employeeEntity);
    }

    public List<EmployeeDTO> findEmployeesForService(EmployeeRequestDTO employeeRequestDTO) {
        List<EmployeeEntity> employeeEntities = employeeRepository.findEmployeesForService(employeeRequestDTO);

        return employeeEntities.stream()
                .map(this::createEmployeeDto)
                .collect(Collectors.toList());
    }

    private EmployeeDTO createEmployeeDto(EmployeeEntity employeeEntity) {
        EmployeeDTO employeeDTO = new EmployeeDTO();

        employeeDTO.setId(employeeEntity.getId());
        employeeDTO.setName(employeeEntity.getName());
        employeeDTO.setSkills(Utils.<SkillEntity>collectionAsStream(employeeEntity.getSkills())
                .map(SkillEntity::getSkillName)
                .collect(Collectors.toSet()));

        employeeDTO.setDaysAvailable(Utils.<DayEntity>collectionAsStream(employeeEntity.getDaysAvailable())
                .map(DayEntity::getDayOfWeek)
                .collect(Collectors.toSet()));

        return employeeDTO;
    }
}
