package com.green.dao;

import com.green.entity.Activity;
import com.green.entity.Member;

import java.util.List;

public interface ActivityDAO {
    public List<Member> getListOfActivities();
    public void saveActivity(Activity activity);
    public Activity getActivityById(int id);
    public void deleteActivity(int id);
}
