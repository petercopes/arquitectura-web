package com.tp2.DAO.imp;
import com.tp2.DAO.StudentDAO;
import com.tp2.entity.Student;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Component
@Data
public class StudentDAOImpl implements StudentDAO {
    final static String ID = "DNI";
    final static String NAME = "names";
    final static String LAST_NAME = "lastName";
    final static String AGE = "age";
    final static String GENDER = "gender";
    final static String CITY = "city";
    final static String ACADEMIC_TRANSCRIPT = "LU";

    @Autowired
    private EntityManager em;

    @Override
    public boolean insertStudent(long DNI, String names, String lastname, int age, char gender, long LU, String city) {


        Student s = em.find(Student.class, DNI);
        Query query = null;
        if(s != null) {
            query = em.createNativeQuery("UPDATE student "
                    + "SET names = :names, lastname = :lastName, "
                    + "age = :age, gender = :gender, LU = :LU, city = :city "
                    + "WHERE DNI = :DNI");
        }
        else {
            query = em.createNativeQuery("INSERT INTO Student (DNI, names, lastname, age, gender, city, LU) "
                    + "VALUES (:DNI, :names, :lastName, :age, :gender, :city, :LU)");
        }

        em.getTransaction().begin();

        if(query != null) {
            query.setParameter(ID, DNI);
            query.setParameter(NAME, names);
            query.setParameter(LAST_NAME, lastname);
            query.setParameter(AGE, age);
            query.setParameter(GENDER, gender);
            query.setParameter(CITY, city);
            query.setParameter(ACADEMIC_TRANSCRIPT, LU);

            query.executeUpdate();
            em.getTransaction().commit();

            return true;
        }else {
            query.executeUpdate();
            em.getTransaction().commit();
            return false;
        }
    }

    @Override
    public List<Student> getStudentsWithOrderBy(){
        em.getTransaction().begin();
        @SuppressWarnings("unchecked")
        List<Student> students = em.createQuery("SELECT s FROM Student s ORDER BY s.lastname").getResultList();
        em.getTransaction().commit();
        return students;
    }

    @Override
    public Student getStudentByLU(Long LU) {
        em.getTransaction().begin();
        Student s = (Student) em.createQuery("SELECT s FROM Student s WHERE s.LU = :LU").
                setParameter(ACADEMIC_TRANSCRIPT, LU).getSingleResult();
        em.getTransaction().commit();
        return s;
    }

    @Override
    public List<Student> getStudentsByGender(char gender) {
        em.getTransaction().begin();

        @SuppressWarnings("unchecked")
        List<Student> students = em.createQuery("SELECT s FROM Student s WHERE s.gender = :gender")
                .setParameter(GENDER, gender).getResultList();
        em.getTransaction().commit();

        return students;
    }

    @Override
    public List<Student> getStudents(){
        em.getTransaction().begin();
        @SuppressWarnings("unchecked")
        List<Student> students = em.createQuery("SELECT s FROM Student s").getResultList();
        em.getTransaction().commit();
        return students;
    }
}
