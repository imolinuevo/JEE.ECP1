package business.wrapper;

import java.util.Calendar;
import java.util.List;

import data.entities.Court;
import data.entities.Lesson;
import data.entities.User;

public class LessonWrapper {

    private int id;
	
    private User trainer;
	
    private List<User> students;
	
    private Court court;
	
	private Calendar timeTable;
	
	private Calendar beginDate;
	
	private Calendar endDate;
	
	public LessonWrapper() {
		
	}

	public LessonWrapper(User trainer, Court court, Calendar timeTable,
			Calendar beginDate, Calendar endDate) {
		super();
		this.trainer = trainer;
		this.court = court;
		this.timeTable = timeTable;
		this.beginDate = beginDate;
		this.endDate = endDate;
	}
	
	public LessonWrapper(Lesson lesson) {
		super();
		this.trainer = lesson.getTrainer();
		this.court = lesson.getCourt();
		this.timeTable = lesson.getTimeTable();
		this.beginDate = lesson.getBeginDate();
		this.endDate = lesson.getEndDate();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getTrainer() {
		return trainer;
	}

	public void setTrainer(User trainer) {
		this.trainer = trainer;
	}

	public List<User> getStudents() {
		return students;
	}

	public void setStudents(List<User> students) {
		this.students = students;
	}

	public Court getCourt() {
		return court;
	}

	public void setCourt(Court court) {
		this.court = court;
	}

	public Calendar getTimeTable() {
		return timeTable;
	}

	public void setTimeTable(Calendar timeTable) {
		this.timeTable = timeTable;
	}

	public Calendar getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Calendar beginDate) {
		this.beginDate = beginDate;
	}

	public Calendar getEndDate() {
		return endDate;
	}

	public void setEndDate(Calendar endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "LessonWrapper [id=" + id + ", trainer=" + trainer
				+ ", students=" + students + ", court=" + court
				+ ", timeTable=" + timeTable + ", beginDate=" + beginDate
				+ ", endDate=" + endDate + "]";
	}
}
