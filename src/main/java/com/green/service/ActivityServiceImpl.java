package com.green.service;

import com.green.dao.ActivityDAO;
import com.green.dao.ActivityDAOImpl;
import com.green.dto.activity.ActivityRequestDTO;
import com.green.dto.activity.ActivityResponseDTO;

import java.util.List;


public class ActivityServiceImpl implements ActivityService {
    private final ActivityDAO activityDAO = new ActivityDAOImpl();

    @Override
    public List<ActivityResponseDTO> getListOfActivities() {
        return activityDAO.getListOfActivities();
    }

    @Override
    public void saveActivity(ActivityRequestDTO activityDTO) {
        activityDAO.saveActivity(activityDTO);
    }

    @Override
    public ActivityResponseDTO getActivityById(int id) {
        return activityDAO.getActivityById(id);
    }

    @Override
    public void deleteActivity(int id) {
        activityDAO.deleteActivity(id);
    }

    @Override
    public void updateActivity(int activityId, ActivityRequestDTO updatedActivityDTO) {
        activityDAO.updateActivity(activityId,updatedActivityDTO);


    }

}
