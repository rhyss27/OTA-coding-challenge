package com.project.note.app.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.note.app.exceptions.NoteFieldException;
import com.project.note.app.model.Note;
import com.project.note.app.service.NoteAppService;

@SpringBootTest
public class NoteAppServiceTest {

	@Autowired
	NoteAppService service;
	
	@Test
	public void testValidateNoteFields() {
		Note note = new Note();
		note.setId((long) 1);
		note.setTitle(" ");
		note.setBody("Test Body");
		
		assertThrows(NoteFieldException.class, () -> service.validateNoteFields(note));
		assertThrows(NoteFieldException.class, () -> service.validateNoteFields(note));
	}
	
}
