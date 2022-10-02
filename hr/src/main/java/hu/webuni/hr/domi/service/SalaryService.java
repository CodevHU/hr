package hu.webuni.hr.domi.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import hu.webuni.hr.domi.model.Employee;

@Service
public class SalaryService {
	
	private EmployeeService employeeService;
	
	public SalaryService(@Lazy EmployeeService employeeService) {
		
		this.employeeService = employeeService;
		
	}
	
	public Employee setSalary(Employee employee) {
		
		int salary = this.employeeService.getPayRaisePercent(employee);	
		int newPay = employee.getPay() + ((employee.getPay() / 100) * salary);
		
		employee.setPay(newPay);
		
		return employee;
		
	}
	
	
	
}
