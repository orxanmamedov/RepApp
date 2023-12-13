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
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ActivityDAOImpl implements ActivityDAO {
    private final SessionFactory factory = HibernateUtil.getSessionFactory();

    @Override
    public List<ActivityResponseDTO> getListOfActivities(Map<String, String[]> parameterMap) {
        Map<String, Object> params = extractParams(parameterMap);
        StringBuilder queryString = new StringBuilder("SELECT a FROM Activity a");
        if (!params.isEmpty()) {
            queryString.append(" WHERE ");
            List<String> conditions = new ArrayList<>();
            for (Map.Entry<String, Object> param : params.entrySet()) {
                switch (param.getKey()) {
                    case "id":
                        conditions.add("a.id = :id");
                        params.put("id", Integer.parseInt((String) param.getValue()));
                        break;
                    case "dateFrom":
                        conditions.add("a.date >= :dateFrom");
                        params.put("dateFrom", LocalDate.parse((String) param.getValue()));
                        break;
                    case "dateTo":
                        conditions.add("a.date <= :dateTo");
                        params.put("dateTo", LocalDate.parse((String) param.getValue()));
                        break;
                    case "subject":
                        conditions.add("a.subject = :subject");
                        params.put("subject", param.getValue());
                        break;
                    case "memberId":
                        conditions.add("a.member.id = :memberId");
                        params.put("memberId", Integer.parseInt((String) param.getValue()));
                        break;
                    case "memberName":
                        conditions.add("a.member.name = :memberName");
                        params.put("memberName", param.getValue());
                        break;
                }
            }
            queryString.append(String.join(" AND ", conditions));
        }
        queryString.append(" ORDER BY a.date DESC");


        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Query<Activity> query = session.createQuery(queryString.toString(), Activity.class);
            for (Map.Entry<String, Object> param : params.entrySet()) {
                query.setParameter(param.getKey(), param.getValue());
            }
            List<Activity> members = query.getResultList();
            session.getTransaction().commit();
            return members.stream()
                    .map(ActivityMapper::toResponseDTO)
                    .collect(Collectors.toList());
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
                throw new CustomApplicationException("Activity not found with ID: " + id);
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
    }
    private Map<String, Object> extractParams(Map<String, String[]> parameterMap) {
        Map<String, Object> params = new HashMap<>();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            String key = entry.getKey();
            String[] values = entry.getValue();
            params.put(key, values[0]);
        }
        return params;
    }
}
