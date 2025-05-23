package com.example.scheduleapi.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
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

    public ScheduleResponesDto(String username, String title, String todo) {
        this.username = username;
        this.title = title;
        this.todo = todo;
        this.id = null;          // id는 아직 없으므로 null
        this.createdAt = null;   // createdAt도 null
        this.updatedAt = null;   // updatedAt도 null
    }
}

