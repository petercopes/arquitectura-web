package DAO;

import entity.Student;
import org.apache.commons.csv.CSVParser;

import javax.persistence.EntityManager;
import java.util.List;

public interface StudentDAO {
    public void studentPersistence(EntityManager em, CSVParser parserStudent);
    public void insertStudent(EntityManager em, long DNI, String name, String lastname, int age, String gender, int LU, String city);
    public List<Student> getStudentsWithOrderBy(EntityManager em);
    public Student getStudentByLU(EntityManager em, Long LU);
    public List<Student> getStudentsByGender(EntityManager em, String gender);
}
