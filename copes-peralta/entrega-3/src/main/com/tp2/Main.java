package com.tp2;

import com.tp2.entity.Career;
import com.tp2.entity.CareerStudent;
import com.tp2.entity.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

 public static void main(String[] args) {

  EntityManagerFactory emf = Persistence.createEntityManagerFactory("Entrega03");
  EntityManager em = emf.createEntityManager();

  Student e1 = new Student("Juan", "Perez", 80, 'M', 88521475L, "MarDelPlata", 5482L);
  Student e2 = new Student("Felipe", "Pergolini", 8, 'M', 12457852L, "Rauch", 1001L);
  Student e3 = new Student("Florencia", "Peña", 25, 'F', 78542658L, "Azul", 6002L);
  Student e4 = new Student("Zac", "Efron", 18, 'F', 68953241L, "Tandil", 7842L);
  Student e5 = new Student("Mark", "Twain", 88, 'M', 31231249L, "Missouri",123L);
  Student e6 = new Student("Britney", "Spears", 54, 'F', 88223123L, "Miami",712L);
  Student e7 = new Student("Harry", "Potter", 30, 'M', 21376786L, "London",111L);
  Student e8 = new Student("Hannah", "Montana", 21, 'F', 3442001L, "CABA",811L);
  Student e9 = new Student("Taylor", "Swift", 29, 'F', 37032222L, "Tennessee",118L);
  Student e10 = new Student("Hermione", "Granger", 21, 'F', 38965000L, "London",110L);
  em.getTransaction().begin();
  em.persist(e1);
  em.persist(e2);
  em.persist(e3);
  em.persist(e4);
  em.persist(e5);
  em.persist(e6);
  em.persist(e7);
  em.persist(e8);
  em.persist(e9);
  em.persist(e10);
  em.getTransaction().commit();

  em.getTransaction().begin();
  Career c1 = new Career("Drama");
  Career c2 = new Career("Software Engineering");
  Career c3 = new Career("Public Relationships");
  Career c4 = new Career("Bussiness");
  em.persist(c1);
  em.persist(c2);
  em.persist(c3);
  em.persist(c4);
  em.getTransaction().commit();

  em.getTransaction().begin();
  CareerStudent ce1 = new CareerStudent(e1, c1, 2022, false);
  CareerStudent ce2 = new CareerStudent(e1, c2, 2021, false);
  CareerStudent ce3 = new CareerStudent(e3, c2, 2019, false);
  CareerStudent ce4 = new CareerStudent(e2, c3, 2020, false);
  CareerStudent ce5 = new CareerStudent(e4, c3, 2020, false);
  CareerStudent ce6 = new CareerStudent(e5, c2, 2019, false);
  CareerStudent ce7 = new CareerStudent(e6, c4, 2003, false);
  CareerStudent ce8 = new CareerStudent(e7, c4, 2017, true);
  CareerStudent ce9 = new CareerStudent(e8, c1, 2014, true);
  CareerStudent ce10 = new CareerStudent(e9, c1, 2006, true);
  CareerStudent ce11 = new CareerStudent(e10, c2, 2009, false);
  CareerStudent ce12 = new CareerStudent(e10, c4, 2008, false);
  CareerStudent ce13 = new CareerStudent(e5, c3, 2022, false);
  CareerStudent ce14 = new CareerStudent(e6, c1, 2018, true);
  em.persist(ce1);
  em.persist(ce2);
  em.persist(ce3);
  em.persist(ce4);
  em.persist(ce5);
  em.persist(ce6);
  em.persist(ce7);
  em.persist(ce8);
  em.persist(ce9);
  em.persist(ce10);
  em.persist(ce11);
  em.persist(ce12);
  em.persist(ce13);
  em.persist(ce14);
  em.getTransaction().commit();

  em.close();
  emf.close();
 }
}
