package hu.webuni.hr.domi.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Employee {

	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private long id;
	
	@NotBlank
	private String name;
	
	@Min(0)
	private int pay;

	@Past
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime firstWorkingDay;
	
	@ManyToOne
	Company company;
	
	@ManyToOne
	Position position;
	
	
	public Employee() {
		
	}
	
	public Employee(long id, @NotBlank String name, Position position, @Min(0) int pay,
			@Past LocalDateTime firstWorkingDay) {

		this.id = id;
		this.name = name;
		this.position = position;
		this.pay = pay;
		this.firstWorkingDay = firstWorkingDay;
	}
	
	

	public Employee(long id, @NotBlank String name, Position position, @Min(0) int pay,
			@Past LocalDateTime firstWorkingDay, Company company) {

		this.id = id;
		this.name = name;
		this.position = position;
		this.pay = pay;
		this.firstWorkingDay = firstWorkingDay;
		this.company = company;
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

	public Position getPosition() {
		return position;
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

	public void setPosition(Position position) {
		this.position = position;
	}

	public void setPay(int pay) {
		this.pay = pay;
	}

	public void setFirstWorkingDay(LocalDateTime firstWorkingDay) {
		this.firstWorkingDay = firstWorkingDay;
	}

}
