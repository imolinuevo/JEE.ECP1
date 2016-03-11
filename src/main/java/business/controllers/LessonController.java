package business.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import business.wrapper.LessonWrapper;
import data.daos.CourtDao;
import data.daos.LessonDao;
import data.daos.UserDao;
import data.entities.Lesson;
import data.entities.User;

@Controller
public class LessonController {

	private LessonDao lessonDao;

	private CourtDao courtDao;

	private UserDao userDao;

	@Autowired
	public void setLessonDao(LessonDao lessonDao) {
		this.lessonDao = lessonDao;
	}

	@Autowired
	public void setCourtDao(CourtDao courtDao) {
		this.courtDao = courtDao;
	}

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public boolean createLesson(LessonWrapper lessonWrapper) {
		if (!courtDao.exists(lessonWrapper.getCourt().getId())
				|| !userDao.exists(lessonWrapper.getTrainer().getId())
				|| lessonDao.exists(lessonWrapper.getId())) {
			return false;
		} else {
			lessonDao.save(new Lesson(lessonWrapper.getTrainer(), lessonWrapper
					.getCourt(), lessonWrapper.getTimeTable(), lessonWrapper
					.getBeginDate(), lessonWrapper.getEndDate()));
			return true;
		}
	}

	public List<LessonWrapper> showLessons() {
		List<LessonWrapper> lessonWrapperList = new ArrayList<>();
		for(Lesson lesson : lessonDao.findAll()) {
			lessonWrapperList.add(new LessonWrapper(lesson));
		}
		return null;
	}

	public boolean deleteLesson(int id) {
		Lesson lesson = lessonDao.findById(id);
		if(lesson == null) {
			return false;
		} else {
			lessonDao.delete(lesson);
			return true;
		}
	}

	public boolean joinLesson(int lessonId, String studentUsername) {
		Lesson lesson = lessonDao.findById(lessonId);
		User student = userDao.findByUsernameOrEmail(studentUsername);
		if(lesson != null && student != null) {
			if(lesson.getStudents() != null) {
				if(lesson.getStudents().size() < 4) {
					if(!lesson.hasStudent(student.getId())) {
						lesson.addStudent(student);
						lessonDao.save(lesson);
						return true;
					} else {
						return false;
					}
				} else {
					return false;
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public boolean deleteStudent(int lessonId, String studentUsername) {
		Lesson lesson = lessonDao.findById(lessonId);
		User student = userDao.findByUsernameOrEmail(studentUsername);
		if(lesson != null && student != null) {
			if(lesson.getStudents() != null) {
				if(lesson.hasStudent(student.getId())) {
					lesson.removeStudent(student);
					lessonDao.save(lesson);
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

}
