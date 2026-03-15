package edu.icet.ecom.Controller;

import edu.icet.ecom.Model.Dto.Meal_dto;
import edu.icet.ecom.Service.Meal_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping

public class Meal_Controller {
    @Autowired
    private Meal_Service mealService;

    @PostMapping
    public ResponseEntity<Meal_dto>addMeal(@RequestBody Meal_dto mealDto){
        return ResponseEntity.ok(mealService.saveMeal(mealDto));

    }
    
}
