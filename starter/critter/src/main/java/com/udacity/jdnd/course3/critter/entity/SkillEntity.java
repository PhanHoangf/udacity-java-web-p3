package com.udacity.jdnd.course3.critter.entity;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "skill")
public class SkillEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "skills")
    private Set<EmployeeEntity> employeeEntity;

    @Column(name = "name")
    private EmployeeSkill skillName;

    public Long getId () {
        return id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    public Set<EmployeeEntity> getEmployeeEntity () {
        return employeeEntity;
    }

    public void setEmployeeEntity (Set<EmployeeEntity> employeeEntity) {
        this.employeeEntity = employeeEntity;
    }

    public EmployeeSkill getSkillName () {
        return skillName;
    }

    public void setSkillName (EmployeeSkill skillName) {
        this.skillName = skillName;
    }
}
