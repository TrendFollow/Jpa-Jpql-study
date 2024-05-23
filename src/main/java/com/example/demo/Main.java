package com.example.demo;

import com.example.demo.domain.Member;
import com.example.demo.domain.MemberDTO;
import com.example.demo.domain.Team;
import jakarta.persistence.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        try {
            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("teamA");
            member.setAge(10);
            member.setTeam(team);
            em.persist(member);

            em.flush();
            em.clear();



//          연관관계 없는 엔티티 외부 조인
//            String query = "select m from Member m left join Team t on m.username = t.name ";
//            List<Member> resultList = em.createQuery(query, Member.class).getResultList();
//
//            System.out.println(resultList.size());

//            조인대상 필터링
//            String query = "select m from Member m left join m.team t on t.name = 'teamA' ";
//            List<Member> resultList = em.createQuery(query, Member.class).getResultList();
//
//            System.out.println(resultList.size());

//            페이지 받는 법
//            List<Member> resultList = em.createQuery("select m from Member m order by m.age desc", Member.class)
//                    .setFirstResult(1)
//                    .setMaxResults(10)
//                    .getResultList();
//
//            System.out.println(resultList.size());
//            for (Member member1 : resultList) {
//                System.out.println(member1.toString());
//            }

//            쿼리 타입 DTO로 받는 법
//            List<MemberDTO> resultList = em.createQuery("select new com.example.demo.domain.MemberDTO(m.username, m.age) from Member m", MemberDTO.class).getResultList();
//            System.out.println(resultList.get(0).getUsername());
//            System.out.println(resultList.get(0).getAge());

//            Member singleResult = em.createQuery("select m from Member m where m.username = :username", Member.class)
//                    .setParameter("username", "mem1")
//                    .getSingleResult();


            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }

        emf.close();
    }
}
