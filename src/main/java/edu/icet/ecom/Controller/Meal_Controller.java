package edu.icet.ecom.Controller;

import edu.icet.ecom.Model.Dto.Meal_dto;
import edu.icet.ecom.Service.Meal_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping

public class Meal_Controller {
    @Autowired
    private Meal_Service mealService;

    @PostMapping
    public ResponseEntity<Meal_dto>addMeal(@RequestBody Meal_dto mealDto){
        return ResponseEntity.ok(mealService.saveMeal(mealDto));

    }
    @GetMapping("/all")
    public List<Meal_dto>getAllMeals(){
        return mealService.getAllMeals();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Meal_dto>updateMeal(@PathVariable Long id,@RequestBody Meal_dto mealDto){
        Meal_dto updateMeal =mealService.updateMeal(id,mealDto);
        return ResponseEntity.ok(updateMeal);
    }
    @DeleteMapping
    public ResponseEntity<Void>deleteMeal(@PathVariable Long id){
        mealService.deleteMeal(id);
        return ResponseEntity.noContent().build();
    }


}
