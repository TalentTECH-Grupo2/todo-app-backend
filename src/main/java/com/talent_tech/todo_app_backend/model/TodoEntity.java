package com.talent_tech.todo_app_backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "TODOS")
@Data
public class TodoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long todoId;

    @Column(nullable = false, unique = true)
    private String todoTitle;

    @Column(nullable = false)
    private String todoDescription;

    @Column(nullable = false)
    private Boolean todoIsCompleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = true)
    private UserEntity user;

}
