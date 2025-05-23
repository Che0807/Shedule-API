package com.example.scheduleapi.service;

import com.example.scheduleapi.dto.ScheduleResponesDto;
import com.example.scheduleapi.entity.Schedule;
import com.example.scheduleapi.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleResponesDto postService(String username, String title, String todo) {

        Schedule schedule = new Schedule(username, title, todo);

        Schedule savedSchedule = scheduleRepository.save(schedule);

        return new ScheduleResponesDto(
                savedSchedule.getId(),
                savedSchedule.getUsername(),
                savedSchedule.getTitle(),
                savedSchedule.getTodo(),
                savedSchedule.getCreatedAt(),
                savedSchedule.getUpdatedAt()
        );
    }

    public ScheduleResponesDto findByID(Long id) {
        Optional<Schedule> optionalSchedule = scheduleRepository.findById(id);

        if(optionalSchedule.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"해당 일정이 없습니다.");
        }

        Schedule findSchedule = optionalSchedule.get();

        return new ScheduleResponesDto(findSchedule.getUsername(), findSchedule.getTitle(), findSchedule.getTodo(),findSchedule.getCreatedAt(),findSchedule.getUpdatedAt());
    }

}
