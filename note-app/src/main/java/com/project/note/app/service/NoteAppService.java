package com.project.note.app.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.note.app.dao.NoteAppDAO;
import com.project.note.app.exceptions.EmptyResultException;
import com.project.note.app.exceptions.NoteFieldException;
import com.project.note.app.helper.NoteFieldStringHelper;
import com.project.note.app.model.Note;
import com.project.note.app.model.Notes;

@Service
public class NoteAppService {

	@Autowired
	NoteAppDAO noteAppDao;
	
	@Autowired 
	NoteFieldStringHelper helper;
	
	public List<Note> getNotes() {
		return noteAppDao.getNotes();
	}
	
	public Note getNoteById(Long id) throws EmptyResultException {
		List<Note> notesList = noteAppDao.getNotes();
		if(noteAppDao.getNotes() != null) {
			for(Note note : notesList) {
				if(note.getId() == id) {
					return note;
				}
			}
		} 
		
		throw new EmptyResultException("Note ID not found");
	}

	public void createNote(Note note) throws NoteFieldException {
		
		List<Note> noteList = noteAppDao.getNotes();
		noteList = (noteList != null) ? noteList : new ArrayList<Note>();
		note.setId(nextAvailableId(noteList));
		noteList.add(note);
		
		Notes currentNotes = new Notes();
		currentNotes.setNoteList(noteList);
		noteAppDao.writeNotes(currentNotes);
		
	}

	public void updateNoteById(Note note) throws EmptyResultException {

		boolean noteFound = false;
		List<Note> noteList = noteAppDao.getNotes();
		noteList = (noteList != null) ? noteList : new ArrayList<Note>();
		for(Note currentNote: noteList) {
			if(currentNote.getId() == note.getId()) {
				currentNote.setBody(note.getBody());
				currentNote.setTitle(note.getTitle());
				noteFound = true;
				break;
			}
		}
		
		if(noteFound) {
			noteList.sort(Comparator.comparing(Note::getId));
			
			Notes currentNotes = new Notes();
			currentNotes.setNoteList(noteList);
			noteAppDao.writeNotes(currentNotes);
		} else {
			throw new EmptyResultException("Note ID not found");
		}

		
	}

	public void deleteNoteById(Long id) throws EmptyResultException {
		
		boolean noteFound = false;
		
		List<Note> noteList = noteAppDao.getNotes();
		noteList = (noteList != null) ? noteList : new ArrayList<Note>();
		for(Iterator<Note> iterator = noteList.iterator(); iterator.hasNext();) {
			Note note = iterator.next();
            if (note.getId() == id) {
                iterator.remove();
                noteFound = true;
                break;
            }
		}
		
		if(noteFound) {
			noteList.sort(Comparator.comparing(Note::getId));
			
			Notes currentNotes = new Notes();
			currentNotes.setNoteList(noteList);
			noteAppDao.writeNotes(currentNotes);
		} else {
			throw new EmptyResultException("Note ID not found");
		}
		
	}

	
	public void validateNoteFields(Note note) throws NoteFieldException {
		if(!helper.isFieldEmpty(note.getId())) {
			throw new NoteFieldException("Note ID cannot be manually assigned.");
		} else if(helper.isFieldEmpty(note.getTitle())) {
			throw new NoteFieldException("Note Title cannot be blank.");			
		}
	}
	
	private Long nextAvailableId(List<Note> notes) {
		if(notes != null && notes.size() > 0) {
			List<Long> currentIds = new ArrayList<Long>();
			notes.forEach(note -> {
				currentIds.add(note.getId());
			});
			notes.sort(Comparator.comparing(Note::getId));
			return notes.get(notes.size() - 1).getId() + 1;
		} else {
			return (long) 1;
		}
	}
	
	
}
