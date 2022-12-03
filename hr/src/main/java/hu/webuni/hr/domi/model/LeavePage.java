package hu.webuni.hr.domi.model;

import java.util.List;

public class LeavePage {
	
private long currentPage;
	
	private long totalItems;
	
	private long totalPages;
	
	private List<Leave> leaves;
	
	public LeavePage() {
			
	}	

	public LeavePage(long currentPage, long totalItems, long totalPages, List<Leave> leaves) {
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

	public List<Leave> getLeaves() {
		return leaves;
	}

	public void setLeaves(List<Leave> leaves) {
		this.leaves = leaves;
	}
	

}
