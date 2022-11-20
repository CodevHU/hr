package hu.webuni.hr.domi.web;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import hu.webuni.hr.domi.dto.EmployeeDto;
import hu.webuni.hr.domi.mapper.EmployeeMapper;
import hu.webuni.hr.domi.model.Employee;
import hu.webuni.hr.domi.repository.EmployeeRepository;
import hu.webuni.hr.domi.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@Autowired
	EmployeeMapper employeeMapper;
	
	@Autowired
	EmployeeRepository employeeRepository;

	@GetMapping
	public List<EmployeeDto> getAll() {
		return employeeMapper.employeeToDtos(employeeService.getAll());
	}

	@GetMapping("/{id}")
	public EmployeeDto getById(@PathVariable long id) {

		Employee employee = employeeService.findById(id);
//				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

		return employeeMapper.employeeToDto(employee);

	}
	
	@PostMapping("/filterByExample")
	public List<EmployeeDto> filterByExample(@RequestBody EmployeeDto employeeExample){
		return employeeMapper.employeeToDtos(employeeService.findEmployees(employeeMapper.employeeDtoToEmployee(employeeExample)));
	}

	@GetMapping("/filter")
	public Map<String,Object> getFilterListByPay(@RequestParam(required = false, name = "pay") Integer pay,
			@RequestParam(required = false, name = "work_position") String position,
			@RequestParam(required = false, name = "first_character") String firstCharacter,
			@RequestParam(required = false, name = "from") String from,
			@RequestParam(required = false, name = "to") String to,
			@RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "3") int size
	        ) {
		
		Pageable paging = PageRequest.of(page, size);

		if (pay != null) {
			Page<Employee> filteredEmployees = employeeService.filterByPay(pay, paging);

			Map<String, Object> response = new HashMap<>();
		      response.put("employees", employeeMapper.employeeToDtos(filteredEmployees.getContent()));
		      response.put("currentPage", filteredEmployees.getNumber());
		      response.put("totalItems", filteredEmployees.getTotalElements());
		      response.put("totalPages", filteredEmployees.getTotalPages());
		      
			return response;

		} else if (position != null) {
			
			Page<Employee> filteredEmployees = employeeService.filterByPosition(position, paging);

			Map<String, Object> response = new HashMap<>();
		      response.put("employees", employeeMapper.employeeToDtos(filteredEmployees.getContent()));
		      response.put("currentPage", filteredEmployees.getNumber());
		      response.put("totalItems", filteredEmployees.getTotalElements());
		      response.put("totalPages", filteredEmployees.getTotalPages());
		      
			return response;
			
		} else if (firstCharacter != null) {

			Page<Employee> result = employeeService.filterByNameFirstCharacter(firstCharacter.charAt(0),paging);
			
			Map<String, Object> response = new HashMap<>();
		      response.put("employees", employeeMapper.employeeToDtos(result.getContent()));
		      response.put("currentPage", result.getNumber());
		      response.put("totalItems", result.getTotalElements());
		      response.put("totalPages", result.getTotalPages());
			
			return response;
			
		
		} else if (from != null && to != null) {
			
			LocalDateTime startDate = LocalDateTime.parse(from);
			LocalDateTime endDate = LocalDateTime.parse(to);
			
			Page<Employee> filteredEmployees = employeeService.filterByFirstWorkDay(startDate, endDate, paging);

			Map<String, Object> response = new HashMap<>();
		      response.put("employees", employeeMapper.employeeToDtos(filteredEmployees.getContent()));
		      response.put("currentPage", filteredEmployees.getNumber());
		      response.put("totalItems", filteredEmployees.getTotalElements());
		      response.put("totalPages", filteredEmployees.getTotalPages());
		      
			return response; 
		} else
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

	}

	@PostMapping
	public EmployeeDto addEmployee(@RequestBody @Valid EmployeeDto employeeDto) {

		Employee employee = employeeService.save(employeeMapper.employeeDtoToEmployee(employeeDto));

		return employeeMapper.employeeToDto(employee);
	}

	@PutMapping("/{id}")
	public EmployeeDto updateEmployee(@PathVariable long id, @RequestBody @Valid EmployeeDto employee) {

		employeeService.update(id, employeeMapper.employeeDtoToEmployee(employee));
		return employee;

	}

	@DeleteMapping("/{id}")
	public boolean deleteEmployee(@PathVariable long id) {

		employeeService.delete(id);

		return true;

	}
	
	@PostMapping("/salary/{id}")
	public int getSalary(@PathVariable long id) {
		
		Employee employee = employeeService.findById(id);
		return employeeService.getPayRaisePercent(employee);
	}

//	private void checkNegativePayment(EmployeeDto employee) {
//		if(employee.getPay() < 0) throw new PayNotNegativeException();
//	}
//	
//	private void checkFirstWorkDayDate(EmployeeDto employee) {
//		LocalDateTime now = LocalDateTime.now();  
//		
//		if(!now.isAfter(employee.getFirstWorkingDay()))
//			throw new StartWorkDateNonPastException();
//	}
}
