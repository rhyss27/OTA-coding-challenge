package com.project.note.app.dao;

import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.springframework.stereotype.Repository;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import com.project.note.app.model.Note;
import com.project.note.app.model.Notes;

@Repository
public class NoteAppDAO {

	private static final String FILE_STORAGE_NAME = "notes.xml";
	private static final String FILE_STORAGE_PATH = "src/main/resources/";
	
	public List<Note> getNotes() {
		
	    JAXBContext context;
	    Notes notes = new Notes();
	    
		try {
			context = JAXBContext.newInstance(Notes.class);
			notes = (Notes) context.createUnmarshaller()
					  .unmarshal(NoteAppDAO.class.getClassLoader().getResourceAsStream(FILE_STORAGE_NAME));
			
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
		return notes.getNoteList();
	}

	public void writeNotes(Notes currentNotes) {

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Notes.class);
	        Marshaller marshaller = jaxbContext.createMarshaller();
	        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	        StringWriter writer = new StringWriter();
	        marshaller.marshal(currentNotes, writer);
		
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.transform(new DOMSource(parseXMLString(writer.toString())), new StreamResult(new File(FILE_STORAGE_PATH + FILE_STORAGE_NAME)));
	        
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
    private static Document parseXMLString(String xmlString) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.parse(new InputSource(new StringReader(xmlString)));
    }

}
