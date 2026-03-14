package edu.icet.ecom.Model.Dto;

import java.util.List;

public class User_dto {
    private Long userId;
    private String userName;
    private List<Task_dto> tasks;
    private List<Meal_dto> meals;
    private List<Activity_dto> activities;
    private Double productivityScore;
}
