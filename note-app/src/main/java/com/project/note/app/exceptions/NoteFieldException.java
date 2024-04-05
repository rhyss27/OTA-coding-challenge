package com.project.note.app.exceptions;

public class NoteFieldException extends Exception {

	public NoteFieldException() {
		super();
	}
	
	public NoteFieldException(String message) {
		super(message);
	}
	
	public NoteFieldException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public NoteFieldException(Throwable cause) {
		super(cause);
	}
	
}
