package com.green.dao;

import com.green.entity.Member;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class MemberDAOImpl implements MemberDAO {

    private SessionFactory factory = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Member.class)
            .buildSessionFactory();

    @Override
    public List<Member> getListOfMembers() {
        Session session = factory.getCurrentSession();

        session.beginTransaction();

        List<Member> employees = session.createQuery("from Employee ").getResultList();

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

        session.delete(session.get(Member.class, id));

        session.getTransaction().commit();

    }
}
