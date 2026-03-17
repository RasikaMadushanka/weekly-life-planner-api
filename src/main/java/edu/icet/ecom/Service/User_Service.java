package edu.icet.ecom.Service;

import edu.icet.ecom.Model.Dto.User_Reprt_dto;
import edu.icet.ecom.Model.Dto.User_dto;
import edu.icet.ecom.Model.Entity.Activity_entity;
import edu.icet.ecom.Model.Entity.Meal_entity;
import edu.icet.ecom.Model.Entity.Task_entity;
import edu.icet.ecom.Model.Entity.User_entity;
import edu.icet.ecom.Repository.Activity_Repository;
import edu.icet.ecom.Repository.Meal_Repository;
import edu.icet.ecom.Repository.Task_Repository;
import edu.icet.ecom.Repository.User_Repository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class User_Service  {
    @Autowired
    private User_Repository userRepository;
    @Autowired
    private Task_Repository taskRepository;
    @Autowired
    private Meal_Repository mealRepository;
    @Autowired
    private Activity_Repository activityRepository; // Injected Activity Repository
    @Autowired
    private ModelMapper modelMapper;
    public User_dto saveUSer(User_dto userDto) {
        User_entity entity = modelMapper.map(userDto, User_entity.class);
        User_entity saveduser = userRepository.save(entity);
        assignInitialPlan(saveduser);
        return modelMapper.map(saveduser, User_dto.class);
    }
    private void assignInitialPlan(User_entity user) {
        if (user.getHeight() == null || user.getWeight() == null) return;

        // Use 100.0 to prevent integer division errors
        double heightInMeters = user.getHeight() / 100.0;
        double BMI = user.getWeight() / (heightInMeters * heightInMeters);

        for (int i = 0; i < 7; i++) {
            LocalDateTime dayOffset = LocalDateTime.now().plusDays(i);

            if (BMI > 25.0) {
                // Calling your method multiple times to add MORE data per day
                autoAssignPlans(user, "Weight Loss Task", "Morning Run", "Oatmeal", 300, "Jogging", "Exercise", dayOffset);
                autoAssignPlans(user, "Hydration", "Drink 3L Water", "Salad", 400, "Brisk Walk", "Cardio", dayOffset);
                autoAssignPlans(user, "Evening Task", "Stretching", "Grilled Fish", 350, "Yoga", "Relaxation", dayOffset);
            } else if (BMI < 18.5) {
                autoAssignPlans(user, "Gain Task", "Heavy Lifting", "Protein Shake", 500, "Gym", "Strength", dayOffset);
                autoAssignPlans(user, "Nutrition", "High Carb Lunch", "Pasta", 800, "Nap", "Recovery", dayOffset);
                autoAssignPlans(user, "Dinner Task", "Calorie Surplus", "Steak", 700, "Evening Walk", "Leisure", dayOffset);
            } else {
                autoAssignPlans(user, "Balance Task", "10k Steps", "Eggs", 400, "Jogging", "Fitness", dayOffset);
                autoAssignPlans(user, "Mindfulness", "Reading", "Rice & Curry", 600, "Meditation", "Mental Health", dayOffset);
                autoAssignPlans(user, "Rest", "Sleep 8 Hours", "Soup", 300, "Nature Walk", "Relaxation", dayOffset);
            }
        }
    }
    private  void autoAssignPlans(User_entity user, String title, String taskDescription, String mealName, int cal, String activityName, String category, LocalDateTime date){
        Task_entity task = new Task_entity();
        task.setTitle(title);
        task.setDescription(taskDescription);
        task.setUser(user);
        taskRepository.save(task);

        Meal_entity meal = new Meal_entity();
        meal.setMealName(mealName);
        meal.setCalories(cal);
        meal.setUser(user);
        mealRepository.save(meal);

        Activity_entity activity = new Activity_entity();
        activity.setActivityName(activityName);
        activity.setCategory(category);
        activity.setStartTime(date.withHour(8).withMinute(0));
        activity.setEndTime(date.withHour(9).withMinute(0));
        activity.setUser(user);
        activityRepository.save(activity);



    }

    public User_dto getUserById(Long id) {
        User_entity user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return modelMapper.map(user, User_dto.class);
    }

    public User_Reprt_dto generateWeeklyReport(Long id) {
        User_entity user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        User_Reprt_dto report = modelMapper.map(user, User_Reprt_dto.class);

        double hightInMeter = user.getHeight()/100;
        double BMI = user.getWeight() / (hightInMeter * hightInMeter);
        report.setProductivityScore(Math.round(BMI *10.0) / 10.0);
        return report;

    }


    public List<User_dto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> modelMapper.map(user, User_dto.class))
                .toList();
    }
}
