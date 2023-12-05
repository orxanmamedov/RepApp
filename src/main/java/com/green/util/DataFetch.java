package com.green.util;


import com.green.entity.Activity;
import com.green.entity.Member;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataFetch {

    public static Map<Member, List<Activity>> takeFromDb(){
        String hql = "SELECT m, a FROM Member m JOIN m.activities a ORDER BY m.name";
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Member.class)
                .addAnnotatedClass(Activity.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        session.beginTransaction();

        Query<Object[]> query = session.createQuery(hql, Object[].class);
        List<Object[]> results = query.getResultList();

        session.getTransaction().commit();

        Map<Member, List<Activity>> output = new HashMap<>();

        for (Object[] result : results) {
            Member member = (Member) result[0];
            Activity activity = (Activity) result[1];
            if (output.containsKey(member)) {

                output.get(member).add(activity);
            }else {

                List<Activity> activities = new ArrayList<>();
                activities.add(activity);
                output.put(member, activities);
            }
        }

        return output;
    }

}


