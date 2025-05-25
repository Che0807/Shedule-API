package com.example.scheduleapi.service;

import com.example.scheduleapi.dto.ScheduleResponseDto;
import com.example.scheduleapi.dto.ScheduleUpdateRequestDto;
import com.example.scheduleapi.entity.Schedule;
import com.example.scheduleapi.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    //생성
    public ScheduleResponseDto postService(String username, String title, String todo) {

        Schedule schedule = new Schedule(username, title, todo);

        Schedule savedSchedule = scheduleRepository.save(schedule);

        return new ScheduleResponseDto(
                savedSchedule.getId(),
                savedSchedule.getUsername(),
                savedSchedule.getTitle(),
                savedSchedule.getTodo(),
                savedSchedule.getCreatedAt(),
                savedSchedule.getUpdatedAt()
        );
    }

    //다건 조회
    public List<ScheduleResponseDto> findAll() {
        return scheduleRepository.findAll().stream()
                .map(schedule -> new ScheduleResponseDto(
                        schedule.getId(),
                        schedule.getUsername(),
                        schedule.getTitle(),
                        schedule.getTodo(),
                        schedule.getCreatedAt(),
                        schedule.getUpdatedAt()
                ))
                .toList();
    }

    //단건 조회
    public ScheduleResponseDto findByID(Long id) {
        Optional<Schedule> optionalSchedule = scheduleRepository.findById(id);

        if (optionalSchedule.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 일정이 없습니다.");
        }

        Schedule findSchedule = optionalSchedule.get();

        return new ScheduleResponseDto(findSchedule.getUsername(), findSchedule.getTitle(), findSchedule.getTodo(), findSchedule.getCreatedAt(), findSchedule.getUpdatedAt());
    }

    //수정
    public ScheduleResponseDto updateSchedule(Long id, ScheduleUpdateRequestDto dto) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 일정이 없습니다."));

        if (dto.getUsername() != null) schedule.setUsername(dto.getUsername());
        if (dto.getTitle() != null) schedule.setTitle(dto.getTitle());
        if (dto.getTodo() != null) schedule.setTodo(dto.getTodo());


        Schedule updated = scheduleRepository.save(schedule);

        return new ScheduleResponseDto(
                updated.getId(),
                updated.getUsername(),
                updated.getTitle(),
                updated.getTodo(),
                updated.getCreatedAt(),
                updated.getUpdatedAt()
        );
    }


    //삭제
    public void delete(Long id) {
        if (!scheduleRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 일정이 없습니다.");
        }
        scheduleRepository.deleteById(id);
    }
}

