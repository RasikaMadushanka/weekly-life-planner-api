package edu.icet.ecom.Service;

import edu.icet.ecom.Model.Dto.User_Reprt_dto;
import edu.icet.ecom.Model.Dto.User_dto;
import edu.icet.ecom.Repository.User_Repository;
import org.jspecify.annotations.Nullable;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class User_Service {
    @Autowired
    private User_Repository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    public User_dto saveUSer(User_dto userDto) {
    }

    public @Nullable User_dto getUserById(Long id) {
    }

    public User_Reprt_dto generateWeeklyReport(Long id) {
    }
}
