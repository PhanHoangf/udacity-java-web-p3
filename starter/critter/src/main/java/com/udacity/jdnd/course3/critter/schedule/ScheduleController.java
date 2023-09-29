package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    @Autowired
    ScheduleService scheduleService;

    @PostMapping
    public ScheduleDTO createSchedule (@RequestBody ScheduleDTO scheduleDTO) throws Exception {
        return scheduleService.createSchedule( scheduleDTO );
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules () {
        return scheduleService.getAllSchedules();
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet (@PathVariable long petId) {
        return scheduleService.getScheduleEntityByPetId( petId );
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee (@PathVariable long employeeId) {
        return scheduleService.getScheduleEntityByEmployeeId( employeeId );
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer (@PathVariable long customerId) {
        return scheduleService.getScheduleEntityByCustomerId( customerId );
    }
}
