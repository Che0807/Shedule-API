package com.example.scheduleapi.dto;

import lombok.Getter;

@Getter
public class ScheduleUpdateRequestDto {
    private String username;
    private String title;
    private String todo;
}
