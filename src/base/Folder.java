package base;

import java.util.ArrayList;
import java.util.Objects;

public class Folder {
	
	private ArrayList<Note> notes;
	private String name;
	
	public Folder(String name) {
		this.name = name;
		notes = new ArrayList<Note>();
	}

	public void addNote(Note note) {
		this.notes.add(note);
	}
	
	public String getName() {
		return this.name;
	}
	
	public ArrayList<Note> getNotes(){
		return this.notes;
	}
	
	@Override
	public String toString() {
		int nText = 0;
		int nImage = 0;
		
		for (Note n: this.notes) {
			if (n instanceof TextNote) {
				nText += 1;
			} else if (n instanceof ImageNote) {
				nImage += 1;
			}
		}
		
		return name + ":" + nText + ":" + nImage;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		Folder other = (Folder) obj;
		return Objects.equals(name, other.name);
	}
	
	
}
