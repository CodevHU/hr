package hu.webuni.hr.domi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import hu.webuni.hr.domi.model.Employee;
import hu.webuni.hr.domi.model.Leave;
import hu.webuni.hr.domi.model.Leave.Status;
import hu.webuni.hr.domi.repository.LeaveRepository;

@Service
public class LeaveService {
	
	@Autowired
	LeaveRepository leaveRepository;

	
	public Page<Leave> getAll(Pageable page) {
		Page<Leave> leaves = leaveRepository.findAll(page);
		
		return leaves;
	}
	
	
	@Transactional
	public Leave updateStatus(long id, Employee superior, Status status) {
		
		Leave leave = leaveRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		
		leave.setSuperior(superior);
		leave.setStatus(status);
		
		return leaveRepository.save(leave);
		
	}
	

	@Transactional
	public void delete(long id) {
		
		Leave leave = leaveRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		
		if(leave.getStatus() != Status.PENDING) throw new ResponseStatusException(HttpStatus.NOT_FOUND); 
			
		leaveRepository.delete(leave);
		
	}
	
	@Transactional
	public Leave update(long id, Leave leave) {
		
		leaveRepository.findById(id)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		
		if(leave.getStatus() != Status.PENDING) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		
		leave.setId(id);
		return leaveRepository.save(leave);
	}

	
}
