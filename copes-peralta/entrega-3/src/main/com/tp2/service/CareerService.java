package com.tp2.service;

import com.tp2.DAO.CareerDAO;
import com.tp2.entity.Career;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CareerService {
    @Autowired
    private CareerDAO careerDAO;

    public List<Career> getCareersOrderByStudents() {
        return careerDAO.getCareersOrderByStudents();
    }

    public List<Career> getCareers() {
        return careerDAO.getCareers();
    }
}
