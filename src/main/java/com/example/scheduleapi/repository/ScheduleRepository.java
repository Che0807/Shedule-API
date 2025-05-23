package com.example.scheduleapi.repository;

import com.example.scheduleapi.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public interface ScheduleRepository extends JpaRepository<Schedule,Long> {

    default Schedule findByIDOrElseThrow(Long id){
        return findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND, "해당 일정이 존재하지 않습니다.")
                );
    }
}
