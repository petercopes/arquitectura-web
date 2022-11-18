package com.tp2.controller;
import java.util.List;
import com.tp2.entity.Student;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.tp2.dto.CareerInscriptionsDTO;
import com.tp2.dto.CareerReportDTO;
import com.tp2.service.CareerStudentService;

@RestController
@RequestMapping("/careerStudent")
public class CareerStudentController {
	
	@Autowired
	private CareerStudentService careerStudentService ;
	

	@GetMapping("/sortedByInscriptions")
	@Operation(summary = "Inscriptos por carrera",
			description = "Devuelve todos los inscriptos ordenados por carrera",
			tags = "inscriptos")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista de inscriptos por carreras",
					content = { @Content(mediaType = "application/json",
							schema = @Schema(implementation = List.class)) }),
			@ApiResponse(responseCode = "404", description = "Not found",
					content = @Content) })
	public List<CareerInscriptionsDTO>getInscriptionSortedByCareer(){
		return careerStudentService.getInscriptionSortedByCareer();
	}
	
	@GetMapping
	@Operation(summary = "Devolver inscriptos segun ciudad",
			description = "Devuelve todos inscriptos en una ciudad determinada",
			tags = "inscriptos")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista de inscriptos en una ciudad determinada",
					content = { @Content(mediaType = "application/json",
							schema = @Schema(implementation = List.class)) }),
			@ApiResponse(responseCode = "404", description = "Not found",
					content = @Content) })
	public List<Student> getStudentsByCareerFilterCity(@RequestParam("careerId") Long id, @RequestParam("city") String city){
		return careerStudentService.getStudentsByCareerFilterCity(id, city);
	}
	
	@GetMapping("/report")
	@Operation(summary = "Devolver reporte",
			description = "Devuelve el reporte de las carreras con sus inscriptos",
			tags = "reporte")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Reporte",
					content = { @Content(mediaType = "application/json",
							schema = @Schema(implementation = List.class)) }),
			@ApiResponse(responseCode = "404", description = "Not found",
					content = @Content) })
	public List<CareerReportDTO> getReportCareer() {
		return careerStudentService.getReportCareer();
	}
}
