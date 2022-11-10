package hu.webuni.hr.domi.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import hu.webuni.hr.domi.config.HrConfigProperties;
import hu.webuni.hr.domi.model.Employee;
import hu.webuni.hr.domi.model.Position;
import hu.webuni.hr.domi.repository.EmployeeRepository;
import hu.webuni.hr.domi.repository.PositionRepository;

@Service
public abstract class AbstractEmployeeService implements EmployeeService {

	@Autowired
	HrConfigProperties config;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	PositionRepository positionRepository;
	

	@Transactional
	public Employee save(Employee employee) {
		clearCompanyAndSetPosition(employee);
		return employeeRepository.save(employee);
	}
	
	private void clearCompanyAndSetPosition(Employee employee) {
		employee.setCompany(null);
		String positionName = employee.getPosition().getPositionName();
		Position position = null;
		
		if(positionName != null) {
			List<Position> positions = positionRepository.findByPositionName(positionName);
			
			if(positions.isEmpty()) {
				position = positionRepository.save(new Position(positionName, null));
			} else {
				position = positions.get(0);
			}
			
			employee.setPosition(position);
		}
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
		if(employeeRepository.existsById(employee.getId())) {
			clearCompanyAndSetPosition(employee);
			employeeRepository.save(employee);
			}
		else 
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}
	
	
	@Override
	public Page<Employee> filterByPay(int pay, Pageable page) {
		
		Page<Employee> filteredEmployees = employeeRepository.findByPayGreaterThan(pay, page);

		return filteredEmployees;

	}
	
	@Override
	public Page<Employee> filterByPosition(String position, Pageable page){
		Page<Employee> filteredEmployees = employeeRepository.findByPosition(position, page);
		
		return filteredEmployees;
	}
	
	@Override
	public Page<Employee> filterByNameFirstCharacter(char firstChar, Pageable page){
		
		Page<Employee> filteredEmployees = employeeRepository.findByNameLikeIgnoreCase(firstChar + "%",page);
		return filteredEmployees;
	}
	
	@Override
	public Page<Employee> filterByFirstWorkDay(LocalDateTime startDate, LocalDateTime endDate, Pageable page){
		Page<Employee> filteredEmployees = employeeRepository.findByFirstWorkingDayBetween(startDate,endDate,page);
		return filteredEmployees;
	}

}
