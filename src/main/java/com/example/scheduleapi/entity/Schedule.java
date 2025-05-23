package com.example.scheduleapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@Entity
@Table(name = "schedule")
@EntityListeners(AuditingEntityListener.class)

public class Schedule extends BaseEntity {
    //속성
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "longtext")
    private String todo;

    //생성자

    //JPA 에서 기본으로 사용됩니다.
    protected Schedule() {}

    public Schedule(String username, String title, String todo) {
        this.username = username;
        this.title = title;
        this.todo = todo;
    }

}


