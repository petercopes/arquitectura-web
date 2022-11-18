package com.tp2.dto;

public class CareerReportDTO {

	private String careerName;
	private int inscription;
	private boolean graduation;
	private int DNI;
	private Long LU;
	private String lastname;
	private String studentName;
	
	public CareerReportDTO(String careerName, int inscription, boolean graduation, int DNI, Long LU,
						   String lastname, String studentName) {
		this.careerName = careerName;
		this.inscription = inscription;
		this.graduation = graduation;
		this.DNI = DNI;
		this.LU = LU;
		this.lastname = lastname;
		this.studentName = studentName;
	}


	public String getCareerName() {
		return careerName;
	}

	public void setCareerName(String careerName) {
		this.careerName = careerName;
	}

	public int getInscription() {
		return inscription;
	}

	public void setInscription(int inscription) {
		this.inscription = inscription;
	}

	public boolean isGraduation() {
		return graduation;
	}

	public void setGraduation(boolean graduation) {
		this.graduation = graduation;
	}

	public int getDNI() {
		return DNI;
	}

	public void setDNI(int DNI) {
		this.DNI = DNI;
	}

	public Long getLU() {
		return LU;
	}

	public void setLU(Long LU) {
		this.LU = LU;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
}
