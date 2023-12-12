package com.green.dao;

import com.green.dto.activity.ActivityRequestDTO;
import com.green.dto.activity.ActivityResponseDTO;
import com.green.entity.Activity;
import java.util.List;

public interface ActivityDAO {
     List<ActivityResponseDTO> getListOfActivities();
     void saveActivity(ActivityRequestDTO activityDTO);
     ActivityResponseDTO getActivityById(int id);
     void deleteActivity(int id);

     void updateActivity(int activityId, ActivityRequestDTO updatedActivityDTO);
}
