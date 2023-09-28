package com.udacity.jdnd.course3.critter.entity;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.Set;

@Entity
@Table(name = "day")
public class DayEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private DayOfWeek dayOfWeek;

    @ManyToMany(mappedBy = "daysAvailable")
    private Set<EmployeeEntity> employeeEntities;

    public DayEntity () {
    }

    public Long getId () {
        return id;
    }

    public void setId (Long id) {
        this.id = id;
    }


    public DayOfWeek getDayOfWeek () {
        return dayOfWeek;
    }

    public void setDayOfWeek (DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Set<EmployeeEntity> getEmployeeEntities () {
        return employeeEntities;
    }

    public void setEmployeeEntities (Set<EmployeeEntity> employeeEntities) {
        this.employeeEntities = employeeEntities;
    }
}
