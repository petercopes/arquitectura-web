package com.tp2.service;
import com.tp2.DAO.StudentDAO;
import com.tp2.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentDAO studentDAO;

    public boolean insertStudent(Student student) {
        return studentDAO.insertStudent(student.getDni(),student.getNames(),student.getLastname(),student.getAge(),student.getGender(),student.getLU(),student.getCity());
    }

    public List<Student> getStudents() {
        return studentDAO.getStudents();
    }

	public List<Student> getStudentsWithOrderBy() {
		return studentDAO.getStudentsWithOrderBy();
	}

	public List<Student> getStudentsByGender(char gender) {
		return studentDAO.getStudentsByGender(gender);
	}
	
	public Student getStudentByLU(Long LU) {
		return studentDAO.getStudentByLU(LU);
	}


    
    
}
