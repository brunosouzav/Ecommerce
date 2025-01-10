package com.ecommerce.fit.exceptions;

public class OrderProblemException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public OrderProblemException (String message) {
		super(message);
	}
}
