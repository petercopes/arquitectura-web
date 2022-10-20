package com.tp2.controller;
import com.tp2.entity.Student;
import com.tp2.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @PostMapping
    public boolean insertStudent(@RequestBody Student student) {
        return studentService.insertStudent(student);
    }
	
	@GetMapping("/sortedByName")
	public List<Student> getStudentsWithOrderBy() {
		return studentService.getStudentsWithOrderBy();
	}
	
	@GetMapping("/gender/{gender}")
	public List<Student> getStudentsByGender(@PathVariable("gender") char gender) {
		return studentService.getStudentsByGender(gender);
	}

	@GetMapping("/lu/{LU}")
	public Student getStudentByLU(@PathVariable("LU") Long LU) {
		return studentService.getStudentByLU(LU);
	}
}
