package hu.webuni.hr.domi.model;

import java.util.List;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
@Embeddable
public class Position {
	
	public enum Qualification { GRADUATION, COLLAGE, UNIVERSITY, PHD }
	
	@Id
	@GeneratedValue
	private long id;

	private String positionName;
	
	private Qualification minQualification;
	
	private int minSalary;
	
	@OneToMany(mappedBy = "position")
	private List<Employee> employees;
	
	
	public Position(){
	}
	

	public Position(String positionName, Qualification minQualification) {
		this.positionName = positionName;
		this.minQualification = minQualification;
	}
	
	public Position(long id, String positionName, Qualification minQualification, int minSalary) {
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


	public List<Employee> getEmployees() {
		return employees;
	}


	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	
	
	
	
	
}
