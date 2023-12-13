package com.green.service;

import com.green.dto.activity.ActivityRequestDTO;
import com.green.dto.activity.ActivityResponseDTO;
import com.green.entity.Activity;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ActivityService {
    List<ActivityResponseDTO> getListOfActivities(Map<String,String[]> params);

    void saveActivity(ActivityRequestDTO activityDTO);

    ActivityResponseDTO getActivityById(int id);

    void deleteActivity(int id);

    void updateActivity(int activityId, ActivityRequestDTO updatedActivityDTO);
}
