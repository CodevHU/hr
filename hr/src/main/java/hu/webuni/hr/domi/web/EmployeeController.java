package hu.webuni.hr.domi.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hu.webuni.hr.domi.dto.EmployeeDto;
import hu.webuni.hr.domi.mapper.EmployeeMapper;
import hu.webuni.hr.domi.model.Employee;
import hu.webuni.hr.domi.service.AbstractEmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
	
	@Autowired
	AbstractEmployeeService employeeService;
	
	@Autowired
	EmployeeMapper employeeMapper;

	

	@GetMapping
	public List<EmployeeDto> getAll() {
		return employeeMapper.employeeToDtos(employeeService.getAll());
	}
	
	
	@GetMapping("/{id}") 
	public EmployeeDto getById(@PathVariable long id) {
		
		Employee employee = employeeService.findById(id);
		return employeeMapper.employeeToDto(employee);
		
	}
	
	
	@GetMapping("/filter")
	public List<EmployeeDto> getFilterListByPay(@RequestParam(name = "pay") int pay) {
		
		List<EmployeeDto> filteredEmployees = employeeMapper.employeeToDtos(employeeService.filterByPay(pay));
		return filteredEmployees;
	}
	
		
	@PostMapping
	public EmployeeDto addEmployee(@RequestBody @Valid EmployeeDto employee){
		
		employeeService.save(employeeMapper.employeeDtoToEmployee(employee));
		
		return employee;
	}
	
	@PutMapping("/{id}")
	public EmployeeDto updateEmployee(@PathVariable long id, @RequestBody @Valid EmployeeDto employee){

		employeeService.update(id, employeeMapper.employeeDtoToEmployee(employee));
		return employee;
		
	}

	@DeleteMapping("/{id}")
	public boolean deleteEmployee(@PathVariable long id){
		
		employeeService.delete(id);
		
		return true;
		
	}
//
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
