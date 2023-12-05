package com.green.util;

import com.green.dto.MemberActivityDTO;
import com.green.entity.Activity;
import com.green.entity.Member;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FormatData {

    public static List<MemberActivityDTO> inMemberActivityDto(){
        String hql = "SELECT m, a FROM Member m JOIN m.activities a ORDER BY m.name";
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Member.class)
                .addAnnotatedClass(Activity.class)
                .buildSessionFactory();


        LocalDate today = LocalDate.now();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        Query<Object[]> query = session.createQuery(hql, Object[].class);
        List<Object[]> results = query.getResultList();

        List<MemberActivityDTO> dtos = new ArrayList<>();
        for (Object[] result : results) {
            Member member = (Member) result[0];
            Activity activity = (Activity) result[1];
            MemberActivityDTO dto = new MemberActivityDTO(member, activity);
            dtos.add(dto);
        }

        session.getTransaction().commit();




        return dtos;
    }

}


