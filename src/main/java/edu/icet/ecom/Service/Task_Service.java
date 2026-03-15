package edu.icet.ecom.Service;

import edu.icet.ecom.Model.Dto.Task_dto;
import edu.icet.ecom.Repository.Task_Repository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class Task_Service {
    @Autowired
    private Task_Repository taskRepository;
    @Autowired
    private ModelMapper modelMapper;
    public Task_dto saveTask(Task_dto taskDto) {
    }
}
