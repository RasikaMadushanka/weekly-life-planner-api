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
                // WEIGHT LOSS: High Protein, Low Carb, Double Cardio
                autoAssignPlans(user, day,
                        "Cardio Session", "Morning 5km Run", "Hydration Task", "Drink 3L Water",
                        "Oats", 250, "Almonds", 100, "Salad", 350, "Fruit", 80, "Grilled Chicken", 400,
                        "Yoga", "Exercise", "Brisk Walk", "Fat Burn");
            } else if (BMI < 18.5) {
                // WEIGHT GAIN: High Calorie, Strength Focus
                autoAssignPlans(user, day,
                        "Gym Session", "Heavy Lifting", "Protein Intake", "Eat 150g Protein",
                        "Smoothie", 500, "Peanut Butter", 300, "Pasta", 800, "Yogurt", 200, "Steak", 700,
                        "Weight Training", "Strength", "Evening Stretch", "Recovery");
            } else {
                // MAINTENANCE: Balanced
                autoAssignPlans(user, day,
                        "Step Count", "Reach 10k Steps", "Reading", "Read 10 pages",
                        "Eggs", 300, "Walnuts", 150, "Rice & Curry", 600, "Tea", 50, "Soup", 300,
                        "Jogging", "Cardio", "Meditation", "Mental Health");
            }
        }
    }
    private  void autoAssignPlans(User_entity user, LocalDateTime date, String title, String taskDescription,
                                  String m1, int c1, String m2, int c2, String m3, int c3,
                                  String activityName, String category){
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
        activity.setStartTime(LocalDateTime.now().plusHours(2)); // Schedule for 2 hours from now
        activity.setEndTime(LocalDateTime.now().plusHours(3));
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
