package com.udacity.jdnd.course3.critter.repository.intf;

import com.udacity.jdnd.course3.critter.entity.ScheduleEntity;
import com.udacity.jdnd.course3.critter.schedule.ScheduleDTO;

import java.util.List;

public interface ScheduleEntityRepositoryIntf {
    List<ScheduleEntity> getAllSchedules();

    List<ScheduleEntity> getScheduleEntityByPetId(Long petId) throws Exception;

    List<ScheduleEntity> getScheduleEntityByEmployeeId(Long employeeId);

    List<ScheduleEntity> getScheduleEntityByCustomerId(Long customerId);

    void createSchedule(ScheduleEntity scheduleEntity);
}
