package com.example.scheduleapi.service;

import com.example.scheduleapi.dto.ScheduleResponseDto;
import com.example.scheduleapi.dto.ScheduleUpdateRequestDto;
import com.example.scheduleapi.entity.Schedule;
import com.example.scheduleapi.entity.User;
import com.example.scheduleapi.repository.ScheduleRepository;
import com.example.scheduleapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;


@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final UserRepository userRepository;

    private final ScheduleRepository scheduleRepository;

    //생성
    public ScheduleResponseDto save(String username, String title, String todo) {

        User findUser = userRepository.findUserByUsernameOrElseThrow(username);


        Schedule schedule = new Schedule(title, todo);
        schedule.setUser(findUser);


        Schedule savedSchedule = scheduleRepository.save(schedule);


        return new ScheduleResponseDto(savedSchedule.getId(),savedSchedule.getTitle(),savedSchedule.getTodo());
    }


    // 다건 조회 (username 으로 필터)
    public List<ScheduleResponseDto> findByUsername(String username) {
        User user = userRepository.findUserByUsernameOrElseThrow(username);
        List<Schedule> schedules = scheduleRepository.findAllByUser(user);
        return schedules.stream().map(ScheduleResponseDto::toDto).toList();
    }

    // 단건 조회 (id + username 검증 포함)
    public ScheduleResponseDto findByIdAndUsername(Long id, String username) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "스케줄이 없습니다."));

        if (!schedule.getUser().getUsername().equals(username)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "접근 권한이 없습니다.");
        }

        return ScheduleResponseDto.toDto(schedule);
    }


    // 수정
    public ScheduleResponseDto updateSchedule(Long id, ScheduleUpdateRequestDto dto) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 일정이 없습니다."));

        if (dto.getUsername() != null) {
            User user = userRepository.findUserByUsernameOrElseThrow(dto.getUsername());
            schedule.setUser(user);
        }

        if (dto.getTitle() != null) schedule.setTitle(dto.getTitle());
        if (dto.getTodo() != null) schedule.setTodo(dto.getTodo());

        Schedule updated = scheduleRepository.save(schedule);

        return ScheduleResponseDto.toDto(updated);
    }

    // 삭제
    public void delete(Long id) {
        if (!scheduleRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 일정이 없습니다.");
        }
        scheduleRepository.deleteById(id);
    }
}

