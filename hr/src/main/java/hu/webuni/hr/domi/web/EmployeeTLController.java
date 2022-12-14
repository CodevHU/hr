package hu.webuni.hr.domi.web;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import hu.webuni.hr.domi.model.Employee;

@Controller
public class EmployeeTLController {

	private List<Employee> allEmployees = new ArrayList<>();

	{
		allEmployees.add(new Employee(1, "Kóst Elek", null, 1000000, LocalDateTime.of(2010, 1, 14, 10, 34)));
		allEmployees.add(new Employee(2, "Lapos Elemér", null, 200000, LocalDateTime.of(2014, 1, 14, 10, 34)));
		allEmployees.add(new Employee(3, "Lusta Gyula", null, 300000, LocalDateTime.of(2018, 1, 14, 10, 34)));
		allEmployees
				.add(new Employee(4, "Kiss Zoltán", null, 800000, LocalDateTime.of(2020, 1, 14, 10, 34)));

	}

	@GetMapping("/employees")
	public String getAll(Map<String, Object> model) {

		model.put("employees", allEmployees);
		model.put("newEmployee", new Employee());

		return "employees";
	}

	@PostMapping("/employees")
	public String addEmployee(Employee employee) {
		
		allEmployees.add(employee);
		
		return "redirect:/employees";
	}
	
	@PutMapping("/employees/update/{id}")
	public String updateEmployee(Employee employee, @PathVariable int id) {
		
		Employee findByIndex = allEmployees.stream()
				  .filter(e -> id == e.getId())
				  .findFirst()
				  .orElse(null);

		if (findByIndex != null) {
			allEmployees.set(allEmployees.indexOf(findByIndex), employee);
		}

		return "redirect:/employees";
	}

	@GetMapping("/employees/{id}")
	public String getEmployeeById(Map<String, Object> model, @PathVariable int id) {

		for (Employee employee : allEmployees) {
	        if (employee.getId() == id) {
	   
	    		model.put("employee", employee);
	    		return "employees_edit";
	    		
	        }
	    }

		return "employees_edit";
	}
	
	
	@GetMapping("/employees/delete/{id}")
	public String deleteEmployee(Map<String, Object> model, @PathVariable int id) {
		
		Employee findByIndex = allEmployees.stream()
				  .filter(e -> id == e.getId())
				  .findFirst()
				  .orElse(null);
		
		if (findByIndex != null) {
			allEmployees.remove(allEmployees.indexOf(findByIndex));
		}

		return "redirect:/employees";
	}

}
