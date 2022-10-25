package com.tp2.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Table(name="career_student")
public class CareerStudent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int antiquity;
    private boolean graduated;
    @JsonManagedReference
    @ManyToOne
    private Student student;
    @JsonManagedReference
    @ManyToOne
    private Career career;

    public CareerStudent(Student student, Career career, int admissionYear, boolean graduated) {
        this.student = student;
        this.career = career;
        this.antiquity = LocalDate.now().getYear() - admissionYear;
        this.graduated = graduated;
    }
}
