package hu.webuni.hr.domi.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import hu.webuni.hr.domi.service.PayNotNegativeException;
import hu.webuni.hr.domi.service.StartWorkDateNonPastException;

@RestControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler(StartWorkDateNonPastException.class)
	public ResponseEntity<MyError> handleStartWorkDateNonPast(StartWorkDateNonPastException e, WebRequest req){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new MyError(e.getMessage(), 1002));
	}
	
	@ExceptionHandler(PayNotNegativeException.class)
	public ResponseEntity<MyError> handlePayNotNegative(PayNotNegativeException e, WebRequest req){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new MyError(e.getMessage(), 1003));
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyError> handleMethodArgumentNotValid(MethodArgumentNotValidException e, WebRequest req){
		MyError myError = new MyError(e.getMessage(), 1004);
		myError.setFieldErrors(e.getBindingResult().getFieldErrors());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(myError);
	}
}
