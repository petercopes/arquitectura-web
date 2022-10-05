package DAO.imp;

import DAO.CareerDAO;
import entity.Career;
import lombok.NoArgsConstructor;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import javax.persistence.EntityManager;
import java.util.List;

@NoArgsConstructor
public class CareerDAOImpl implements CareerDAO {
    final static String NAME = "name";
    final static String LENGTH = "length";

    @Override
    public void careerPersistence(EntityManager em, CSVParser parserCareer) {
        for(CSVRecord row: parserCareer) {
            em.getTransaction().begin();

            String name = row.get(NAME);
            int length = Integer.parseInt(row.get(LENGTH));

            Career insert = new Career(name, length);
            em.persist(insert);
            em.getTransaction().commit();
        }
    }
    @Override
    public List<Career> getCareersOrderByStudents(EntityManager em) {
        em.getTransaction().begin();
        List<Career> careers = em.createQuery("SELECT DISTINCT c FROM Career c JOIN c.students s WHERE size(s) > 0 ORDER BY size(s)").getResultList();
        em.getTransaction().commit();
        return careers;
    }
}
