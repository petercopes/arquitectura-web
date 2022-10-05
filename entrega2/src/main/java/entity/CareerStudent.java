package entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;
@Data
@Entity
@Table(name = "career_student")
public class CareerStudent {

    @EmbeddedId
    private CareerStudentId id;
    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    private Student student;
    @ManyToOne
    @MapsId("careerId")
    @JoinColumn(name = "career_id")
    private Career career;
    @Column
    private Integer graduation;
    @Column
    private int antiquity;

    public CareerStudent() {	}

    public CareerStudent(Student student, Career career) {
        super();
        this.career = career;
        this.student = student;
    }

    public CareerStudent(Student student, Career career, Integer graduation, int antiquity) {
        super();
        this.career = career;
        this.student = student;
        this.graduation = graduation;
        this.antiquity = antiquity;
        this.id = new CareerStudentId(student.getDNI(), career.getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        CareerStudent that = (CareerStudent) o;
        return Objects.equals(student, that.student) &&
                Objects.equals(career, that.career);
    }

    @Override
    public int hashCode() {
        return Objects.hash(student, career);
    }

    @Override
    public String toString() {
        return "CareerStudent [id=" + this.getId() + ", student=" + student.getDNI() + ", career=" + career.getId() + ", graduation=" + graduation
                + ", antiquity=" + antiquity + "]";
    }

	public CareerStudentId getId() {
		return id;
	}

	public Student getStudent() {
		return student;
	}

	public Career getCareer() {
		return career;
	}

	public Integer getGraduation() {
		return graduation;
	}

	public int getAntiquity() {
		return antiquity;
	}

	
}
