package edu.icet.ecom.Controller;

import edu.icet.ecom.Model.Dto.Activity_dto;
import edu.icet.ecom.Service.Activity_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("activities")

public class Activity_Controller {
    @Autowired
    private Activity_Service activityService;

    @PostMapping("/add")
    public ResponseEntity<Activity_dto>addActivity(@RequestBody Activity_dto activityDto){
        return ResponseEntity.ok(activityService.saveActivity(activityDto));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Activity_dto>updateActivity(@PathVariable Long id ,@RequestBody Activity_dto activityDto){
        Activity_dto updateActivity = activityService.updateActivity(id,activityDto);
        return ResponseEntity.ok(updateActivity);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void>deleteActivity(@PathVariable Long id){
        activityService.deleteActivity(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/all")
    public List<Activity_dto> getAllActivities(){
        return activityService.getAllActivities();
    }



}
