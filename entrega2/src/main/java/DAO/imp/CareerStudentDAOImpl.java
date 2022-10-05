package DAO.imp;

import DAO.CareerStudentDAO;
import DTO.ReportDTO;
import entity.Career;
import entity.CareerStudent;
import entity.Student;
import lombok.NoArgsConstructor;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
public class CareerStudentDAOImpl implements CareerStudentDAO {
    final static String ANTIQUITY = "antiquity";
    final static String GRADUATION = "graduation";
    final static String CAREER = "career";
    final static String STUDENT = "student";
    final static String CITY = "city";

    @Override
    public void career_studentPersistence(EntityManager em, CSVParser parserCareerStudent) {

        for(CSVRecord row: parserCareerStudent) {
            em.getTransaction().begin();

            int antiquity = Integer.parseInt(row.get(ANTIQUITY));
            Integer graduation = null;
            if(!row.get(GRADUATION).equals("")){
                graduation = Integer.parseInt(row.get(GRADUATION));
            }
            Long career_id = Long.parseLong(row.get(CAREER));
            Long student_DNI = Long.parseLong(row.get(STUDENT));

            Student student = em.find(Student.class, student_DNI);
            Career career = em.find(Career.class, career_id);
            student.addCareer(career);
            career.addStudent(student);

            CareerStudent insert = new CareerStudent(student, career, graduation, antiquity);
            em.persist(insert);
            em.getTransaction().commit();
        }
    }

    public void addStudent(EntityManager em, long idStudent, long idCareer) {
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
        student.addCareer(career);

        query.executeUpdate();
        em.getTransaction().commit();
    }

    @Override
    public List<Student> getStudentsByCareerFilterCity(EntityManager em, Long career_id, String city) {
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


    @SuppressWarnings("unchecked")
    @Override
    public List<ReportDTO> getReport(EntityManager em) {
        em.getTransaction().begin();

        List<Object[]> query = em.createNativeQuery("SELECT name, graduation years, NULL enrolled, cs.student_id graduate "
                        + "FROM career c "
                        + "INNER JOIN  career_student cs "
                        + "ON c.id= cs.career_id "
                        + "WHERE cs.graduation IS NOT NULL "
                        + "UNION ALL "
                        + "SELECT name, YEAR(CURDATE()) - cs.antiquity years, cs.student_id enrolled, NULL graduate "
                        + "FROM career c INNER JOIN  career_student cs "
                        + "ON c.id= cs.career_id "
                        + "WHERE cs.graduation IS NULL "
                        + "UNION ALL "
                        + "SELECT name, (graduation - cs.antiquity) years, cs.student_id enrolled, NULL graduate "
                        + "FROM career c "
                        + "INNER JOIN  career_student cs "
                        + "ON c.id= cs.career_id "
                        + "WHERE cs.graduation IS NOT NULL "
                        + "ORDER BY name, years, enrolled DESC")
                .getResultList();

        List<ReportDTO> reports = query.stream().map(o -> new ReportDTO((String)o[0], (BigInteger)o[1], (BigInteger)o[2], (BigInteger)o[3])).collect(Collectors.toList());

        em.getTransaction().commit();
        return reports;
    }
}
