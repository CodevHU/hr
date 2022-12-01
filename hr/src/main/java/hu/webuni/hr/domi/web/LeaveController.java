package hu.webuni.hr.domi.web;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.webuni.hr.domi.dto.LeaveDto;
import hu.webuni.hr.domi.mapper.LeaveMapper;
import hu.webuni.hr.domi.model.Leave;
import hu.webuni.hr.domi.model.LeaveSearchCriteria;
import hu.webuni.hr.domi.service.LeaveService;

@RestController
@RequestMapping("/api/leaves")
public class LeaveController {
	
	@Autowired
	private LeaveService leaveService;
	
	@Autowired
	private LeaveMapper leaveMapper;

	@GetMapping
	public Map<String,Object> getAll(Pageable pageable) {
		
		Page<Leave> leaves = leaveService.getAll(pageable);
		
		Map<String, Object> response = new HashMap<>();
	      response.put("leaves", leaveMapper.leavesToDtos(leaves.getContent()));
	      response.put("currentPage", leaves.getNumber());
	      response.put("totalItems", leaves.getTotalElements());
	      response.put("totalPages", leaves.getTotalPages());
	      
	      return response;
	}
	
	@GetMapping("/filter")
	public Map<String,Object> filter(Pageable pageable, @RequestBody LeaveSearchCriteria example) {
		
		Page<Leave> leaves = leaveService.find(pageable, example);
		
		Map<String, Object> response = new HashMap<>();
	      response.put("leaves", leaveMapper.leavesToDtos(leaves.getContent()));
	      response.put("currentPage", leaves.getNumber());
	      response.put("totalItems", leaves.getTotalElements());
	      response.put("totalPages", leaves.getTotalPages());
	      
	      return response;
	}
	
	@PostMapping("/feedback/{id}")
	public LeaveDto feedback(@PathVariable long id, @RequestBody @Valid LeaveDto leave) {
		return leaveMapper.leaveToSummaryDto(leaveService.updateStatus(id,leaveMapper.dtoToLeave(leave)));
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("#leave.createdBy.id == authentication.principal.employee.id")
	public LeaveDto update(@PathVariable long id, @RequestBody @Valid LeaveDto leave) {
		System.out.println(leave.getCreatedBy().getId());
		return leaveMapper.leaveToSummaryDto(leaveService.update(id, leaveMapper.dtoToLeave(leave)));
		
	}
	
	@DeleteMapping("/{id}")
	public boolean delete(@PathVariable long id) {
		
		leaveService.delete(id);
		
		return true;
		
	}
	
}
