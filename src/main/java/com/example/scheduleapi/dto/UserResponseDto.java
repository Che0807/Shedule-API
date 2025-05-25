package com.example.scheduleapi.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserResponseDto {

    private final String username;

    private final String email;

    private final LocalDateTime createdAt;    // 추가

    private final LocalDateTime updatedAt;    // 추가

    public UserResponseDto(String username, String email, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.username = username;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
