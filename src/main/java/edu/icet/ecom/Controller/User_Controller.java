package edu.icet.ecom.Controller;

import edu.icet.ecom.Model.Dto.User_Reprt_dto;
import edu.icet.ecom.Model.Dto.User_dto;
import edu.icet.ecom.Service.User_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")

public class User_Controller {
    @Autowired
    private User_Service userService;

    @PostMapping("/register")
    public ResponseEntity<User_dto>resgiterUser(@RequestBody User_dto userDto){
        return new ResponseEntity<>(userService.saveUSer(userDto), HttpStatus.CREATED);

    }
    @GetMapping("/{id}")
    public ResponseEntity<User_dto>getUserProfile(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }
    @GetMapping("/all")
        public List<User_dto>getAllUsers(){
        return userService.getAllUsers();
    }
    @GetMapping("/{id}/report")
    public ResponseEntity<User_Reprt_dto>getUsersReport(@PathVariable Long id){
        User_Reprt_dto report = userService.generateWeeklyReport(id);
        return ResponseEntity.ok(report);
    }
}
