package edu.icet.ecom.Model.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
@Getter
@Setter
@Entity
@Table(name = "activities")
public class Activity_entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String activityName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String category; // Exercise, Meeting, Leisure

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User_entity user;
}
