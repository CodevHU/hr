package hu.webuni.hr.domi.web;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
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

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	Map<Long, EmployeeDto> allEmployees = new HashMap<>();

	{
		allEmployees.put(1L, 
				new EmployeeDto(1, "Kóst Elek", "CEO", 1000000, LocalDateTime.of(2010, 1, 14, 10, 34)));
		allEmployees.put(2L,
				new EmployeeDto(2, "Lapos Elemér", "Coordinator", 200000, LocalDateTime.of(2014, 1, 14, 10, 34)));
		allEmployees.put(3L,
				new EmployeeDto(3, "Lusta Gyula", "Manager", 300000, LocalDateTime.of(2018, 1, 14, 10, 34)));
		allEmployees.put(4L,
				new EmployeeDto(4, "Kiss Zoltán", "Project Manager", 800000, LocalDateTime.of(2020, 1, 14, 10, 34)));
	}

	@GetMapping
	public List<EmployeeDto> getAll() {
		return new ArrayList<>(allEmployees.values());
	}
	
	@GetMapping("/filter")
	public List<EmployeeDto> getFilterListByPay(@RequestParam(name = "pay") int pay) {
		
		List<EmployeeDto> filteredEmployees = allEmployees
				.entrySet()
				.stream()
				.filter(employee -> employee.getValue().getPay() > pay)
				.map(Map.Entry::getValue)
				.collect(Collectors.toList());
		
		return filteredEmployees;
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<EmployeeDto> getById(@PathVariable long id) {
		
		if(!allEmployees.containsKey(id)) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(allEmployees.get(id));
		
	}
	
	@PostMapping
	public ResponseEntity<EmployeeDto> addEmployee(@RequestBody EmployeeDto employee){
		allEmployees.put(employee.getIdentifier(), employee);
		
		return ResponseEntity.ok(employee);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable long id, @RequestBody EmployeeDto employee){
		
		if(!allEmployees.containsKey(id)) {
			return ResponseEntity.notFound().build();
		}
		
		employee.setIdentifier(id);
		allEmployees.put(id,employee);
		
		return ResponseEntity.ok(allEmployees.get(id));
		
	}
	
	@DeleteMapping("/{id}")
	public boolean deleteEmployee(@PathVariable long id){
		
		if(!allEmployees.containsKey(id)) {
			return false;
		}
		
		allEmployees.remove(id);
		
		return true;
		
	}

}
