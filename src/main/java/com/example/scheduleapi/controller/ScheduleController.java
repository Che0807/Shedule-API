package com.example.scheduleapi.controller;

import com.example.scheduleapi.dto.ScheduleRequestDto;
import com.example.scheduleapi.dto.ScheduleResponesDto;
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
    public ResponseEntity<ScheduleResponesDto> createSchedule(@RequestBody ScheduleRequestDto requestDto) {

        ScheduleResponesDto scheduleResponesDto =
                scheduleService.postService(
                        requestDto.getUsername(),
                        requestDto.getTitle(),
                        requestDto.getTodo()
                );

        return new ResponseEntity<>(scheduleResponesDto,HttpStatus.CREATED);

    }
    //다건 조회
    @GetMapping
    public ResponseEntity<List<ScheduleResponesDto>> findAll() {
        List<ScheduleResponesDto> scheduleResponesDtoList = scheduleService.findAll();
        return new ResponseEntity<>(scheduleResponesDtoList, HttpStatus.OK);
    }


    //단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponesDto> findByID(@PathVariable Long id) {

        ScheduleResponesDto scheduleResponesDto = scheduleService.findByID(id);

        return new ResponseEntity<>(scheduleResponesDto, HttpStatus.OK);
    }

    //수정
    @PatchMapping("/{id}")
    public ResponseEntity<ScheduleResponesDto> updateSchedule(
            @PathVariable Long id,
            @RequestBody ScheduleUpdateRequestDto requestDto
    ) {
        ScheduleResponesDto responseDto = scheduleService.updateSchedule(id, requestDto);
        return ResponseEntity.ok(responseDto);
    }

    //삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        scheduleService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

