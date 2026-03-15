package edu.icet.ecom.Model.Entity;

import jakarta.annotation.Priority;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@Entity
@Table(name = "tasks")
public class Task_entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    private String priority;
    private LocalDateTime deadline;
    private boolean isCompleted = false;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User_entity user;
}
