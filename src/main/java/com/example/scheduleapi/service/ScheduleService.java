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

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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


    //다건 조회
    public List<ScheduleResponseDto> findAll() {
        return scheduleRepository.findAll()
                .stream()
                .map(ScheduleResponseDto::toDto)
                .toList();
    }

    //단건 조회
    public ScheduleResponseDto findById(Long id) {
        Schedule schedule = scheduleRepository.findByIdOrElseThrow(id);
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

