package hu.webuni.hr.domi.model;

import java.time.LocalDate;

import hu.webuni.hr.domi.model.Leave.Status;

public class LeaveSearchCriteria {

	private Status status;
	private Employee createdBy;
	private Employee superior;

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

	public Employee getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Employee createdBy) {
		this.createdBy = createdBy;
	}

	public Employee getSuperior() {
		return superior;
	}

	public void setSuperior(Employee superior) {
		this.superior = superior;
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
