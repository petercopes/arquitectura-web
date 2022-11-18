package com.tp2.service;

import java.util.List;
import com.tp2.DAO.CareerStudentDAO;
import com.tp2.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tp2.dto.CareerInscriptionsDTO;
import com.tp2.dto.CareerReportDTO;

@Service
public class CareerStudentService {
	@Autowired
    private CareerStudentDAO careerStudentDAO;
	
	public List<Student> getStudentsByCareerFilterCity(Long idCarrera, String ciudad){
		return careerStudentDAO.getStudentsByCareerFilterCity(idCarrera, ciudad);
	}
	
	public List<CareerInscriptionsDTO> getInscriptionSortedByCareer(){
		return careerStudentDAO.getInscriptionSortedByCareer();
	}
	
	public List<CareerReportDTO> getReportCareer(){
		return careerStudentDAO.getReportCareer();
	}

}
