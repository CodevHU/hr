package hu.webuni.hr.domi.service;

import java.time.LocalDateTime;
import java.util.List;

import hu.webuni.hr.domi.model.Employee;

public interface EmployeeService {

	public int getPayRaisePercent(Employee employee);

	Employee save(Employee employee);

	List<Employee> getAll();

	void delete(long id);

	void update(long id, Employee employee);

	List<Employee> filterByPay(int pay);

	List<Employee> filterByWorkPosition(String workPosition);

	List<Employee> filterByNameFirstCharacter(char firstChar);

	List<Employee> filterByFirstWorkDay(LocalDateTime startDate, LocalDateTime endDate);

	public Employee findById(long id);
	
}
