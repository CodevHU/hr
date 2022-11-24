package hu.webuni.hr.domi.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import hu.webuni.hr.domi.model.Employee;
import hu.webuni.hr.domi.model.Leave.Status;

public class LeaveDto {

	
	private long id;
	private LocalDate startDate;
	private LocalDate endDate;
	
	private Employee createdBy;
	private Employee superior;
	private Status status;

	private LocalDateTime createdAt;
	
	
	public LeaveDto() {
		
	}	

	public LeaveDto(long id, LocalDate startDate, LocalDate endDate,
			Employee createdBy, Employee superior, Status status, LocalDateTime createdAt) {
		super();
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.createdBy = createdBy;
		this.superior = superior;
		this.status = status;
		this.createdAt = createdAt;
	}
	
	public LeaveDto(long id, LocalDate startDate, LocalDate endDate,
			Employee createdBy) {
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.createdBy = createdBy;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public Employee getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Employee createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Employee getSuperior() {
		return superior;
	}

	public void setSuperior(Employee superior) {
		this.superior = superior;
	}
	
	

}
