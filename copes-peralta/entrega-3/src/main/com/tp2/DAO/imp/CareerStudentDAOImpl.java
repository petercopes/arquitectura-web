package com.tp2.DAO.imp;

import com.tp2.DAO.CareerStudentDAO;
import com.tp2.dto.CareerInscriptionsDTO;
import com.tp2.dto.CareerReportDTO;
import com.tp2.entity.Career;
import com.tp2.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class CareerStudentDAOImpl implements CareerStudentDAO {
    final static String ANTIQUITY = "antiquity";
    final static String GRADUATION = "graduation";
    final static String CAREER = "career";
    final static String STUDENT = "student";
    final static String CITY = "city";

    @Autowired
    private EntityManager em;

    public void addStudent( long idStudent, long idCareer) {
        Query query = em.createNativeQuery("INSERT INTO career_student (career_id, student_id, antiquity, graduation) "
                + "VALUES (:career, :student, :antiquity, :graduation)");

        em.getTransaction().begin();

        query.setParameter(CAREER, idCareer);
        query.setParameter(STUDENT, idStudent);
        query.setParameter(ANTIQUITY, 0);
        query.setParameter(GRADUATION, null);

        Student student = em.find(Student.class, idStudent);
        Career career = em.find(Career.class, idCareer);

        career.addStudent(student);

        query.executeUpdate();
        em.getTransaction().commit();
    }

    @Override
    public List<Student> getStudentsByCareerFilterCity(Long career_id, String city) {
        em.getTransaction().begin();

        @SuppressWarnings("unchecked")
        List<Student> students = em.createQuery("SELECT DISTINCT(s) FROM Student s, CareerStudent cs "
                        + "WHERE cs.career.id = :career "
                        + "AND s.city = :city")
                .setParameter(CAREER, career_id)
                .setParameter(CITY, city)
                .getResultList();

        em.getTransaction().commit();

        return students;
    }

    @Override
    public List<CareerReportDTO> getReportCareer() {
        TypedQuery<CareerReportDTO> q = this.em.createQuery(
                "SELECT new com.tp2.dto.CareerReportDTO (c.careerName, ce.antiquity, ce.graduated, e.dni, e.LU, e.names, e.lastname )" +
                        "FROM Career c JOIN CareerStudent ce ON c.id = ce.career.id JOIN Student e ON ce.student.id=e.id " +
                        "ORDER BY c.careerName, ce.antiquity", CareerReportDTO.class);
        return q.getResultList();
    }

    @Override
    public List<CareerInscriptionsDTO> getInscriptionSortedByCareer(){
        TypedQuery<CareerInscriptionsDTO> q =  this.em.createQuery(
                "SELECT new com.tp2.dto.CareerInscriptionsDTO(ce.career.careerName, ce.career.id, COUNT(ce)) " +
                        "FROM CareerStudent ce JOIN Career c ON ce.career.id = c.id "
                        + "GROUP BY ce.id " +
                        "ORDER BY COUNT(ce.career.id)",
                CareerInscriptionsDTO.class);

        return q.getResultList();
    }

}
