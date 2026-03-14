package edu.icet.ecom.Model.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "meals")
public class Meal_entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mealName;
    private String type; // Breakfast, Lunch, Dinner
    private String dietPreference; // Vegan, Keto, etc.
    private Integer calories;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User_entity user;
}
