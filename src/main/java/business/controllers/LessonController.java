package business.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import business.wrapper.LessonWrapper;
import data.daos.LessonDao;

@Controller
public class LessonController {
	
	private LessonDao lessonDao;
	
	@Autowired
	public void setLessonDao(LessonDao lessonDao) {
		this.lessonDao = lessonDao;
	}

	public boolean createLesson(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<LessonWrapper> showLessons() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean deleteLesson(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean joinLesson(int lessonId, int studentId) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean deleteStudent(int lessonId, int studentId) {
		// TODO Auto-generated method stub
		return false;
	}

}
