package com.tp2.controller;
import java.util.List;
import com.tp2.entity.Student;
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
	public List<CareerInscriptionsDTO>getInscriptionSortedByCareer(){
		return careerStudentService.getInscriptionSortedByCareer();
	}
	
	@GetMapping
	public List<Student> getStudentsByCareerFilterCity(@RequestParam("careerId") Long id, @RequestParam("city") String city){
		return careerStudentService.getStudentsByCareerFilterCity(id, city);
	}
	
	@GetMapping("/report")
	public List<CareerReportDTO> getReportCareer() {
		return careerStudentService.getReportCareer();
	}
}
