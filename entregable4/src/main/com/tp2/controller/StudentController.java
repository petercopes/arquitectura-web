package com.tp2.controller;
import com.tp2.entity.Student;
import com.tp2.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
	@Operation(summary = "Devolver estudiantes",
			description = "Devuelve todos los estudiantes",
			tags = "estudiantes")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista de estudiantes",
					content = { @Content(mediaType = "application/json",
							schema = @Schema(implementation = List.class)) }),
			@ApiResponse(responseCode = "404", description = "Not found",
					content = @Content) })
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @PostMapping
	@Operation(summary = "Insertar un estudiante",
			description = "Inserta un estudiante que recibe por parametro",
			tags = "estudiantes")
	@ApiResponses(value = {
			@ApiResponse(description = "Success", responseCode = "200",
					content = @Content(mediaType = "application/json",
							schema = @Schema(implementation = Boolean.class))),
			@ApiResponse(responseCode = "400", description = "Bad request",
					content = @Content) })
    public boolean insertStudent(@RequestBody Student student) {
        return studentService.insertStudent(student);
    }
	
	@GetMapping("/sortedByName")
	@Operation(summary = "Devolver estudiantes ordenados alfabeticamente",
			description = "Devuelve todos los estudiantes ordenados alfabeticamente",
			tags = "estudiantes")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista de estudiantes",
					content = { @Content(mediaType = "application/json",
							schema = @Schema(implementation = List.class)) }),
			@ApiResponse(responseCode = "404", description = "Not found",
					content = @Content) })
	public List<Student> getStudentsWithOrderBy() {
		return studentService.getStudentsWithOrderBy();
	}
	
	@GetMapping("/gender/{gender}")
	@Operation(summary = "Devolver estudiantes filtrados por genero",
			description = "Devuelve todos los estudiantes filtrados por el genero que recibe por parametro",
			tags = "estudiantes")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista de estudiantes de un genero particular",
					content = { @Content(mediaType = "application/json",
							schema = @Schema(implementation = List.class)) }),
			@ApiResponse(responseCode = "404", description = "Not found",
					content = @Content) })
	public List<Student> getStudentsByGender(@PathVariable("gender") char gender) {
		return studentService.getStudentsByGender(gender);
	}

	@GetMapping("/lu/{LU}")
	@Operation(summary = "Devolver estudiante",
			description = "Devuelve un estudiante segun el numero de LU",
			tags = "estudiantes")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Estudiante",
					content = { @Content(mediaType = "application/json",
							schema = @Schema(implementation = Student.class)) }),
			@ApiResponse(responseCode = "404", description = "Not found",
					content = @Content) })
	public Student getStudentByLU(@PathVariable("LU") Long LU) {
		return studentService.getStudentByLU(LU);
	}
}
