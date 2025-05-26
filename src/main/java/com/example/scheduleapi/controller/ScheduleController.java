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
@RequestMapping("/schedules")  // 복수형 사용
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    // 생성
    @PostMapping("/user/{username}")
    public ResponseEntity<ScheduleResponseDto> createSchedule (
            @PathVariable String username,
            @RequestBody ScheduleRequestDto requestDto) {

        ScheduleResponseDto scheduleResponseDto =
                scheduleService.save(username, requestDto.getTitle(), requestDto.getTodo());

        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.CREATED);
    }

    // 전체 조회
    @GetMapping("/user/{username}")
    public ResponseEntity<List<ScheduleResponseDto>> getSchedulesByUsername (@PathVariable String username) {
        List<ScheduleResponseDto> schedules = scheduleService.findByUsername(username);
        return new ResponseEntity<>(schedules, HttpStatus.OK);
    }

    // 단건 조회
    @GetMapping("/user/{username}/{id}")
    public ResponseEntity<ScheduleResponseDto> getScheduleById (
            @PathVariable String username,
            @PathVariable Long id) {

        ScheduleResponseDto scheduleResponseDto = scheduleService.findByIdAndUsername(id, username);
        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.OK);
    }

    // 수정
    @PatchMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(
            @PathVariable Long id,
            @RequestBody ScheduleUpdateRequestDto requestDto) {

        ScheduleResponseDto responseDto = scheduleService.updateSchedule(id, requestDto);
        return ResponseEntity.ok(responseDto);
    }

    // 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule (@PathVariable Long id) {
        scheduleService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}


