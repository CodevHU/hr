package hu.webuni.hr.domi.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

import hu.webuni.hr.domi.model.Position;

public class EmployeeDto {

	private long id;
	
	@NotBlank
	private String name;
	
	@Min(0)
	private int pay;

	@Past
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime firstWorkingDay;
	
	private CompanyDto company;
	
	private Position position;
	
	
	public EmployeeDto() {
		
	}
	
	public EmployeeDto(long id, String name, Position position, int pay, LocalDateTime firstWorkingDay) {
		this.id = id;
		this.name = name;
		this.pay = pay;
		this.firstWorkingDay = firstWorkingDay;
	}
	
	public EmployeeDto(long id, @NotBlank String name, @Min(0) int pay,
			@Past LocalDateTime firstWorkingDay, CompanyDto company) {

		this.id = id;
		this.name = name;
		this.pay = pay;
		this.firstWorkingDay = firstWorkingDay;
		this.company = company;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
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



	public void setPay(int pay) {
		this.pay = pay;
	}

	public void setFirstWorkingDay(LocalDateTime firstWorkingDay) {
		this.firstWorkingDay = firstWorkingDay;
	}

	public CompanyDto getCompany() {
		return company;
	}

	public void setCompany(CompanyDto company) {
		this.company = company;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
	
	
	
	

}
