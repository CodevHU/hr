package hu.webuni.hr.domi.mapper;

import java.util.List;

import javax.validation.Valid;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import hu.webuni.hr.domi.dto.EmployeeDto;
import hu.webuni.hr.domi.model.Employee;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

	List<EmployeeDto> employeeToDtos(List<Employee> employees);

	@Mapping(target = "company.employees", ignore = true)
	@Mapping(target = "position.employees", ignore = true)
	EmployeeDto employeeToDto(Employee employee);

	Employee employeeDtoToEmployee(@Valid EmployeeDto employee);

	List<Employee> employeeDtosToEmployee(List<EmployeeDto> employees);

	List<EmployeeDto> pageEmployeeToDtos(Page<Employee> filterByNameFirstCharacter);
	
}
