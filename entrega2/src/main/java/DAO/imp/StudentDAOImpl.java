package DAO.imp;

import DAO.StudentDAO;
import entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@NoArgsConstructor
@Data
public class StudentDAOImpl implements StudentDAO {
    final static String ID = "DNI";
    final static String NAME = "name";
    final static String LAST_NAME = "lastName";
    final static String AGE = "age";
    final static String GENDER = "gender";
    final static String CITY = "city";
    final static String ACADEMIC_TRANSCRIPT = "LU";


    @Override
    public void studentPersistence(EntityManager em, CSVParser parserStudent) {
        //Persistencia CSV de Student
        for(CSVRecord row: parserStudent) {
            em.getTransaction().begin();

            Long DNI = Long.parseLong(row.get(ID));
            String name = row.get(NAME);
            String lastName = row.get(LAST_NAME);
            int age = Integer.parseInt(row.get(AGE));
            String gender = row.get(GENDER);
            String city = row.get(CITY);
            long LU = Long.parseLong(row.get(ACADEMIC_TRANSCRIPT));

            Student insert = new Student(DNI, name, lastName, age, gender, city, LU);
            em.persist(insert);
            em.getTransaction().commit();
        }
    }

    @Override
    public void insertStudent(EntityManager em, long DNI, String name, String lastname, int age, String gender, int LU, String city) {


        Student s = em.find(Student.class, DNI);
        Query query = null;
        if(s != null) {
            query = em.createNativeQuery("UPDATE student "
                    + "SET name = :name, lastname = :lastName, "
                    + "age = :age, gender = :gender, LU = :LU, city = :city "
                    + "WHERE DNI = :DNI");
        }
        else {
            query = em.createNativeQuery("INSERT INTO Student (DNI, name, lastname, age, gender, city, LU) "
                    + "VALUES (:DNI, :name, :lastName, :age, :gender, :city, :LU)");
        }

        em.getTransaction().begin();

        query.setParameter(ID, DNI);
        query.setParameter(NAME, name);
        query.setParameter(LAST_NAME, lastname);
        query.setParameter(AGE, age);
        query.setParameter(GENDER, gender);
        query.setParameter(CITY, city);
        query.setParameter(ACADEMIC_TRANSCRIPT, LU);

        query.executeUpdate();
        em.getTransaction().commit();
    }

    @Override
    public List<Student> getStudentsWithOrderBy(EntityManager em){
        em.getTransaction().begin();
        @SuppressWarnings("unchecked")
        List<Student> students = em.createQuery("SELECT s FROM Student s ORDER BY lastname").getResultList();
        em.getTransaction().commit();
        return students;
    }

    @Override
    public Student getStudentByLU(EntityManager em, Long LU) {
        em.getTransaction().begin();
        Student s = (Student) em.createQuery("SELECT s FROM Student s WHERE s.LU = :LU").
                setParameter(ACADEMIC_TRANSCRIPT, LU).getSingleResult();
        em.getTransaction().commit();
        return s;
    }

    @Override
    public List<Student> getStudentsByGender(EntityManager em, String gender) {
        em.getTransaction().begin();

        @SuppressWarnings("unchecked")
        List<Student> students = em.createQuery("SELECT s FROM Student s WHERE s.gender = :gender")
                .setParameter(GENDER, gender).getResultList();
        em.getTransaction().commit();

        return students;
    }
}
