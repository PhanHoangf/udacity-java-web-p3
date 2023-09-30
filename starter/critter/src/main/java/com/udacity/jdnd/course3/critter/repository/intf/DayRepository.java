package com.udacity.jdnd.course3.critter.repository.intf;

import com.udacity.jdnd.course3.critter.entity.DayEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.time.DayOfWeek;

@Transactional
public interface DayRepository extends JpaRepository<DayEntity, Long> {
    DayEntity findDayEntityByDayOfWeek(DayOfWeek dayOfWeek);
}
