package com.green.dao;

import com.green.HibernateUtil;
import com.green.dto.mapper.MemberMarkMapper;
import com.green.dto.marks.MemberMarkRequestDTO;
import com.green.dto.marks.MemberMarkResponseDTO;
import com.green.entity.Member;
import com.green.entity.MemberMark;
import com.green.exception.CustomApplicationException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class MemberMarkDAOImpl implements MemberMarkDAO {
    private static final SessionFactory factory = HibernateUtil.getSessionFactory();

    @Override
    public List<MemberMarkResponseDTO> getListOfMemberMarks(Map<String, String[]> params) {
        return Collections.emptyList();
    }

    @Override
    public void saveMemberMark(MemberMarkRequestDTO memberMarkDTO, int memberId) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Member member = session.get(Member.class, memberId);
            MemberMark memberMark = MemberMarkMapper.fromRequestDTO(memberMarkDTO, member);
            memberMark.setDate(LocalDate.now());
            session.saveOrUpdate(memberMark);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            throw new CustomApplicationException("Error saving/updating member mark", e);
        }
    }


    @Override
    public MemberMarkResponseDTO getMemberMarkById(int id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            MemberMark memberMark = session.get(MemberMark.class, id);
            session.getTransaction().commit();
            if (memberMark == null) {
                throw new CustomApplicationException("Member mark not found with ID: " + id);
            }
            return MemberMarkMapper.toResponseDTO(memberMark);
        } catch (HibernateException e) {
            throw new CustomApplicationException("Error retrieving member mark by ID", e);
        }
    }

    @Override
    public void deleteMemberMark(int id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            MemberMark memberMark = session.get(MemberMark.class, id);
            session.delete(memberMark);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            throw new CustomApplicationException("Error deleting member mark", e);
        }
    }

    @Override
    public void updateMemberMark(int memberMarkId, MemberMarkRequestDTO updatedMemberMarkDTO) {
        try (Session session = factory.getCurrentSession()) {
            try {
                session.beginTransaction();

                MemberMark existingMemberMark = session.get(MemberMark.class, memberMarkId);
                if (existingMemberMark != null) {
                    MemberMarkMapper.updateFromRequestDTO(existingMemberMark, updatedMemberMarkDTO);
                    session.saveOrUpdate(existingMemberMark);
                } else {
                    throw new CustomApplicationException("Member mark not found with ID: " + memberMarkId);
                }

                session.getTransaction().commit();
            } catch (HibernateException e) {
                if (session.getTransaction() != null && session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
                throw new CustomApplicationException("Error updating member mark", e);
            }
        }
    }
}
