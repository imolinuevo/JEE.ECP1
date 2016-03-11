package business.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public void createLesson(@RequestParam(required = true) int id) throws AlreadyExistLessonIdException {
        if (!this.lessonController.createLesson(id)) {
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
	public void joinLesson(@PathVariable int lessonId, int studentId) throws JoinLessonException {
		if(!lessonController.joinLesson(lessonId, studentId)) {
			throw new JoinLessonException();
		}
	}
	
	@RequestMapping(value = Uris.ID + Uris.STUDENTS + Uris.ID, method = RequestMethod.DELETE)
	public void deleteStudent(@PathVariable int lessonId, int studentId)  throws DeleteStudentException {
		if(!lessonController.deleteStudent(lessonId, studentId)) {
			throw new DeleteStudentException();
		}
	}
}
