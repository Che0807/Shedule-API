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

    // 일정 생성
    public ScheduleResponseDto createSchedule(String username, String title, String todo) {
        User user = userRepository.findUserByUsernameOrElseThrow(username);

        Schedule schedule = new Schedule(title, todo);
        schedule.setUser(user);

        Schedule savedSchedule = scheduleRepository.save(schedule);

        return ScheduleResponseDto.toDto(savedSchedule);
    }

    // 사용자 일정 전체 조회
    public List<ScheduleResponseDto> getSchedulesByUsername(String username) {
        User user = userRepository.findUserByUsernameOrElseThrow(username);
        List<Schedule> schedules = scheduleRepository.findAllByUser(user);
        return schedules.stream().map(ScheduleResponseDto::toDto).toList();
    }

    // ID와 사용자명으로 일정 단건 조회
    public ScheduleResponseDto getScheduleByIdAndUsername(Long scheduleId, String username) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "일정을 찾을 수 없습니다."));

        if (!schedule.getUser().getUsername().equals(username)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "권한이 없습니다.");
        }

        return ScheduleResponseDto.toDto(schedule);
    }

    // 일정 수정
    public ScheduleResponseDto updateSchedule(Long scheduleId, ScheduleUpdateRequestDto dto) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "일정을 찾을 수 없습니다."));

        if (dto.getUsername() != null) {
            User user = userRepository.findUserByUsernameOrElseThrow(dto.getUsername());
            schedule.setUser(user);
        }
        if (dto.getTitle() != null) schedule.setTitle(dto.getTitle());
        if (dto.getTodo() != null) schedule.setTodo(dto.getTodo());

        Schedule updated = scheduleRepository.save(schedule);
        return ScheduleResponseDto.toDto(updated);
    }

    // 일정 삭제
    public void deleteSchedule(Long scheduleId) {
        if (!scheduleRepository.existsById(scheduleId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "일정을 찾을 수 없습니다.");
        }
        scheduleRepository.deleteById(scheduleId);
    }
}
