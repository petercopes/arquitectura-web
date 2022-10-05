package DAO;

import DTO.ReportDTO;
import entity.Student;
import org.apache.commons.csv.CSVParser;

import javax.persistence.EntityManager;
import java.util.List;

public interface CareerStudentDAO {
    public void career_studentPersistence(EntityManager em, CSVParser parserCareerStudent);
    public void addStudent(EntityManager em, long idStudent, long idCareer);
    public List<Student> getStudentsByCareerFilterCity(EntityManager em, Long career_id, String city);
    public List<ReportDTO> getReport(EntityManager em);
}
