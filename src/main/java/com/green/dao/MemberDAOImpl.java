package com.green.dao;

import com.green.HibernateUtil;
import com.green.dto.mapper.MemberMapper;
import com.green.dto.member.MemberRequestDTO;
import com.green.dto.member.MemberResponseDTO;
import com.green.entity.Member;
import com.green.exception.CustomApplicationException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.stream.Collectors;

public class MemberDAOImpl implements MemberDAO {
    private static final SessionFactory factory = HibernateUtil.getSessionFactory();

    @Override
    public List<MemberResponseDTO> getListOfMembers() {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            List<Member> members = session.createQuery("from Member", Member.class).getResultList();
            session.getTransaction().commit();
            return members.stream()
                    .map(MemberMapper::toResponseDTO)
                    .collect(Collectors.toList());
        } catch (HibernateException e) {
            throw new CustomApplicationException("Error retrieving list of members", e);
        }
    }


    @Override
    public void saveMember(MemberRequestDTO memberDTO) {
        try (Session session = factory.getCurrentSession()) {
            try {
                session.beginTransaction();
                Member member=MemberMapper.fromRequestDTO(memberDTO);
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
    public MemberResponseDTO getMemberById(int id) {
        try (Session session = factory.getCurrentSession()) {
            try {
                session.beginTransaction();
                Member member = session.get(Member.class, id);
                session.getTransaction().commit();
                return MemberMapper.toResponseDTO(member);
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
    @Override
    public void updateMember(int memberId, MemberRequestDTO updatedMemberDTO) {
        try (Session session = factory.getCurrentSession()) {
            try {
                session.beginTransaction();

                Member existingMember = session.get(Member.class, memberId);
                if (existingMember != null) {
                    MemberMapper.updateFromRequestDTO(existingMember, updatedMemberDTO);
                    session.saveOrUpdate(existingMember);
                } else {
                    throw new CustomApplicationException("Member not found with ID: " + memberId);
                }

                session.getTransaction().commit();
            } catch (HibernateException e) {
                if (session.getTransaction() != null && session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
                throw new CustomApplicationException("Error updating member", e);
            }
        }
    }

}