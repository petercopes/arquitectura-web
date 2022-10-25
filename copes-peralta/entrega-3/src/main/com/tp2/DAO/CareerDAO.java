package com.tp2.DAO;

import com.tp2.entity.Career;
import java.util.List;

public interface CareerDAO {
    public List<Career> getCareersOrderByStudents( );

    public List<Career> getCareers();

}
