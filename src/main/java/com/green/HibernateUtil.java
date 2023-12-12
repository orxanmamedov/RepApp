package com.green;

import com.green.entity.Activity;
import com.green.entity.Group;
import com.green.entity.Member;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory factory = buildSessionFactory();

    private HibernateUtil() {
    }

    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(Member.class)
                    .addAnnotatedClass(Activity.class)
                    .addAnnotatedClass(Group.class)
                    .buildSessionFactory();
        } catch (Exception ex) {
            throw new ClassCastException("Error building the SessionFactory: " + ex.getMessage());
        }
    }

    public static SessionFactory getSessionFactory() {
        return factory;
    }
}
