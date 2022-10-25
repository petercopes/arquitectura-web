package com.tp2.controller;
import java.util.List;
import com.tp2.entity.Career;
import com.tp2.service.CareerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/career")
public class CareerController {

	@Autowired
    private CareerService careerService;

    /*@GetMapping("/sortedByStudent")
    public List<Career> getCareersOrderByStudents() {
        return careerService.getCareersOrderByStudents();
    }
     */

    @GetMapping("/")
    public List<Career> getCareers() {
        return careerService.getCareers();
    }
}