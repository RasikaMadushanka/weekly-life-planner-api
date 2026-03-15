package edu.icet.ecom.Service;

import edu.icet.ecom.Model.Dto.User_Reprt_dto;
import edu.icet.ecom.Model.Dto.User_dto;
import edu.icet.ecom.Model.Entity.User_entity;
import edu.icet.ecom.Repository.User_Repository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class User_Service  {
    @Autowired
    private User_Repository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    public User_dto saveUSer(User_dto userDto) {
        User_entity entity = modelMapper.map(userDto, User_entity.class);
        User_entity savedEntity = userRepository.save(entity);
        return modelMapper.map(savedEntity, User_dto.class);
    }

    public User_dto getUserById(Long id) {
        User_entity user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return modelMapper.map(user, User_dto.class);
    }

    public User_Reprt_dto generateWeeklyReport(Long id) {
        User_entity user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        User_Reprt_dto report = modelMapper.map()
    }


    public List<User_dto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> modelMapper.map(user, User_dto.class))
                .toList();
    }
}
