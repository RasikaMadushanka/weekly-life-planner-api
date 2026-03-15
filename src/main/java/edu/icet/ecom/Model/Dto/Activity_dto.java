package edu.icet.ecom.Model.Dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Activity_dto {
    private String activityName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String category;
}
