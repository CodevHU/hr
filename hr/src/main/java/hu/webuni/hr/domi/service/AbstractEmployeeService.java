package hu.webuni.hr.domi.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import hu.webuni.hr.domi.config.HrConfigProperties;
import hu.webuni.hr.domi.model.Employee;

@Service
public abstract class AbstractEmployeeService implements EmployeeService {

	@Autowired
	HrConfigProperties config;

	private Map<Long, Employee> allEmployees = new HashMap<>();

	{
		allEmployees.put(1L, new Employee(1, "Kóst Elek", "CEO", 1000000, LocalDateTime.of(2010, 1, 14, 10, 34)));
		allEmployees.put(2L,
				new Employee(2, "Lapos Elemér", "Coordinator", 200000, LocalDateTime.of(2014, 1, 14, 10, 34)));
		allEmployees.put(3L, new Employee(3, "Lusta Gyula", "Manager", 300000, LocalDateTime.of(2018, 1, 14, 10, 34)));
		allEmployees.put(4L,
				new Employee(4, "Kiss Zoltán", "Project Manager", 800000, LocalDateTime.of(2020, 1, 14, 10, 34)));
	}

	public Employee save(Employee employee) {
		allEmployees.put(employee.getIdentifier(), employee);

		return employee;
	}

	public List<Employee> getAll() {
		return new ArrayList<>(allEmployees.values());
	}

	public Employee findById(long id) {

		if (allEmployees.containsKey(id)) {
			return allEmployees.get(id);
		}

		else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);

	}

	public void delete(long id) {

		if (allEmployees.containsKey(id)) {
			allEmployees.remove(id);
		}

		else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}

	public void update(long id, Employee employee) {

		if (allEmployees.containsKey(id)) {
			allEmployees.put(employee.getIdentifier(), employee);
		}

		else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}

	public List<Employee> filterByPay(int pay) {

		List<Employee> filteredEmployees = this.getAll().stream().filter(employee -> employee.getPay() > pay)
				.collect(Collectors.toList());

		return filteredEmployees;

	}

}
