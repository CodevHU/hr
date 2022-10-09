package hu.webuni.hr.domi.model;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

public class Employee {
	
	private long id;
	private String name;
	private String workPosition;
	private int pay;
	
	@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm") 
	private LocalDateTime firstWorkingDay;
	
	public Employee() {
		
	}
	
	public Employee(long identifier, String name, String workPosition, int pay, LocalDateTime firstWorkingDay) {
		
		this.id = identifier;
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
	
	public void setPay(int pay) {
		this.pay = pay;
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

	public void setFirstWorkingDay(LocalDateTime firstWorkingDay) {
		this.firstWorkingDay = firstWorkingDay;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", workPosition=" + workPosition + ", pay="
				+ pay + ", firstWorkingDay=" + firstWorkingDay + "]";
	}
	
	
	
	
	
}
