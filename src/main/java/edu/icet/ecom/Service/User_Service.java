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
        if(user.getHeight() == null || user.getHeight() == null) return;
        double heightInMeters = user.getHeight()/100;
        double BMI = user.getWeight() / (heightInMeters * heightInMeters);
        if (BMI > 25.0) {
            autoAssignPlans(user, "Weight Loss Plan", "Daily 30min Cardio", "Oatmeal", 300, "Evening Jogging", "Exercise");
        } else if (BMI < 18.5) {
            autoAssignPlans(user, "Weight Gain Plan", "Strength Training", "Protein Pasta", 800, "Muscle Recovery Nap", "Leisure");
        } else {
            autoAssignPlans(user, "Maintenance Plan", "Yoga & Stretching", "Balanced Salad", 500, "Nature Walk", "Relaxation");
        }
    }
    private  void autoAssignPlans(User_entity user, String title,String taskDescription ,String mealName ,int cal ,String activityName ,String category){
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
