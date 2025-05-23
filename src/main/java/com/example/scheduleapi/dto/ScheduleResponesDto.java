package com.example.scheduleapi.dto;

import com.example.scheduleapi.entity.Schedule;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScheduleResponesDto {

    private final Long id;

    private final String username;

    private final String title;

    private final String todo;

    private final LocalDateTime createdAt;

    private final LocalDateTime updatedAt;

    public ScheduleResponesDto(Long id, String username, String title, String todo, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.username = username;
        this.title = title;
        this.todo = todo;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public ScheduleResponesDto(String username, String title, String todo, LocalDateTime createdAt,LocalDateTime updatedAt) {
        this.id = null;
        this.username = username;
        this.title = title;
        this.todo = todo;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}


