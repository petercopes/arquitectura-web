import DAO.CareerDAO;
import DAO.CareerStudentDAO;
import DAO.StudentDAO;
import DAO.imp.CareerDAOImpl;
import DAO.imp.CareerStudentDAOImpl;
import DAO.imp.StudentDAOImpl;
import DTO.ReportDTO;
import entity.Career;
import entity.Student;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws  IOException {
        final String TYPE = "Integ02";
        final String CSV_CAREER = "./src/main/resources/csv/csv/career.csv";
        final String CSV_STUDENT = "./src/main/resources/csv/csv/student.csv";
        final String CSV_CAREER_STUDENT = "./src/main/resources/csv/csv/career_student.csv";

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Integ02");
        EntityManager em = emf.createEntityManager();

        CSVParser parserCareer = CSVFormat.DEFAULT.withHeader().parse(new FileReader(CSV_CAREER));
        CSVParser parserStudent = CSVFormat.DEFAULT.withHeader().parse(new FileReader(CSV_STUDENT));
        CSVParser parserCareerStudent = CSVFormat.DEFAULT.withHeader().parse(new FileReader(CSV_CAREER_STUDENT));

        CareerDAO career = new CareerDAOImpl();
        StudentDAO student = new StudentDAOImpl();
        CareerStudentDAO careerStudent = new CareerStudentDAOImpl();

        student.studentPersistence(em, parserStudent);
        career.careerPersistence(em, parserCareer);
        careerStudent.career_studentPersistence(em, parserCareerStudent);

        System.out.println(" Recuperar todos los estudiantes, y especificar alg�n criterio de ordenamiento simple.");
        List<Student> studentsOrderBy = student.getStudentsWithOrderBy(em);
        studentsOrderBy.forEach(s -> System.out.println(s));
        System.out.println("Recuperar un estudiante, en base a su numero de libreta universitaria.");
        System.out.println(" Numero de libreta universitaria: "+ 258406);
        Student studentByLU = student.getStudentByLU(em, 258406L);
        System.out.println(studentByLU.toString());
        System.out.println("Recuperar todos los estudiantes, en base a su genero.");
        String[] genders = {"Male","Agender","Female","Bigender","Genderfluid","Polygender","Genderqueer","Non-binary"};
        for(String gender : genders){
            System.out.println("Genero: "+gender);
            List<Student> studentsByGender = student.getStudentsByGender(em, gender);
            studentsByGender.forEach(s -> System.out.println(s));
        }
        System.out.println("Recuperar las carreras con estudiantes inscriptos, y ordenar por cantidad de inscriptos.");
        List<Career> careers = career.getCareersOrderByStudents(em);
        careers.forEach(c -> System.out.println(c));
        System.out.println("Recuperar los estudiantes de una determinada carrera, filtrado por ciudad de residencia. ");
        System.out.println("Identificador de la carrera: 17");
        System.out.println("Ciudad: Dzaoudzi");
        List<Student> studentsByCareerFilterCity = careerStudent.getStudentsByCareerFilterCity(em, 17L, "Dzaoudzi");
        studentsByCareerFilterCity.forEach(s -> System.out.println(s));
        System.out.println("Reporte de las carreras con informaci�n de los inscriptos y egresados por anio, ordenando las carreras alfabeticamente y los anios de manera cronol�gica.\n");
        List<ReportDTO> reports = careerStudent.getReport(em);
        reports.forEach(s -> System.out.println(s));
    }
}
