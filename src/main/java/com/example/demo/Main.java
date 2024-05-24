package com.example.demo;

import com.example.demo.domain.Member;
import com.example.demo.domain.MemberDTO;
import com.example.demo.domain.Team;
import jakarta.persistence.*;
import org.hibernate.dialect.H2Dialect;
import org.hibernate.dialect.MySQLDialect;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        try {
            Team teamA = new Team();
            teamA.setName("팀A");
            em.persist(teamA);

            Team teamB = new Team();
            teamB.setName("팀B");
            em.persist(teamB);

            Member member1 = new Member();
            member1.setUsername("회원1");
            member1.setTeam(teamA);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("회원2");
            member2.setTeam(teamA);
            em.persist(member2);

            Member member3 = new Member();
            member3.setUsername("회원3");
            member3.setTeam(teamB);
            em.persist(member3);

            Member member4 = new Member();
            member4.setUsername("회원4");
            em.persist(member4);

            em.flush();
            em.clear();

            int resultCount = em.createQuery("update Member m set m.age = 20").executeUpdate();
            System.out.println(resultCount);


//            List<Member> resultList1 = em.createNamedQuery("Member.findByUsername", Member.class)
//                    .setParameter("username", "회원2")
//                    .getResultList();
//
//            for (Member member : resultList1) {
//                System.out.println(member);
//            }

//            String query = "select distinct t from Team t join fetch t.members";

//            List<Team> result = em.createQuery(query, Team.class).getResultList();
//            System.out.println(result.size());

//            String query = "select " +
//                    " case when m.age <= 10 then '학생요금' " +
//                    "       when m.age >= 60 then '경로요금' " +
//                    " end " +
////                    " from Member m ";
//            List<Team> resultList = em.createQuery(query, Team.class).getResultList();
//            for (Team m : resultList) {
//                System.out.println(m.getName() + " = " + m.getMembers().size());
//                for (Member member : m.getMembers()) {
//                    System.out.println(member);
//                }
//            }

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
