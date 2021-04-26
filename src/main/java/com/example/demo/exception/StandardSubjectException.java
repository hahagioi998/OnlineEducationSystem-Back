package com.example.demo.exception;

public class StandardSubjectException extends Exception {
	private static final long serialVersionUID = 1L;

	public StandardSubjectException() {
		super();
	}

	public StandardSubjectException(String message) {
		super(message);
	}

	public StandardSubjectException(String message, Throwable e) {
		super(message);
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}
}
