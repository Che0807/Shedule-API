package com.example.sheduleapi.service;

import com.example.sheduleapi.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {
    private final ScheduleRepository shceduleRepository;

    public ScheduleService(ScheduleRepository shceduleRepository) {
        this.shceduleRepository = shceduleRepository;
    }

}
