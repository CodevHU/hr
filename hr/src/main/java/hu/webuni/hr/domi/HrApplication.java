package hu.webuni.hr.domi;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import hu.webuni.hr.domi.model.Employee;
import hu.webuni.hr.domi.service.SalaryService;

@SpringBootApplication
public class HrApplication implements CommandLineRunner {

	@Autowired
	private SalaryService saleryService;
	
	public static void main(String[] args) {
		SpringApplication.run(HrApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(saleryService.setSalary(new Employee(1,"Kóst Elek","CEO",1000000,LocalDateTime.of(2010, 1, 14, 10, 34))));
		System.out.println(saleryService.setSalary(new Employee(2,"Lapos Elemér","Coordinator",200000,LocalDateTime.of(2014, 1, 14, 10, 34))));
		System.out.println(saleryService.setSalary(new Employee(3,"Lusta Gyula","Manager",300000,LocalDateTime.of(2018, 1, 14, 10, 34))));
		System.out.println(saleryService.setSalary(new Employee(4,"Kiss Zoltán","Project Manager",800000,LocalDateTime.of(2020, 1, 14, 10, 34))));
	}

}
