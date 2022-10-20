package com.tp2.DAO;
import com.tp2.dto.CareerInscriptionsDTO;
import com.tp2.dto.CareerReportDTO;
import com.tp2.entity.Student;
import java.util.List;

public interface CareerStudentDAO {
    public void addStudent( long idStudent, long idCareer);
    public List<Student> getStudentsByCareerFilterCity(Long career_id, String city);
    public List<CareerInscriptionsDTO> getInscriptionSortedByCareer();
    public List<CareerReportDTO> getReportCareer();
}
