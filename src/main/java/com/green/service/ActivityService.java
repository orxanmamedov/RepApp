package com.green.service;

import com.green.dto.activity.ActivityRequestDTO;
import com.green.dto.activity.ActivityResponseDTO;
import com.green.entity.Activity;

import java.util.List;
import java.util.Optional;

public interface ActivityService {
    List<ActivityResponseDTO> getListOfActivities();

    void saveActivity(ActivityRequestDTO activityDTO);

    ActivityResponseDTO getActivityById(int id);

    void deleteActivity(int id);

    void updateActivity(int activityId, ActivityRequestDTO updatedActivityDTO);
}
