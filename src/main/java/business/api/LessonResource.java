package business.api;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import data.entities.Court;
import data.entities.User;
import business.api.exceptions.AlreadyExistLessonIdException;
import business.api.exceptions.DeleteStudentException;
import business.api.exceptions.JoinLessonException;
import business.api.exceptions.NotFoundLessonIdException;
import business.controllers.LessonController;
import business.wrapper.LessonWrapper;

@RestController
@RequestMapping(Uris.SERVLET_MAP + Uris.LESSONS)
public class LessonResource {

	private LessonController lessonController;
	
	@Autowired
	public void setLessonController(LessonController lessonController) {
		this.lessonController = lessonController;
	}
	
	@RequestMapping(method = RequestMethod.POST)
    public void createLesson(@RequestParam(required = true) User trainer, Court court, Calendar timeTable,
			Calendar beginDate, Calendar endDate) throws AlreadyExistLessonIdException {
        if (!this.lessonController.createLesson(new LessonWrapper(trainer, court, timeTable, beginDate, endDate))) {
            throw new AlreadyExistLessonIdException();
        }
    }
	
	@RequestMapping(method = RequestMethod.DELETE)
	public void deleteLesson(@RequestParam(required = true) int id) throws NotFoundLessonIdException {
		if (!lessonController.deleteLesson(id)) {
            throw new NotFoundLessonIdException("id: " + id);
        }
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public List<LessonWrapper> showLessons() {
		return lessonController.showLessons();
	}
	
	@RequestMapping(value = Uris.ID + Uris.STUDENTS + Uris.ID, method = RequestMethod.POST)
	public void joinLesson(@PathVariable int lessonId, String studentUsername) throws JoinLessonException {
		if(!lessonController.joinLesson(lessonId, studentUsername)) {
			throw new JoinLessonException();
		}
	}
	
	@RequestMapping(value = Uris.ID + Uris.STUDENTS + Uris.ID, method = RequestMethod.DELETE)
	public void deleteStudent(@PathVariable int lessonId, String studentUsername)  throws DeleteStudentException {
		if(!lessonController.deleteStudent(lessonId, studentUsername)) {
			throw new DeleteStudentException();
		}
	}
}
