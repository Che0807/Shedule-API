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
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    //생성
    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleRequestDto requestDto) {

        ScheduleResponseDto scheduleResponseDto =
                scheduleService.postService(
                        requestDto.getUsername(),
                        requestDto.getTitle(),
                        requestDto.getTodo()
                );

        return new ResponseEntity<>(scheduleResponseDto,HttpStatus.CREATED);

    }
    //다건 조회
    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> findAll() {
        List<ScheduleResponseDto> scheduleResponseDtoList = scheduleService.findAll();
        return new ResponseEntity<>(scheduleResponseDtoList, HttpStatus.OK);
    }


    //단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findByID(@PathVariable Long id) {

        ScheduleResponseDto scheduleResponseDto = scheduleService.findByID(id);

        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.OK);
    }

    //수정
    @PatchMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(
            @PathVariable Long id,
            @RequestBody ScheduleUpdateRequestDto requestDto
    ) {
        ScheduleResponseDto responseDto = scheduleService.updateSchedule(id, requestDto);
        return ResponseEntity.ok(responseDto);
    }

    //삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        scheduleService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

