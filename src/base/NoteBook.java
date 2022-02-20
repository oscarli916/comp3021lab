package base;

import java.util.ArrayList;

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
	
}
