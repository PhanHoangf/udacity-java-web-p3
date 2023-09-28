package com.udacity.jdnd.course3.critter.repository.intf;

import com.udacity.jdnd.course3.critter.entity.SkillEntity;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<SkillEntity, Long> {
    SkillEntity findFirstBySkillName(EmployeeSkill employeeSkill);
}
