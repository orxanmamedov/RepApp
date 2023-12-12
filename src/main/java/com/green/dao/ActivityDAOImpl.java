package com.green.dao;

import com.green.HibernateUtil;
import com.green.dto.activity.ActivityRequestDTO;
import com.green.dto.activity.ActivityResponseDTO;
import com.green.dto.mapper.ActivityMapper;
import com.green.entity.Activity;
import com.green.entity.Member;
import com.green.exception.CustomApplicationException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ActivityDAOImpl implements ActivityDAO {
    private final SessionFactory factory = HibernateUtil.getSessionFactory();

    @Override
    public List<ActivityResponseDTO> getListOfActivities() {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();

            List<Activity> activities = session.createQuery("from Activity", Activity.class).getResultList();

            List<ActivityResponseDTO> activityDTOs = activities.stream()
                    .map(ActivityMapper::toResponseDTO)
                    .collect(Collectors.toList());

            session.getTransaction().commit();
            return activityDTOs;
        } catch (HibernateException e) {
            throw new CustomApplicationException("Error retrieving list of activities", e);
        }
    }

    @Override
    public void saveActivity(ActivityRequestDTO activityDTO) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Member member = session.get(Member.class, activityDTO.getMemberId());

            Activity activity = ActivityMapper.fromRequestDTO(activityDTO, member);
            activity.setDate(LocalDate.now());

            session.saveOrUpdate(activity);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            throw new CustomApplicationException("Error saving/updating activity", e);
        }
    }

    @Override
    public ActivityResponseDTO getActivityById(int id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Activity activity = session.get(Activity.class, id);
            session.getTransaction().commit();
            if (activity == null) {
                throw new CustomApplicationException("Activity not found with ID: "+ id);
            }
            return ActivityMapper.toResponseDTO(activity);
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
@Override
    public void updateActivity(int activityId, ActivityRequestDTO updatedActivityDTO) {
        try (Session session = factory.getCurrentSession()) {
            try {
                session.beginTransaction();

                Activity existingActivity = session.get(Activity.class, activityId);
                if (existingActivity != null) {
                    ActivityMapper.updateFromRequestDTO(existingActivity, updatedActivityDTO);
                    session.saveOrUpdate(existingActivity);
                } else {
                    throw new CustomApplicationException("Activity not found with ID: " + activityId);
                }

                session.getTransaction().commit();
            } catch (HibernateException e) {
                if (session.getTransaction() != null && session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
                throw new CustomApplicationException("Error updating activity", e);
            }
        }
}}
