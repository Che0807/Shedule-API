package com.example.scheduleapi.dto;

import lombok.Getter;

@Getter

public class ScheduleRequestDto {

    private final String username;

    private final String title;

    private final String todo;

    public ScheduleRequestDto(String username, String title, String todo) {
        this.username = username;
        this.title = title;
        this.todo = todo;
    }
}
