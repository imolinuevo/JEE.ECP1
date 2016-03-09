package data.entities;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class PadelLesson {

	@Id
    @GeneratedValue
    private int id;
	
	@ManyToOne
    @JoinColumn
    private User trainer;
	
	@OneToMany(mappedBy = "padelLesson", fetch = FetchType.EAGER)
    private List<User> students;
	
	@ManyToOne
    @JoinColumn
    private Court court;
	
	private Calendar timeTable;
	
	private Calendar beginDate;
	
	private Calendar endDate;
	
	public PadelLesson() {
		
	}
	
	public PadelLesson(User trainer, Court court, Calendar timeTable, Calendar beginDate, Calendar endDate) {
		this.trainer = trainer;
		this.court = court;
		this.timeTable = timeTable;
		this.beginDate = beginDate;
		this.endDate = endDate;
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
		return "PadelLesson [id=" + id + ", trainer=" + trainer + ", students="
				+ students + ", court=" + court + ", timeTable=" + timeTable
				+ ", beginDate=" + beginDate + ", endDate=" + endDate + "]";
	}
}
