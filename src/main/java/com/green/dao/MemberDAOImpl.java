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



//    public static void main(String[] args) {
//
//        SessionFactory factory = new Configuration()
//                .configure("hibernate.cfg.xml")
//                .addAnnotatedClass(Member.class)
//                .addAnnotatedClass(Activity.class)
//                .buildSessionFactory();
//
//        Session session = factory.getCurrentSession();
//        Member member0 = new Member("Orkhan");
//        Member member1 = new Member("Oksana");
//        Member member2 = new Member("Anton");
//        Member member3 = new Member("Priyankur");
//
//        Activity activity01 = new Activity(LocalDate.now(), "HTML", 3);
//        Activity activity02 = new Activity(LocalDate.now(), "JSP", 1.6);
//        Activity activity11 = new Activity(LocalDate.now(), "SMTH", 2);
//        Activity activity12 = new Activity(LocalDate.now(), "IDK", 6);
//        Activity activity21 = new Activity(LocalDate.now(), "MB", 2);
//        Activity activity22 = new Activity(LocalDate.now(), "WWOWO", 1.3);
//        Activity activity32 = new Activity(LocalDate.now(), "UWU", 12);
//
//        member0.addActivityToMember(activity01);
//        member0.addActivityToMember(activity02);
//        member1.addActivityToMember(activity11);
//        member1.addActivityToMember(activity12);
//        member2.addActivityToMember(activity21);
//        member2.addActivityToMember(activity22);
//        member3.addActivityToMember(activity32);
//
//
//
//        session.beginTransaction();
//
//        session.save(member0);
//        session.save(member1);
//        session.save(member2);
//        session.save(member3);
//
//        session.getTransaction().commit();
//
//    }
}
