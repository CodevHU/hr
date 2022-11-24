package hu.webuni.hr.domi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import hu.webuni.hr.domi.model.Employee;
import hu.webuni.hr.domi.model.Leave;
import hu.webuni.hr.domi.repository.LeaveRepository;

@Service
public class LeaveService {
	
	@Autowired
	LeaveRepository leaveRepository;

	public Leave updateStatus(Employee superior, Leave leave) {
		return null;
	}
	
	public Leave delete(Leave leave) {
		return null;
	}
	
	public Page<Leave> getAll(Pageable page) {
		Page<Leave> leaves = leaveRepository.findAll(page);
		
		return leaves;
	}

	
}
