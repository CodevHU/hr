package hu.webuni.hr.domi.model;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Embeddable
public class Position {
	
	public enum Qualification { GRADUATION, COLLAGE, UNIVERSITY, PHD }
	
	@Id
	@GeneratedValue
	private long id;

	private String positionName;
	
	@Enumerated(EnumType.STRING)
	private Qualification minQualification;
	
	private int minSalary;
	
	
	public Position(){
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
	
	
	
	
}
