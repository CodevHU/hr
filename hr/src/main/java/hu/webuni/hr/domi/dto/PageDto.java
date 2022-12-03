package hu.webuni.hr.domi.dto;

import java.util.List;


public class PageDto {
	
	private long currentPage;
	
	private long totalItems;
	
	private long totalPages;
	
	private List<LeaveDto> leaves;
	
	public PageDto() {
			
	}	

	public PageDto(long currentPage, long totalItems, long totalPages, List<LeaveDto> leaves) {
		super();
		this.leaves = leaves;
		this.currentPage = currentPage;
		this.totalItems = totalItems;
		this.totalPages = totalPages;
	}

	public long getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public long getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(int totalItems) {
		this.totalItems = totalItems;
	}

	public long getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public List<LeaveDto> getLeaves() {
		return leaves;
	}

	public void setLeaves(List<LeaveDto> leaves) {
		this.leaves = leaves;
	}
	
	
	
	
	
	
	
}
