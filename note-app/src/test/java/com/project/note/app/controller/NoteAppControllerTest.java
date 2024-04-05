package com.project.note.app.controller;

import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.hasSize;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.project.note.app.model.Note;
import com.project.note.app.service.NoteAppService;

@WebMvcTest(NoteAppController.class)
public class NoteAppControllerTest {

	@Autowired
	MockMvc mvc;
	
	@MockBean
    private NoteAppService service;
	
	private List<Note> testNoteList = new ArrayList<Note>();
	private Note testNote = new Note();
	
	@BeforeEach
	private void initializeTestData() {
		testNote.setId((long)1);
		testNote.setTitle("TestTitle");
		testNote.setBody("TestBody");
		testNoteList.add(testNote);
	}
	
	@Test
	public void testGetNotes() throws Exception {
		when(service.getNotes()).thenReturn(testNoteList);
		mvc.perform(MockMvcRequestBuilders.get("/api/notes")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testGetNote() throws Exception {
		when(service.getNoteById((long)1)).thenReturn(testNote);
		mvc.perform(MockMvcRequestBuilders.get("/api/notes/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
	}
	
}
