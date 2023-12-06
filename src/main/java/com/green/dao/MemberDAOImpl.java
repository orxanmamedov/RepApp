package com.green.dao;

import com.green.entity.Activity;
import com.green.entity.Member;
import org.checkerframework.checker.units.qual.A;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;
import java.util.List;

public class MemberDAOImpl implements MemberDAO {

    private SessionFactory factory = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Member.class)
            .addAnnotatedClass(Activity.class)
            .buildSessionFactory();

    @Override
    public List<Member> getListOfMembers() {
        Session session = factory.getCurrentSession();

        session.beginTransaction();

        List<Member> employees = session.createQuery("from Member ").getResultList();

        session.getTransaction().commit();
        return employees;
    }

    @Override
    public void saveMember(Member member) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        session.saveOrUpdate(member);

        session.getTransaction().commit();
    }

    @Override
    public Member getMemberById(int id) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        Member member =  session.get(Member.class, id);

        session.getTransaction().commit();
        return member;

    }

    @Override
    public void deleteMember(int id) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        Member member = session.get(Member.class, id);
        session.delete(member);

        session.getTransaction().commit();

    }

}
