package hu.webuni.hr.domi.service;

import org.springframework.stereotype.Service;

import hu.webuni.hr.domi.model.Employee;

@Service
public class DefaultEmployeeService extends AbstractEmployeeService {
	
	@Override
	public int getPayRaisePercent(Employee employee) {
		return config.getSalary().getDef().getPercent();
	}

}
