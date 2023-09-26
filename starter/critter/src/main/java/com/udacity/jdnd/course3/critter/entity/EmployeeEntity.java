package com.udacity.jdnd.course3.critter.entity;

import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "employee")
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Nationalized
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "skill_employee",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private Set<SkillEntity> skills;

    @ManyToMany
    @JoinTable(
            name = "day_employee_available",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "day_id")
    )
    private Set<DayEntity> daysAvailable;

    public EmployeeEntity () {
    }

    public Long getId () {
        return id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public Set<SkillEntity> getSkills () {
        return skills;
    }

    public void setSkills (Set<SkillEntity> skills) {
        this.skills = skills;
    }

    public Set<DayEntity> getDaysAvailable () {
        return daysAvailable;
    }

    public void setDaysAvailable (Set<DayEntity> daysAvailable) {
        this.daysAvailable = daysAvailable;
    }
}
