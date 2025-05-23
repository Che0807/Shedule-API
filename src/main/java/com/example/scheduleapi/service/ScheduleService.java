package com.example.scheduleapi.service;

import com.example.scheduleapi.dto.ScheduleResponesDto;
import com.example.scheduleapi.entity.Schedule;
import com.example.scheduleapi.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
