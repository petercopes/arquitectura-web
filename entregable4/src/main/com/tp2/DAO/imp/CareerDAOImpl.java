package com.tp2.DAO.imp;

import com.tp2.DAO.CareerDAO;
import com.tp2.entity.Career;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.persistence.EntityManager;
import java.util.List;

@Component
public class CareerDAOImpl implements CareerDAO {

    @Autowired
    private EntityManager em;

    @Override
    public List<Career> getCareersOrderByStudents() {
        this.em.getTransaction().begin();
        List<Career> careers = this.em.createQuery("SELECT DISTINCT c FROM Career c JOIN c.students s WHERE size(s) > 0 ORDER BY size(s)").getResultList();
        this.em.getTransaction().commit();
        return careers;
    }
    @Override
    public List<Career> getCareers() {
        this.em.getTransaction().begin();
        List<Career> careers = this.em.createQuery("SELECT DISTINCT c FROM Career c ").getResultList();
        this.em.getTransaction().commit();
        return careers;
    }
}
