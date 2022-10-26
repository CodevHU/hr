package hu.webuni.hr.domi.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.FieldError;

public class MyError {

	private String message;
	private int errorCode;
	private List<FieldError> fieldErrors = new ArrayList<>();
	
	public MyError(String message, int errorCode) {
		super();
		this.message = message;
		this.errorCode = errorCode;
	}
	
	public List<FieldError> getFieldErrors() {
		return fieldErrors;
	}

	public void setFieldErrors(List<FieldError> fieldErrors) {
		this.fieldErrors = fieldErrors;
	}

	public String getMessage() {
		return message;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

}
