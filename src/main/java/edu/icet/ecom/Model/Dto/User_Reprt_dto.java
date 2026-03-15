package edu.icet.ecom.Model.Dto;

import lombok.Data;
import java.util.List;

@Data
public class User_Reprt_dto {
    private Long id;
    private String name;
    private String email;

    // Physical Profile (Added for your new logic)
    private Integer age;
    private Double weight;
    private Double height;
    private String medicalIssues;

    // Calculated Health Insights
    private Double bmi;
    private String bodyStatus; // e.g., "Normal", "Overweight", "Underweight"
    private Integer dailyCalorieTarget;
    private String healthAdvice;

    // Combined data for the dashboard
    private List<Task_dto> tasks;
    private List<Meal_dto> meals;
    private List<Activity_dto> activities;

    private Double productivityScore;
    private String aiRecommendation;
}