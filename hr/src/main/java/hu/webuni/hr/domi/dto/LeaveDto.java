package hu.webuni.hr.domi.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import hu.webuni.hr.domi.model.Leave.Status;

public class LeaveDto {

	
	private long id;
	private LocalDate startDate;
	private LocalDate endDate;
	
	private EmployeeDto employee;
	private EmployeeDto approver;
	private Status status;

	private LocalDateTime createdAt;
	
	
	public LeaveDto() {
		
	}	

	public LeaveDto(long id, LocalDate startDate, LocalDate endDate,
			EmployeeDto employee, EmployeeDto approver, Status status, LocalDateTime createdAt) {
		super();
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.employee = employee;
		this.approver = approver;
		this.status = status;
		this.createdAt = createdAt;
	}
	
	public LeaveDto(long id, LocalDate startDate, LocalDate endDate,
			EmployeeDto employee) {
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.employee = employee;
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

	public EmployeeDto getCreatedBy() {
		return employee;
	}

	public void setEmployee(EmployeeDto employee) {
		this.employee = employee;
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

	public EmployeeDto getApprover() {
		return approver;
	}

	public void setApprover(EmployeeDto approver) {
		this.approver = approver;
	}
	
	

}
