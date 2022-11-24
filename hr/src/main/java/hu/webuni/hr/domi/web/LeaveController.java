package hu.webuni.hr.domi.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.webuni.hr.domi.mapper.LeaveMapper;
import hu.webuni.hr.domi.model.Leave;
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
	
}
