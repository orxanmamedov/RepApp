package com.green.dao;

import com.green.entity.Activity;
import com.green.entity.Member;
import com.green.exception.CustomApplicationException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class MemberDAOImpl implements MemberDAO {


    private final SessionFactory factory = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Member.class)
            .addAnnotatedClass(Activity.class)
            .buildSessionFactory();

    @Override
    public List<Member> getListOfMembers() {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            List<Member> members = session.createQuery("from Member", Member.class).getResultList();
            session.getTransaction().commit();
            return members;
        } catch (HibernateException e) {
            throw new CustomApplicationException("Error retrieving list of members", e);
        }
    }


    @Override
    public void saveMember(Member member) {
        try (Session session = factory.getCurrentSession()) {
            try {
                session.beginTransaction();
                session.saveOrUpdate(member);
                session.getTransaction().commit();
            } catch (HibernateException e) {
                if (session.getTransaction() != null && session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
                throw new CustomApplicationException("Error saving/updating member", e);
            }
        }
    }

    @Override
    public Member getMemberById(int id) {
        try (Session session = factory.getCurrentSession()) {
            try {
                session.beginTransaction();
                Member member = session.get(Member.class, id);
                session.getTransaction().commit();
                return member;
            } catch (HibernateException e) {
                if (session.getTransaction() != null && session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }

                throw new CustomApplicationException("Error retrieving member by ID", e);
            }
        }
    }

    @Override
    public void deleteMember(int id) {
        try (Session session = factory.getCurrentSession()) {
            try {
                session.beginTransaction();
                Member member = session.get(Member.class, id);
                session.delete(member);
                session.getTransaction().commit();
            } catch (HibernateException e) {
                if (session.getTransaction() != null && session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
                throw new CustomApplicationException("Error deleting member by ID", e);
            }
        }
    }
}