package com.example.scheduleapi.controller;

import com.example.scheduleapi.dto.ScheduleRequestDto;
import com.example.scheduleapi.dto.ScheduleResponseDto;
import com.example.scheduleapi.dto.ScheduleUpdateRequestDto;
import com.example.scheduleapi.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping("/user/{username}")
    public ResponseEntity<ScheduleResponseDto> postSchedule(
            @PathVariable String username,
            @RequestBody ScheduleRequestDto requestDto) {

        ScheduleResponseDto scheduleResponseDto =
                scheduleService.createSchedule(username, requestDto.getTitle(), requestDto.getTodo());

        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<List<ScheduleResponseDto>> getSchedules(
            @PathVariable String username) {

        List<ScheduleResponseDto> schedules = scheduleService.getSchedulesByUsername(username);
        return new ResponseEntity<>(schedules, HttpStatus.OK);
    }

    @GetMapping("/user/{username}/{id}")
    public ResponseEntity<ScheduleResponseDto> getSchedule(
            @PathVariable String username,
            @PathVariable Long id) {

        ScheduleResponseDto scheduleResponseDto = scheduleService.getScheduleByIdAndUsername(id, username);
        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> patchSchedule(
            @PathVariable Long id,
            @RequestBody ScheduleUpdateRequestDto requestDto) {

        ScheduleResponseDto responseDto = scheduleService.updateSchedule(id, requestDto);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id) {
        scheduleService.deleteSchedule(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
