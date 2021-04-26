package com.example.demo.exception;

public class TeacherException extends Exception {
	private static final long serialVersionUID = 1L;

	public TeacherException() {
		super();
	}

	public TeacherException(String message) {
		super(message);
	}

	public TeacherException(String message, Throwable e) {
		super(message);
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}
}
