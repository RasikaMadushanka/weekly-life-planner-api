package edu.icet.ecom.Controller;

import edu.icet.ecom.Model.Dto.Activity_dto;
import edu.icet.ecom.Service.Activity_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    

}
