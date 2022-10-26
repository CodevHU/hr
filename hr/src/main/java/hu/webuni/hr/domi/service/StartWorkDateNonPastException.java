package hu.webuni.hr.domi.service;

public class StartWorkDateNonPastException extends RuntimeException {

	public StartWorkDateNonPastException() {
		super("The work start date cannot be in the future");
	}
	
}
