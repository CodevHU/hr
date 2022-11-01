package hu.webuni.hr.domi.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import hu.webuni.hr.domi.config.HrConfigProperties;
import hu.webuni.hr.domi.model.Employee;
import hu.webuni.hr.domi.repository.EmployeeRepository;

@Service
public abstract class AbstractEmployeeService implements EmployeeService {

	@Autowired
	HrConfigProperties config;
	
	@Autowired
	EmployeeRepository employeeRepository;

	@Transactional
	public Employee save(Employee employee) {
		
		return employeeRepository.save(employee);
	}

	
	@Override
	public List<Employee> getAll() {
		return new ArrayList<>(employeeRepository.findAll());
	}
	
	@Override
	public Employee findById(long id) {

		if(employeeRepository.existsById(id)) {
			return employeeRepository.findById(id).get();
		} else 
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		
		
	}

	@Override
	@Transactional
	public void delete(long id) {
		
		Optional<Employee> employee = employeeRepository.findById(id);
			
		if (!employee.isEmpty()) {
			employeeRepository.deleteById(id);
		}

		else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}

	@Override
	@Transactional
	public void update(long id, Employee employee) {
		
		employee.setId(id);
		if(employeeRepository.existsById(employee.getId()))
			employeeRepository.save(employee);
		else 
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}
	
	
	@Override
	public List<Employee> filterByPay(int pay) {
		
		List<Employee> filteredEmployees = employeeRepository.findByPayGreaterThan(pay);

		return filteredEmployees;

	}
	
	@Override
	public List<Employee> filterByWorkPosition(String workPosition){
		List<Employee> filteredEmployees = employeeRepository.findByWorkPosition(workPosition);
		return filteredEmployees;
	}
	
	@Override
	public List<Employee> filterByNameFirstCharacter(char firstChar){
		List<Employee> filteredEmployees = employeeRepository.findByNameLikeIgnoreCase(firstChar + "%");
		return filteredEmployees;
	}
	
	@Override
	public List<Employee> filterByFirstWorkDay(LocalDateTime startDate, LocalDateTime endDate){
		List<Employee> filteredEmployees = employeeRepository.findByFirstWorkingDayBetween(startDate,endDate);
		return filteredEmployees;
	}

}
