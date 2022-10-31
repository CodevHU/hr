package hu.webuni.hr.domi.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Employee {

	@Id
	@GeneratedValue
	private long id;
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String workPosition;
	
	@Min(0)
	private int pay;

	@Past
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime firstWorkingDay;
	
	@ManyToOne 
	Company company;
	
	public Employee() {
		
	}
	
	public Employee(long id, @NotBlank String name, @NotBlank String workPosition, @Min(0) int pay,
			@Past LocalDateTime firstWorkingDay) {

		this.id = id;
		this.name = name;
		this.workPosition = workPosition;
		this.pay = pay;
		this.firstWorkingDay = firstWorkingDay;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getWorkPosition() {
		return workPosition;
	}

	public int getPay() {
		return pay;
	}

	public LocalDateTime getFirstWorkingDay() {
		return firstWorkingDay;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setWorkPosition(String workPosition) {
		this.workPosition = workPosition;
	}

	public void setPay(int pay) {
		this.pay = pay;
	}

	public void setFirstWorkingDay(LocalDateTime firstWorkingDay) {
		this.firstWorkingDay = firstWorkingDay;
	}

}
