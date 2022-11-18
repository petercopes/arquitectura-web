package com.tp2.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@JsonIgnoreProperties("careers")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String names;
    private String lastname;
    private int age;
    private char gender;
    private Long dni;
    private String city;
    private Long LU;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "career_student",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "career_id"))
    private List<Career> careers;
    public Student(String names, String lastname, int age, char gender, Long dni, String city, Long LU) {
        this.names = names;
        this.lastname = lastname;
        this.age = age;
        this.gender = gender;
        this.dni = dni;
        this.city = city;
        this.LU = LU;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", names='" + names + '\'' +
                ", lastname='" + lastname + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", dni=" + dni +
                ", city='" + city + '\'' +
                ", LU=" + LU +
                '}';
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public Long getDni() {
		return dni;
	}

	public void setDni(Long dni) {
		this.dni = dni;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Long getLU() {
		return LU;
	}

	public void setLU(Long LU) {
		this.LU = LU;
	}

	public List<Career> getCareers() {
		return careers;
	}

	public void setCareers(List<Career> careers) {
		this.careers = careers;
	}
}
