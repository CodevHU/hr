package hu.webuni.hr.domi.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import hu.webuni.hr.domi.model.Employee;

public interface EmployeeService {

	public int getPayRaisePercent(Employee employee);

	Employee save(Employee employee);

	List<Employee> getAll();

	void delete(long id);

	void update(long id, Employee employee);

	Page<Employee> filterByPay(int pay, Pageable paging);

	Page<Employee> filterByPosition(String position, Pageable paging);

	Page<Employee> filterByNameFirstCharacter(char firstChar, Pageable paging);

	Page<Employee> filterByFirstWorkDay(LocalDateTime startDate, LocalDateTime endDate, Pageable paging);

	public Employee findById(long id);
	
}
