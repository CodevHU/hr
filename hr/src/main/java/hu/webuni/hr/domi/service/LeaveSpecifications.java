package hu.webuni.hr.domi.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.data.jpa.domain.Specification;

import hu.webuni.hr.domi.model.Employee_;
import hu.webuni.hr.domi.model.Leave;
import hu.webuni.hr.domi.model.Leave.Status;
import hu.webuni.hr.domi.model.Leave_;

public class LeaveSpecifications  {

	public static Specification<Leave> hasStatus(Status status) {
		return  (root, cq, cb) -> cb.equal(root.get(Leave_.STATUS), status);
	}

	public static Specification<Leave> hasCreatedEmployeeName(String name) {
		return  (root, cq, cb) -> cb.like(root.get(Leave_.CREATED_BY).get(Employee_.NAME), name + "%");
	}

	public static Specification<Leave> hasSuperiorEmployeeName(String name) {
		return  (root, cq, cb) -> cb.equal(root.get(Leave_.SUPERIOR).get(Employee_.NAME), name + "%");
	}

	public static Specification<Leave> hasCreatedAt(LocalDate createdAtFrom, LocalDate createdAtTo) {

		LocalDateTime fromDate = LocalDateTime.of(
				createdAtFrom, 
				LocalTime.of(0, 0)
			);
		
		LocalDateTime toDate = LocalDateTime.of(
				createdAtTo, 
				LocalTime.of(0, 0)
			);
		
		return (root, cq, cb) -> cb.between(root.get(Leave_.CREATED_AT), fromDate, toDate);
	}

	public static Specification<Leave> hasLeaveDate(LocalDate leaveDateFrom, LocalDate leaveDateTo) {
			
		return (root, cq, cb) -> cb.or(
				cb.between(root.get(Leave_.START_DATE), leaveDateFrom, leaveDateTo),
				cb.between(root.get(Leave_.END_DATE), leaveDateFrom, leaveDateTo)
			);
	}

	

}
