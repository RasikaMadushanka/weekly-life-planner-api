package edu.icet.ecom.Model.Dto;

import java.util.List;

public class User_Reprt_dto {
    private Long id;
    private String name;
    private String email;

    // Combined data for the dashboard
    private List<Task_dto> tasks;
    private List<Meal_dto> meals;
    private List<Activity_dto> activities;

    // AI-calculated fields for the "Business Analyst" insights
    private Double productivityScore;
    private String aiRecommendation;
}
