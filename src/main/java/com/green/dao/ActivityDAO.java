package com.green.dao;

import com.green.entity.Activity;
import java.util.List;

public interface ActivityDAO {
     List<Activity> getListOfActivities();
     void saveActivity(Activity activity);
     Activity getActivityById(int id);
     void deleteActivity(int id);
}
