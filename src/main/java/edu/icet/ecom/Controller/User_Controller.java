package edu.icet.ecom.Controller;

import edu.icet.ecom.Model.Dto.User_dto;
import edu.icet.ecom.Service.User_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")

public class User_Controller {
    @Autowired
    private User_Service userService;

    @PostMapping("/register")
    public ResponseEntity<User_dto>resgiterUser(@RequestBody User_dto userDto){
        return new ResponseEntity<>(userService.saveUSer(User_dto), HttpStatus.CREATED);

    }
}
