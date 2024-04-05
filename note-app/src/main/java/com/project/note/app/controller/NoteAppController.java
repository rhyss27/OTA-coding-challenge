package com.project.note.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.note.app.exceptions.EmptyResultException;
import com.project.note.app.exceptions.NoteFieldException;
import com.project.note.app.model.Note;
import com.project.note.app.service.NoteAppService;

@RestController
@RequestMapping("/api/notes")
public class NoteAppController {

	@Autowired
	NoteAppService noteAppService;
	
	@GetMapping
	public ResponseEntity<List<Note>> getNotes() {
		return ResponseEntity.ok().body(noteAppService.getNotes());
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Note> getNote(@PathVariable Long id) {
		try {
			return ResponseEntity.ok().body(noteAppService.getNoteById(id));

		} catch (EmptyResultException ere) {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<String> createNote(@RequestBody Note note) throws NoteFieldException {
		try {
			noteAppService.validateNoteFields(note);
			noteAppService.createNote(note);
			return ResponseEntity.ok("Note Created");

		} catch (NoteFieldException nfe) {
			return ResponseEntity.badRequest().body(nfe.getMessage());
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<String> updateNote(@PathVariable Long id, @RequestBody Note note){
		try {
			noteAppService.validateNoteFields(note);
			note.setId(id);
			noteAppService.updateNoteById(note);
			return ResponseEntity.ok("Update Successful");

		} catch (EmptyResultException ere) {
			return ResponseEntity.notFound().build();
		} catch (NoteFieldException nfe) {
			return ResponseEntity.badRequest().body(nfe.getMessage());
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteNote(@PathVariable Long id) {
		try {
			noteAppService.deleteNoteById(id);
			return ResponseEntity.ok("Delete Successful");

		} catch (EmptyResultException ere) {
			return ResponseEntity.notFound().build();
		}
	}

}
