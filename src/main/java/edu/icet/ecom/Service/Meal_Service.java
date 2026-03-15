package edu.icet.ecom.Service;

import edu.icet.ecom.Model.Dto.Meal_dto;
import edu.icet.ecom.Model.Entity.Meal_entity;
import edu.icet.ecom.Repository.Meal_Repository;
import org.jspecify.annotations.Nullable;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class Meal_Service {
    @Autowired
    private Meal_Repository mealRepository;
    @Autowired
    private ModelMapper modelMapper;

    public @Nullable Meal_dto saveMeal(Meal_dto mealDto) {
        Meal_entity entity = modelMapper.map(mealDto, Meal_entity.class);
        return modelMapper.map(mealRepository.save(entity), Meal_dto.class);
    }

    public Meal_dto updateMeal(Long id, Meal_dto mealDto) {
        Meal_entity existingMeal = mealRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Meal Not Found"));
        existingMeal.setMealName(mealDto.getMealName());
        Meal_entity updated=mealRepository.save(existingMeal);
        return modelMapper.map(updated, Meal_dto.class);
    }

    public void deleteMeal(Long id) {
        mealRepository.deleteById(id);
    }

    public List<Meal_dto> getAllMeals() {
        return mealRepository.findAll().stream().map(meal -> modelMapper.map(meal, Meal_dto.class)).collect(Collectors.toList());
    }
}
