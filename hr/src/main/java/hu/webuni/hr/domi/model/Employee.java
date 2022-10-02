package hu.webuni.hr.domi.model;

import java.time.LocalDateTime;

public class Employee {
	
	private long id;
	private String name;
	private String workPosition;
	private int pay;
	private LocalDateTime firstWorkingDay;
	
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

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", workPosition=" + workPosition + ", pay="
				+ pay + ", firstWorkingDay=" + firstWorkingDay + "]";
	}
	
	
	
	
	
}
