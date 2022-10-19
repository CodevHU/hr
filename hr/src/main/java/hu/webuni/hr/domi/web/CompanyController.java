package hu.webuni.hr.domi.web;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.websocket.server.PathParam;

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

import hu.webuni.hr.domi.dto.CompanyDto;
import hu.webuni.hr.domi.dto.EmployeeDto;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

	Map<Long, CompanyDto> allCompany = new HashMap<>();

	{

		allCompany.put(1L, new CompanyDto(1, "01-06-016428", "TÁRSALGÓ BT", "1213 Budapest, Hollandi út 107/a.",
				new ArrayList<>(List.of(
						new EmployeeDto(1, "Kóst Elek", "CEO", 1000000, LocalDateTime.of(2010, 1, 14, 10, 34)),
						new EmployeeDto(2, "Lapos Elemér", "Coordinator", 200000,
								LocalDateTime.of(2014, 1, 14, 10, 34)),
						new EmployeeDto(3, "Lusta Gyula", "Manager", 300000, LocalDateTime.of(2018, 1, 14, 10, 34)),
						new EmployeeDto(4, "Kiss Zoltán", "Project Manager", 800000,
								LocalDateTime.of(2020, 1, 14, 10, 34))))));

		allCompany.put(2L,
				new CompanyDto(2, "01-06-613027", "CAC BT", "1222 Budapest, Lugosi u. 1.", new ArrayList<>(List.of(
						new EmployeeDto(1, "Fix László", "CEO", 2000000, LocalDateTime.of(2000, 1, 14, 10, 34)),
						new EmployeeDto(2, "Nagy István", "CTO", 800000, LocalDateTime.of(2010, 1, 14, 10, 34)),
						new EmployeeDto(3, "Kis Elek", "Operátor", 100000, LocalDateTime.of(2020, 1, 14, 10, 34))))));

		allCompany.put(3L, new CompanyDto(3, "01-06-748293", "TÁRSASVÁR Bt.", "1161 Budapest, Csömöri út 122.",
				new ArrayList<>(List.of(
						new EmployeeDto(1, "Kis Etelka", "CEO", 1000000, LocalDateTime.of(1998, 1, 14, 10, 34)),
						new EmployeeDto(2, "Nagy Béla", "Eladó", 200000, LocalDateTime.of(2010, 1, 14, 10, 34))))));

	}

	@GetMapping
	public List<CompanyDto> getCompanies(@RequestParam Optional<Boolean> full) {

		if (full.orElse(false)) {
			return new ArrayList<>(allCompany.values());
		} else {
			return allCompany.values().stream().map(c -> createCompanyWithoutEmployees(c)).collect(Collectors.toList());
		}
	}

	private CompanyDto createCompanyWithoutEmployees(CompanyDto c) {
		return new CompanyDto(c.getId(), c.getRegistrationNumber(), c.getName(), c.getAddress(), null);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CompanyDto> getCompanyById(@PathVariable int id) {

		List<CompanyDto> findById = allCompany.entrySet().stream().filter(e -> e.getValue().getId() == id)
				.map(Map.Entry::getValue).collect(Collectors.toList());

		if (findById.size() != 1) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(findById.get(0));
	}

	@PostMapping
	public ResponseEntity<CompanyDto> addCompany(@RequestBody CompanyDto company) {
		allCompany.put(company.getId(), company);

		return ResponseEntity.ok(company);
	}

	@PutMapping("/{id}")
	public ResponseEntity<CompanyDto> updateCompany(@PathVariable long id, @RequestBody CompanyDto company) {

		if (!allCompany.containsKey(id)) {
			return ResponseEntity.notFound().build();
		}

		company.setId(id);
		allCompany.put(id, company);

		return ResponseEntity.ok(allCompany.get(id));

	}

	@DeleteMapping("/{id}")
	public boolean deleteCompany(@PathVariable long id) {

		if (!allCompany.containsKey(id)) {
			return false;
		}

		allCompany.remove(id);

		return true;

	}

	@PostMapping("/{id}/employees")
	public ResponseEntity<CompanyDto> addEmployeeToCompany(@RequestBody EmployeeDto employee, @PathVariable int id) {

		long searchedId;

		List<Long> findById = searchedListKeyById(id);

		if (findById.size() != 1) {
			return ResponseEntity.notFound().build();
		}

		searchedId = findById.get(0);

		List<EmployeeDto> employees = allCompany.get(searchedId).getEmployees();
		employees.add(employee);

		allCompany.get(searchedId).setEmployees(employees);

		return ResponseEntity.ok(allCompany.get(searchedId));

	}

	@DeleteMapping("/{id}/employees/{eid}")
	public boolean deleteEmployee(@PathVariable int id, @PathVariable int eid) {

		long searchedId;
		List<Long> findById = searchedListKeyById(id);

		if (findById.size() != 1) {
			return false;
		}

		searchedId = findById.get(0);
		allCompany.get(searchedId).getEmployees().removeIf(e -> e.getIdentifier() == eid);

		return true;

	}
	
	@PutMapping("/{id}/employees")
	public ResponseEntity<CompanyDto> changeAllEmployeeInCompany(@PathVariable int id, @RequestBody List<EmployeeDto> employees){
		long searchedId;
		List<Long> findById = searchedListKeyById(id);

		if (findById.size() != 1) {
			return ResponseEntity.notFound().build();
		}

		searchedId = findById.get(0);
		
		allCompany.get(searchedId).setEmployees(employees);
		
		return ResponseEntity.ok(allCompany.get(searchedId));
		
	}

	private List<Long> searchedListKeyById(int id) {

		return allCompany.entrySet().stream().filter(e -> e.getValue().getId() == id).map(Map.Entry::getKey)
				.collect(Collectors.toList());
	}
}
