package com.green.dao;

import com.green.entity.Activity;
import com.green.entity.Member;
import com.green.exception.CustomApplicationException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;
import java.util.List;

public class ActivityDAOImpl implements ActivityDAO {

    private final SessionFactory factory = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Member.class)
            .addAnnotatedClass(Activity.class)
            .buildSessionFactory();


    @Override
    public List<Activity> getListOfActivities() {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            List<Activity> activities = session.createQuery("from Activity",Activity.class).getResultList();
            session.getTransaction().commit();
            return activities;
        } catch (HibernateException e) {
            throw new CustomApplicationException("Error retrieving list of activities", e);
        }
    }

    @Override
    public void saveActivity(Activity activity) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            activity.setDate(LocalDate.now());
            session.saveOrUpdate(activity);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            throw new CustomApplicationException("Error saving/updating activity", e);
        }
    }

    @Override
    public Activity getActivityById(int id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Activity activity = session.get(Activity.class, id);
            session.getTransaction().commit();
            return activity;
        } catch (HibernateException e) {
            throw new CustomApplicationException("Error retrieving activity by ID", e);
        }
    }

    @Override
    public void deleteActivity(int id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Activity activity = session.get(Activity.class, id);
            session.delete(activity);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            throw new CustomApplicationException("Error deleting activity", e);
        }
    }
}
