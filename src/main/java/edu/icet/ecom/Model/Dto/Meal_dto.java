package edu.icet.ecom.Model.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Meal_dto {
    private String mealName;
    private String type;
    private Integer calories;
    private String recipeLink;
}
