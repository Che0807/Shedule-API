package com.example.scheduleapi.controller;

import com.example.scheduleapi.dto.ScheduleRequestDto;
import com.example.scheduleapi.dto.ScheduleResponesDto;
import com.example.scheduleapi.entity.Schedule;
import com.example.scheduleapi.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ScheduleResponesDto> createSchedule(@RequestBody ScheduleRequestDto requestDto) {

        ScheduleResponesDto scheduleResponesDto =
                scheduleService.postService(
                        requestDto.getUsername(),
                        requestDto.getTitle(),
                        requestDto.getTodo()
                );

        return new ResponseEntity<>(scheduleResponesDto,HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponesDto> findByID(@PathVariable Long id) {

        ScheduleResponesDto scheduleResponesDto = scheduleService.findByID(id);

        return new ResponseEntity<>(scheduleResponesDto, HttpStatus.OK);
    }
}
