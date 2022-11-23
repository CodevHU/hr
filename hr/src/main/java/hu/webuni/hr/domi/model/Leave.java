package hu.webuni.hr.domi.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Leave {
	
	public enum Status {PENDING, ACCAPTED, DENIDED}

	@Id
	@GeneratedValue
	private long id;

	@NotNull
	@FutureOrPresent
	private LocalDate startDate;

	@NotNull
	@FutureOrPresent
	private LocalDate endDate;

	@ManyToOne
	private Employee createdBy;
	
	@Enumerated(EnumType.STRING)
	private Status status = Status.PENDING;

	@CreationTimestamp
	private LocalDateTime createdAt;
	
	
	public Leave() {
		
	}	

	public Leave(long id, @NotNull @FutureOrPresent LocalDate startDate, @NotNull @FutureOrPresent LocalDate endDate,
			Employee createdBy, LocalDateTime createdAt) {
		super();
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.createdBy = createdBy;
		this.createdAt = createdAt;
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
	
	

}
