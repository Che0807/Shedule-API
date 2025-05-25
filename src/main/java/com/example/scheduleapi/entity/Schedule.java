package com.example.scheduleapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Entity
@Table(name = "schedule")
public class Schedule extends BaseEntity {

    //속성
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "longtext")
    private String todo;

    @ManyToOne
    @JoinColumn(name = "User_id")
    private User user;

    //생성자

    //JPA 에서 기본으로 사용됩니다.
    protected Schedule() {}

    public Schedule(String title, String todo) {
        this.title = title;
        this.todo = todo;
    }

    public void setUser(User user){
        this.user = user;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }


}


