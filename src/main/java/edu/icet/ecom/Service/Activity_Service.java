package edu.icet.ecom.Service;

import edu.icet.ecom.Model.Dto.Activity_dto;
import edu.icet.ecom.Model.Entity.Activity_entity;
import edu.icet.ecom.Repository.Activity_Repository;
import org.jspecify.annotations.Nullable;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class Activity_Service {
    @Autowired
    private Activity_Repository activityRepository;
    @Autowired
    private ModelMapper modelMapper;

    public @Nullable Activity_dto saveActivity(Activity_dto activityDto) {
        Activity_entity entity = modelMapper.map(activityDto, Activity_entity.class);
        return modelMapper.map(activityRepository.save(entity), Activity_dto.class);
    }

    public Activity_dto updateActivity(Long id, Activity_dto activityDto) {
        Activity_entity existingActivity = activityRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Activity Not Found"));
        existingActivity.setActivityName(activityDto.getActivityName());
        existingActivity.setCategory(activityDto.getCategory());
        existingActivity.setStartTime(activityDto.getStartTime());
        existingActivity.setEndTime(activityDto.getEndTime());        Activity_entity updated=activityRepository.save(existingActivity);
        return modelMapper.map(updated, Activity_dto.class);
    }

    public void deleteActivity(Long id) {
        activityRepository.deleteById(id);
    }

    public List<Activity_dto> getAllActivities() {
        return activityRepository.findAll().stream().map(activity -> modelMapper.map(activity, Activity_dto.class)).toList();

    }
}
