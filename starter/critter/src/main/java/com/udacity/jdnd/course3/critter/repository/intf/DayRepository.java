package com.udacity.jdnd.course3.critter.repository.intf;

import com.udacity.jdnd.course3.critter.entity.DayEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.DayOfWeek;
import java.util.List;

public interface DayRepository extends JpaRepository<DayEntity, Long> {
    DayEntity findDayEntityByDayOfWeek(DayOfWeek dayOfWeek);
}
