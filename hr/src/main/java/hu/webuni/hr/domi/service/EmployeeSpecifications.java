package hu.webuni.hr.domi.service;

import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.data.jpa.domain.Specification;

import hu.webuni.hr.domi.model.Company_;
import hu.webuni.hr.domi.model.Employee;
import hu.webuni.hr.domi.model.Employee_;
import hu.webuni.hr.domi.model.Position_;

public class EmployeeSpecifications  {

	public static Specification<Employee> hasId(long id) {
		return (root, cq, cb) -> cb.equal(root.get(Employee_.ID),id);
	}

	public static Specification<Employee> hasName(String name) {
		return (root, cq, cb) -> cb.like(
				cb.lower(
						root.get(Employee_.NAME)
				), name.toLowerCase() + "%");
	}

	public static Specification<Employee> hasPosition(String position) {
		return (root, cq, cb) -> cb.equal(root.get(Employee_.position).get(Position_.POSITION_NAME), position);
	}

	public static Specification<Employee> hasSalary(int salary) {
		int starSalary = (int) (salary * 0.95); 
		int endSalary = (int) (salary * 1.05); 
		
		return (root, cq, cb) -> cb.and(
				cb.ge(root.get(Employee_.PAY), starSalary),
				cb.le(root.get(Employee_.PAY), endSalary)
			);
	}

	public static Specification<Employee> hasStartWorkingDay(LocalDateTime firstWorkDate) {
		
		LocalDateTime date = LocalDateTime.of(
				firstWorkDate.toLocalDate(), 
				LocalTime.of(0, 0)
			);
				
		return (root, cq, cb) -> cb.between(root.get(Employee_.FIRST_WORKING_DAY), date, date.plusDays(1));
	}

	public static Specification<Employee> hasCompanyName(String companyName) {
		return (root, cq, cb) -> cb.like(
				cb.lower(
						root.get(Employee_.COMPANY).get(Company_.NAME)
				), companyName.toLowerCase() + "%");
	}

}
