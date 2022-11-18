package com.tp2.DAO;

import com.tp2.entity.Student;
import java.util.List;

public interface StudentDAO {
    public boolean insertStudent(long DNI, String name, String lastname, int age, char gender, long LU, String city);
    public List<Student> getStudentsWithOrderBy();
    public Student getStudentByLU(Long LU);
    public List<Student> getStudentsByGender(char gender);
    List<Student> getStudents();

}
