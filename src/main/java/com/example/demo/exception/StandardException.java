package com.example.demo.exception;

public class StandardException extends Exception {
	private static final long serialVersionUID = 1L;

	public StandardException() {
		super();
	}

	public StandardException(String message) {
		super(message);
	}

	public StandardException(String message, Throwable e) {
		super(message);
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}
}
