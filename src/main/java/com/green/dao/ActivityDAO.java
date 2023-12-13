package com.green.dao;

import com.green.dto.activity.ActivityRequestDTO;
import com.green.dto.activity.ActivityResponseDTO;

import java.util.List;
import java.util.Map;

public interface ActivityDAO {
    List<ActivityResponseDTO> getListOfActivities(Map<String, String[]> params);

    void saveActivity(ActivityRequestDTO activityDTO);

    ActivityResponseDTO getActivityById(int id);

    void deleteActivity(int id);

    void updateActivity(int activityId, ActivityRequestDTO updatedActivityDTO);
}
