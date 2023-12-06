package com.green.dao;

import com.green.entity.Activity;
import com.green.entity.Member;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ActivityDAOImpl implements ActivityDAO {

    private SessionFactory factory = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Member.class)
            .addAnnotatedClass(Activity.class)
            .buildSessionFactory();


    @Override
    public List<Activity> getListOfActivities() {
        Session session = factory.getCurrentSession();

        session.beginTransaction();

        List<Activity> activities = session.createQuery("from Activity ").getResultList();

        session.getTransaction().commit();
        return activities;
    }

    @Override
    public void saveActivity(Activity activity) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        session.saveOrUpdate(activity);

        session.getTransaction().commit();
    }

    @Override
    public Activity getActivityById(int id) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        Activity activity =  session.get(Activity.class, id);

        session.getTransaction().commit();
        return activity;
    }

    @Override
    public void deleteActivity(int id) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        Activity activity = session.get(Activity.class, id);
        session.delete(activity);

        session.getTransaction().commit();
    }

}
