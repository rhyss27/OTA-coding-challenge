package com.project.note.app.helper;

import org.springframework.stereotype.Component;

@Component
public class NoteFieldStringHelper {

	public boolean isFieldEmpty(String stringField) {
		return stringField == null || stringField.isBlank();
	}
	
	public boolean isFieldEmpty(Long longField) {
		return  longField == null || longField.toString().isBlank();
	}

}
