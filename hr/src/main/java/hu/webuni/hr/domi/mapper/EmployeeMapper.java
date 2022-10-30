package hu.webuni.hr.domi.mapper;

import java.util.List;

import javax.validation.Valid;

import org.mapstruct.Mapper;

import hu.webuni.hr.domi.dto.EmployeeDto;
import hu.webuni.hr.domi.model.Employee;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

	List<EmployeeDto> employeeToDtos(List<Employee> employees);

	EmployeeDto employeeToDto(Employee employee);

	Employee employeeDtoToEmployee(@Valid EmployeeDto employee);

	List<Employee> employeeDtosToEmployee(List<EmployeeDto> employees);
	
}
