package hu.webuni.hr.domi.service;

public class PayNotNegativeException extends RuntimeException {

	public PayNotNegativeException() {
		super("The employee pay must be greater than 0");
	}
	
}
