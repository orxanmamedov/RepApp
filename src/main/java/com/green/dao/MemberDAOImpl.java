package com.green.dao;

import com.google.protobuf.MapEntry;
import com.green.HibernateUtil;
import com.green.dto.mapper.MemberMapper;
import com.green.dto.member.MemberRequestDTO;
import com.green.dto.member.MemberResponseDTO;
import com.green.entity.Member;
import com.green.exception.CustomApplicationException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;


import java.util.*;
import java.util.stream.Collectors;

public class MemberDAOImpl implements MemberDAO {
    private static final SessionFactory factory = HibernateUtil.getSessionFactory();

    @Override
    public List<MemberResponseDTO> getListOfMembers(Map<String, String[]> parameterMap) {
        Map<String, Object> params = extractParams(parameterMap);
        StringBuilder queryString = new StringBuilder("SELECT m FROM Member m");
        if (!params.isEmpty()) {
            queryString.append(" WHERE ");
            List<String> conditions = new ArrayList<>();
            for (Map.Entry<String, Object> param : params.entrySet()) {
                switch (param.getKey()) {
                    case "id":
                        conditions.add("m.id = :id");
                        params.put("id", Integer.parseInt((String) param.getValue()));
                        break;
                    case "groupId":
                        conditions.add("m.group.id = :groupId");
                        params.put("groupId", Integer.parseInt((String) param.getValue()));
                        break;
                    case "groupName":
                        conditions.add("m.group.name = :groupName");
                        params.put("groupName", param.getValue());
                        break;
                }
            }
            queryString.append(String.join(" AND ", conditions));
        }
        queryString.append(" ORDER BY m.id ASC");


        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Query<Member> query = session.createQuery(queryString.toString(), Member.class);
            for (Map.Entry<String, Object> param : params.entrySet()) {
                query.setParameter(param.getKey(), param.getValue());
            }
            List<Member> members = query.getResultList();
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
                Member member = MemberMapper.fromRequestDTO(memberDTO);
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


    private Map<String, Object> extractParams(Map<String, String[]> parameterMap) {
        Map<String, Object> params = new HashMap<>();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            String key = entry.getKey();
            String[] values = entry.getValue();
            params.put(key, values[0]);
        }
        return params;
    }
}