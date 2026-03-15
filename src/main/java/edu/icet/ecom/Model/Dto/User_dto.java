package edu.icet.ecom.Model.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User_dto {
    private Long id;
    private String name;
    private String email;

    // Captured during Sign-up for Health Analysis
    private Integer age;
    private Double weight; // in kg
    private Double height; // in cm
    private String medicalIssues;
}