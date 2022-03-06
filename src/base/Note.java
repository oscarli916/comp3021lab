package base;

import java.util.Date;
import java.util.Objects;

public class Note implements Comparable<Note> {
	
	private Date date;
	private String title;
	
	public Note(String title) {
		this.title = title;
		date = new Date(System.currentTimeMillis());
	}
	
	public String getTitle() {
		return this.title;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		Note other = (Note) obj;
		return Objects.equals(title, other.title);
	}

	@Override
	public int compareTo(Note o) {
		// TODO Auto-generated method stub
		if (this.date.compareTo(o.date) == 0)
			// same date return 0
			return 0;
		else if (this.date.compareTo(o.date) < 0)
			// this date occurs before o date, so it is bigger, return 1
			return 1;
		else
			// this date occurs after o date, so it is smaller, return -1
			return -1;
	}
	
	public String toString() {
		return date.toString() + "\t" + title;
	}
	
}
