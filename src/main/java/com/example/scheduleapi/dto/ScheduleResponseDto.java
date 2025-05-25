package com.example.scheduleapi.dto;

import com.example.scheduleapi.entity.Schedule;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScheduleResponseDto {

        private final Long id;

        private final String title;

        private final String todo;

        public ScheduleResponseDto(Long id, String title, String todo) {
            this.id = id;
            this.title = title;
            this.todo = todo;
        }
    public static ScheduleResponseDto toDto(Schedule schedule) {
        return new ScheduleResponseDto(schedule.getId(), schedule.getTitle(), schedule.getTodo());
    }
    }



