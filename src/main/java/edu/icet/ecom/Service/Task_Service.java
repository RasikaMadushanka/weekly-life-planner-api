package edu.icet.ecom.Service;

import edu.icet.ecom.Model.Dto.Task_dto;
import edu.icet.ecom.Model.Entity.Task_entity;
import edu.icet.ecom.Repository.Task_Repository;
import org.jspecify.annotations.Nullable;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class Task_Service {
    @Autowired
    private Task_Repository taskRepository;
    @Autowired
    private ModelMapper modelMapper;
    public Task_dto saveTask(Task_dto taskDto) {
        Task_entity entity = modelMapper.map(taskDto, Task_entity.class);
        return modelMapper.map(taskRepository.save(entity), Task_dto.class);
    }

    public @Nullable List<Task_dto> getTasksByUserId(Long userId) {
        if (userId == null) {
            throw new RuntimeException("User ID cannot be null");
        }
        return taskRepository.findByUserId(userId).stream().map(task -> modelMapper.map(task, Task_dto.class)).toList();
    }

    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new RuntimeException("Task not found");
        }
        taskRepository.deleteById(id);
    }

    public @Nullable Task_dto updateTaskStatus(Long id, boolean completed) {
        Task_entity existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        existingTask.setCompleted(completed);
        return modelMapper.map(taskRepository.save(existingTask), Task_dto.class);
    }
}
