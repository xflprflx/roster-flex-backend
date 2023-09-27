package com.rosterflex.application.tests;

import com.rosterflex.application.dtos.ScheduleTypeDTO;
import com.rosterflex.application.models.ScheduleType;

import java.time.temporal.ChronoUnit;

public class Factory {

    public static ScheduleType createScheduleType() {
        ScheduleType scheduleType = new ScheduleType(1L, "12x36", 12.0, 36.0, ChronoUnit.HOURS, 400, 1);
        return scheduleType;
    }

    public static ScheduleTypeDTO createScheduleTypeDTO() {
        ScheduleType scheduleType = createScheduleType();
        return new ScheduleTypeDTO(scheduleType);
    }
}
