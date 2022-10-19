package hu.webuni.hr.domi.dto;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

public class EmployeeDto {

	private long id;
	private String name;
	private String workPosition;
	private int pay;

	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime firstWorkingDay;
	
	public EmployeeDto() {
		
	}
	
	public EmployeeDto(long id, String name, String workPosition, int pay, LocalDateTime firstWorkingDay) {
		this.id = id;
		this.name = name;
		this.workPosition = workPosition;
		this.pay = pay;
		this.firstWorkingDay = firstWorkingDay;
	}

	public long getIdentifier() {
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

	public void setIdentifier(long id) {
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
