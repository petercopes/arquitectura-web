package com.tp2.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Career {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String careerName;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "career_student",
            joinColumns = @JoinColumn(name = "career_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<Student> students;

    public Career(String careerName) {
        this.careerName = careerName;
        this.students = new ArrayList<>();
    }

    public void addStudent(Student e) {
        students.add(e);
    }

    public void removeStudent(Student e) {
        students.remove(e);
    }

    @Override
    public String toString() {
        return "Career{" +
                "id=" + id +
                ", CareerName='" + careerName + '\'' +
                '}';
    }
}
