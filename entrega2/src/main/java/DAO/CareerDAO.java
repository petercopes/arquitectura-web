package DAO;

import entity.Career;
import org.apache.commons.csv.CSVParser;

import javax.persistence.EntityManager;
import java.util.List;

public interface CareerDAO {
    public void careerPersistence(EntityManager em, CSVParser parserCareer);
    public List<Career> getCareersOrderByStudents(EntityManager em);
}
