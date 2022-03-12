package base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Folder implements Comparable<Folder>, Serializable {
	
	private static final long serialVersionUID = 1L;
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

	@Override
	public int compareTo(Folder o) {
		// TODO Auto-generated method stub
		if (this.name.compareTo(o.name) == 0)
			// this name is equal to o name return 0
			return 0;
		else if (this.name.compareTo(o.name) < 1)
			// this name is smaller than o name return -1
			return -1;
		else
			// this name is greater than o name return 1
			return 1;
	}
	
	public void sortNotes() {
		Collections.sort(this.notes);
	}
	
	public List<Note> searchNotes(String keywords){
		ArrayList<Note> result = new ArrayList<Note>();
		String[] tempKeywords = keywords.split(" ");
	
		ArrayList<ArrayList<String>> searchKeywords = new ArrayList<ArrayList<String>>();
		for (int i=0; i<tempKeywords.length; i++) {
			if (tempKeywords[i].toLowerCase().equals("or")) {
				searchKeywords.get(searchKeywords.size() - 1).add(tempKeywords[i + 1]);
				i = i + 1;
			}else {
				ArrayList<String> searchKeyword = new ArrayList<String>();
				searchKeyword.add(tempKeywords[i]);
				searchKeywords.add(searchKeyword);
			}
		}
		
		
		for (int i=0; i<this.notes.size(); i++) {
			
			Note note = this.notes.get(i);
			if (note instanceof TextNote) {
				// If note is text node
				
				// Check contents contains keyword
				int check = 0;
				for (int j=0; j<searchKeywords.size(); j++) {
					int pass = 0;
					for (int k=0; k<searchKeywords.get(j).size(); k++) {
						if (((TextNote) note).getContent().toLowerCase().contains(searchKeywords.get(j).get(k).toLowerCase())) {
							pass = 1;
							break;
						}
					}
					if (pass == 0) {
						check = 0;
						break;
					}else {
						check = 1;
					}
				}
				if (check == 1) {
					result.add(note);
					continue;
				} else {
					// Check title contains keyword
					for (int j=0; j<searchKeywords.size(); j++) {
						int pass = 0;
						for (int k=0; k<searchKeywords.get(j).size(); k++) {
							if (note.getTitle().toLowerCase().contains(searchKeywords.get(j).get(k).toLowerCase())) {
								pass = 1;
								break;
							}
						}
						if (pass == 0) {
							check = 0;
							break;
						}else {
							check = 1;
						}
					}
					if (check == 1)
						result.add(note);
				}
			} else {
				// if node is image note
				int check = 0;
				for (int j=0; j<searchKeywords.size(); j++) {
					int pass = 0;
					for (int k=0; k<searchKeywords.get(j).size(); k++) {
						
						if (note.getTitle().toLowerCase().contains(searchKeywords.get(j).get(k).toLowerCase())) {
							pass = 1;
							break;
						}
						
					}
					if (pass == 0) {
						check = 0;
						break;
					} else{
						check = 1;
					}
				}
				if (check == 1)
					result.add(note);
			}
		}
		
		return result;
	}
	
}
