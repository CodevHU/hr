package hu.webuni.hr.domi.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.webuni.hr.domi.config.HrConfigProperties;
import hu.webuni.hr.domi.config.HrConfigProperties.Year;
import hu.webuni.hr.domi.model.Employee;

@Service
public class SmartEmployeeService implements EmployeeService {
	
	@Autowired
	HrConfigProperties config;

	@Override
	public int getPayRaisePercent(Employee employee) {
		
		long months = ChronoUnit.MONTHS.between(employee.getFirstWorkingDay(), LocalDateTime.now());
		int percent = config.getSalary().getSmart().getDef().getPercent();
		float howLongWorking = (float) months / 12;
		
		Map<Integer, Year> years = config.getSalary().getSmart().getYears();
		
		for(Map.Entry<Integer, Year> year : years.entrySet()) {
		    
			if(howLongWorking >= year.getValue().getLimit()) {
	    		percent = year.getValue().getPercent();
	    		break;
	    	}
		}
		
		return percent;
		
	}

}
