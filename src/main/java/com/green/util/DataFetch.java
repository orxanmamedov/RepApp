package com.green.util;

import com.green.entity.Activity;
import com.green.entity.Member;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataFetch {

//    public static Map<Member, List<Activity>> takeFromDb() {
//        String hql = "SELECT m, a FROM Member m JOIN m.activities a where a.date = current_date";
//        SessionFactory factory = new Configuration()
//                .configure("hibernate.cfg.xml")
//                .addAnnotatedClass(Member.class)
//                .addAnnotatedClass(Activity.class)
//                .buildSessionFactory();
//
//        Session session = factory.getCurrentSession();
//
//        try {
//            session.beginTransaction();
//
//            Query<Object[]> query = session.createQuery(hql, Object[].class);
//            List<Object[]> results = query.getResultList();
//
//            session.getTransaction().commit();
//
//            Map<Member, List<Activity>> output = new HashMap<>();
//
//            for (Object[] result : results) {
//                Member member = (Member) result[0];
//                Activity activity = (Activity) result[1];
//                output.computeIfAbsent(member, k -> new ArrayList<>()).add(activity);
//            }
//
//            return output;
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        } finally {
//            if (session != null && session.isOpen()) {
//                session.close();
//            }
//            if (factory != null && !factory.isClosed()) {
//                factory.close();
//            }
//        }
//    }


    //todo need to change this method to default one with parameters!!!
    public static Map<Member, List<Activity>> getActivitiesByDate(LocalDate date) {

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Member.class)
                .addAnnotatedClass(Activity.class)
                .buildSessionFactory();


        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            String hql = "SELECT m, a FROM Member m JOIN m.activities a where a.date = :date";
            Query<Object[]> query = session.createQuery(hql, Object[].class);
            query.setParameter("date", date);
            List<Object[]> results = query.getResultList();

            session.getTransaction().commit();

            Map<Member, List<Activity>> output = new HashMap<>();

            for (Object[] result : results) {
                Member member = (Member) result[0];
                Activity activity = (Activity) result[1];
                output.computeIfAbsent(member, k -> new ArrayList<>()).add(activity);
            }

            return output;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
