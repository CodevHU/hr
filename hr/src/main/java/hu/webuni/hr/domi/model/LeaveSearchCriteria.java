package hu.webuni.hr.domi.model;

import java.time.LocalDate;

import hu.webuni.hr.domi.model.Leave.Status;

public class LeaveSearchCriteria {

	private Status status;
	private String employee;
	private String approver;

	private LocalDate createdAtFrom;
	private LocalDate createdAtTo;

	private LocalDate leaveDateFrom;
	private LocalDate leaveDateTo;

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	public String getEmployee() {
		return employee;
	}

	public void setEmployee(String employee) {
		this.employee = employee;
	}

	public String getApprover() {
		return approver;
	}

	public void setApprover(String approver) {
		this.approver = approver;
	}

	public LocalDate getCreatedAtFrom() {
		return createdAtFrom;
	}

	public void setCreatedAtFrom(LocalDate createdAtFrom) {
		this.createdAtFrom = createdAtFrom;
	}

	public LocalDate getCreatedAtTo() {
		return createdAtTo;
	}

	public void setCreatedAtTo(LocalDate createdAtTo) {
		this.createdAtTo = createdAtTo;
	}

	public LocalDate getLeaveDateFrom() {
		return leaveDateFrom;
	}

	public void setLeaveDateFrom(LocalDate leaveDateFrom) {
		this.leaveDateFrom = leaveDateFrom;
	}

	public LocalDate getLeaveDateTo() {
		return leaveDateTo;
	}

	public void setLeaveDateTo(LocalDate leaveDateTo) {
		this.leaveDateTo = leaveDateTo;
	}

}
