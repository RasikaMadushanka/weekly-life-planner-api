package edu.icet.ecom.Controller;

import edu.icet.ecom.Model.Dto.Task_dto;
import edu.icet.ecom.Service.Task_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
@CrossOrigin(origins = "*")
public class Task_Controller {

    @Autowired
    private Task_Service taskService;

    @PostMapping("/add")
    public ResponseEntity<Task_dto> addTask(@RequestBody Task_dto taskDto) {
        return new ResponseEntity<>(taskService.saveTask(taskDto), HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Task_dto>> getTasksByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(taskService.getTasksByUserId(userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok("Task deleted");
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Task_dto> updateStatus(@PathVariable Long id, @RequestParam boolean completed) {
        return ResponseEntity.ok(taskService.updateTaskStatus(id, completed));
    }
}