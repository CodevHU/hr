package hu.webuni.hr.domi.security;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import hu.webuni.hr.domi.config.HrConfigProperties;
import hu.webuni.hr.domi.model.Employee;
import hu.webuni.hr.domi.repository.EmployeeRepository;

@Service
public class JWTService {

	@Autowired
	HrConfigProperties config;

	@Autowired
	EmployeeRepository employeeRepository;

	public String createToken(EmployeeUser principal) {

		return JWT.create().withSubject(principal.getUsername())
				.withClaim("employeeId", principal.getEmployee().getId())
				.withClaim("employeeName", principal.getEmployee().getName())
				.withClaim("subEmployees", getSubEmployees(principal.getEmployee()))
				.withClaim("superior", getEmployeeMap(principal.getEmployee().getSuperior()))
				.withExpiresAt(new Date(System.currentTimeMillis()
						+ TimeUnit.MINUTES.toMillis(config.getSecurity().getExpiresMinutes())))
				.withIssuer(config.getSecurity().getIssuer()).sign(Algorithm.HMAC256(config.getSecurity().getSecret()));
	}

	private List<Map<String, String>> getSubEmployees(Employee employee) {

		List<Employee> superior = employeeRepository.findBySuperior(employee);
		List<Map<String, String>> subEmp = new ArrayList<>();

		superior.forEach(e -> {
			Map<String, String> employeeData = getEmployeeMap(e);
			subEmp.add(employeeData);
		});

		return subEmp;

	}

	private HashMap<String, String> getEmployeeMap(Employee e) {
		return new HashMap<>() {
			{
				put("id", Long.toString(e.getId()));
				put("username", e.getUsername());
			}
		};
	}

}
