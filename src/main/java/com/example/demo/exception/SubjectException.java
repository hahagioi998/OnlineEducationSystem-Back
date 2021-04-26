package com.example.demo.exception;

public class SubjectException extends Exception {
	private static final long serialVersionUID = 1L;

	public SubjectException() {
		super();
	}

	public SubjectException(String message) {
		super(message);
	}

	public SubjectException(String message, Throwable e) {
		super(message);
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}
}
