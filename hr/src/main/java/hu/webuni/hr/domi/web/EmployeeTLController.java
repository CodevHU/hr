package hu.webuni.hr.domi.web;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import hu.webuni.hr.domi.model.Employee;

@Controller
public class EmployeeTLController {
	
	private List<Employee> allEmployees = new ArrayList<>();
	
	{
		allEmployees.add(new Employee(1,"Kóst Elek","CEO",1000000,LocalDateTime.of(2010, 1, 14, 10, 34)));
		allEmployees.add(new Employee(2,"Lapos Elemér","Coordinator",200000,LocalDateTime.of(2014, 1, 14, 10, 34)));
		allEmployees.add(new Employee(3,"Lusta Gyula","Manager",300000,LocalDateTime.of(2018, 1, 14, 10, 34)));
		allEmployees.add(new Employee(4,"Kiss Zoltán","Project Manager",800000,LocalDateTime.of(2020, 1, 14, 10, 34)));
		
	}

	@GetMapping("/employees")
	public String getAll(Map<String, Object> model){
		
		model.put("employees", allEmployees);
		model.put("newEmployee", new Employee());
		
		return "employees";
	}
	
	@PostMapping("/employees")
	public String addEmployee(Employee employee){
		
		allEmployees.add(employee);
		
		return "redirect:employees";
	}
	
}
