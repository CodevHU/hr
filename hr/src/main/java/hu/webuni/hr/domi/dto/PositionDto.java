package hu.webuni.hr.domi.dto;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;


public class PositionDto {
	
	public enum Qualification { GRADUATION, COLLAGE, UNIVERSITY, PHD }
	
	@Id
	@GeneratedValue
	private long id;

	private String positionName;
	
	private Qualification minQualification;
	
	private int minSalary;
	
	@OneToMany(mappedBy = "position")
	private List<EmployeeDto> employees;
	
	
	public PositionDto(){
	}
	

	public PositionDto(String positionName, Qualification minQualification) {
		this.positionName = positionName;
		this.minQualification = minQualification;
	}
	
	public PositionDto(long id, String positionName, Qualification minQualification, int minSalary) {
		this.id = id;
		this.positionName = positionName;
		this.minQualification = minQualification;
		this.minSalary = minSalary;
	}
			
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public Qualification getMinQualification() {
		return minQualification;
	}

	public void setMinQualification(Qualification minQualification) {
		this.minQualification = minQualification;
	}

	public int getMinSalary() {
		return minSalary;
	}

	public void setMinSalary(int minSalary) {
		this.minSalary = minSalary;
	}


	public List<EmployeeDto> getEmployees() {
		return employees;
	}


	public void setEmployees(List<EmployeeDto> employees) {
		this.employees = employees;
	}
	
	
	
	
	
}
