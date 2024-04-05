package com.project.note.app.exceptions;

public class EmptyResultException extends Exception {

	public EmptyResultException() {
		super();
	}
	
	public EmptyResultException(String message) {
		super(message);
	}
	
	public EmptyResultException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public EmptyResultException(Throwable cause) {
		super(cause);
	}
	
}
