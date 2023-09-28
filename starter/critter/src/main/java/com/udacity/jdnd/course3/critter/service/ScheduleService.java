package com.udacity.jdnd.course3.critter.service;

import com.google.common.collect.Lists;
import com.udacity.jdnd.course3.critter.entity.EmployeeEntity;
import com.udacity.jdnd.course3.critter.entity.PetEntity;
import com.udacity.jdnd.course3.critter.entity.ScheduleEntity;
import com.udacity.jdnd.course3.critter.entity.SkillEntity;
import com.udacity.jdnd.course3.critter.repository.impl.EmployeeRepositoryImpl;
import com.udacity.jdnd.course3.critter.repository.impl.PetRepositoryImpl;
import com.udacity.jdnd.course3.critter.repository.impl.ScheduleRepositoryImpl;
import com.udacity.jdnd.course3.critter.repository.intf.SkillRepository;
import com.udacity.jdnd.course3.critter.schedule.ScheduleDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeRequestDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import com.udacity.jdnd.course3.critter.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ScheduleService {
    @Autowired
    ScheduleRepositoryImpl scheduleRepository;

    @Autowired
    EmployeeRepositoryImpl employeeRepository;

    @Autowired
    PetRepositoryImpl petRepository;

    @Autowired
    SkillRepository skillRepository;

    public List<ScheduleDTO> getAllSchedules() {
        List<ScheduleEntity> scheduleEntities = scheduleRepository.getAllSchedules();
        List<ScheduleDTO> scheduleDTOS = new ArrayList<>();

        for (ScheduleEntity entity : scheduleEntities) {
            ScheduleDTO scheduleDTO = new ScheduleDTO();
            scheduleDTO = toScheduleDto(entity);
            scheduleDTOS.add(scheduleDTO);
        }

        return scheduleDTOS;
    }

    ScheduleDTO toScheduleDto(ScheduleEntity entity) {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setId(entity.getId());
        scheduleDTO.setDate(entity.getDate());
        scheduleDTO.setActivities(Utils.<SkillEntity>collectionAsStream(entity.getSkillEntities())
                .map(SkillEntity::getSkillName)
                .collect(Collectors.toSet()));
        scheduleDTO.setEmployeeIds(
                Utils.<EmployeeEntity>collectionAsStream(entity.getEmployeeEntities())
                        .map(EmployeeEntity::getId)
                        .collect(Collectors.toList())
        );
        scheduleDTO.setPetIds(
                Utils.<PetEntity>collectionAsStream(entity.getPetEntities())
                        .map(PetEntity::getId)
                        .collect(Collectors.toList())
        );

        return scheduleDTO;
    }

    public ScheduleDTO createSchedule(ScheduleDTO scheduleDTO) throws Exception {
        Long employeeId = scheduleDTO.getEmployeeIds().get(0);
        Long petId = scheduleDTO.getPetIds().get(0);
        Set<EmployeeSkill> activities = scheduleDTO.getActivities();
        LocalDate date = scheduleDTO.getDate();

        EmployeeRequestDTO employeeDTO = new EmployeeRequestDTO();
        employeeDTO.setDate(date);
        employeeDTO.setSkills(activities);

        List<EmployeeEntity> employeeEntities = employeeRepository.findEmployeesForService(employeeDTO);
        List<EmployeeEntity> employeeEntitiesToCreateSchedule = new ArrayList<>();

        for (EmployeeEntity em : employeeEntities) {
            if (em.getId().equals(employeeId)) {
                employeeEntitiesToCreateSchedule.add(em);
            } else {
                throw new Exception("Employee not available");
            }
        }

        PetEntity petEntity = petRepository.findPetById(petId);

        List<SkillEntity> skillEntities = new ArrayList<>();

        for (EmployeeSkill skill : activities) {
            SkillEntity skillEntity = skillRepository.findFirstBySkillName(skill);
            skillEntities.add(skillEntity);
        }

        ScheduleEntity scheduleEntity = new ScheduleEntity();

        scheduleEntity.setEmployeeEntities(employeeEntitiesToCreateSchedule);
        scheduleEntity.setPetEntities(Lists.newArrayList(petEntity));
        scheduleEntity.setSkillEntities(skillEntities);
        scheduleEntity.setDate(date);

        scheduleRepository.createSchedule(scheduleEntity);

        scheduleDTO.setId(scheduleEntity.getId());
        return scheduleDTO;
    }
}
