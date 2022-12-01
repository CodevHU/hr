package hu.webuni.hr.domi.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import hu.webuni.hr.domi.model.Employee;

public class EmployeeUser extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Employee employee;

	public EmployeeUser(String username, String password, Collection<? extends GrantedAuthority> authorities,
			Employee employee) {
		super(username, password, authorities);
		this.employee = employee;
		
		System.out.println(employee.getId());
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}
