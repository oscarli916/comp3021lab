package base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NoteBook {

	private ArrayList<Folder> folders;
	
	public NoteBook() {
		folders = new ArrayList<Folder>();
	}
	
	public boolean createImageNote(String folderName, String title) {
		ImageNote note = new ImageNote(title);
		return insertNote(folderName, note);
	}
	
	public boolean createTextNote(String folderName, String title) {
		TextNote note = new TextNote(title);
		return insertNote(folderName, note);
	}
	
	public boolean createTextNote(String folderName, String title, String content) {
		TextNote note = new TextNote(title, content);
		return insertNote(folderName, note);
	}
	
	public ArrayList<Folder> getFolders(){
		return this.folders;
	}
	
	public boolean insertNote(String folderName, Note note) {
		Folder f = null;
		for (Folder f1: this.folders) {
			if (f1.getName() == folderName) {
				f = f1;
			}
		}
		
		if (f == null) {
			f = new Folder(folderName);
			this.folders.add(f);
		}
		
		for (Note n: f.getNotes()) {
			if (n.equals(note)) {
				System.out.println("Creating note " + note.getTitle() + " under folder " + folderName + " failed");
				return false;
			}
		}
		f.getNotes().add(note);
		return true;
	}
	
	public void sortFolders() {
		for (int i=0; i<this.folders.size(); i++) {
			this.folders.get(i).sortNotes();
		}
		Collections.sort(this.folders);
	}
	
	public List<Note> searchNotes(String keywords){
		ArrayList<Note> result = new ArrayList<Note>();
		for (int i=0; i<this.folders.size(); i++) {
			List<Note> notes = this.folders.get(i).searchNotes(keywords);
			for (int j=0; j<notes.size(); j++) {
				result.add(notes.get(j));
			}
		}
		return result;
	}
	
}
