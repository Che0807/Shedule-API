package com.example.scheduleapi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScheduleResponseDto {

    private final Long id;

    private final String username;

    private final String title;

    private final String todo;

    private final LocalDateTime createdAt;

    private final LocalDateTime updatedAt;

    public ScheduleResponseDto(Long id, String username, String title, String todo, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.username = username;
        this.title = title;
        this.todo = todo;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public ScheduleResponseDto(String username, String title, String todo, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = null;
        this.username = username;
        this.title = title;
        this.todo = todo;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}


