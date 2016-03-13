package api;

import java.util.Calendar;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PersistenceConfig;
import config.TestsPersistenceConfig;
import data.daos.CourtDao;
import data.daos.UserDao;
import data.entities.Court;
import data.entities.User;
import business.wrapper.LessonWrapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class})
public class LessonResourceFunctionalTesting {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private CourtDao courtDao;
	
	RestService restService = new RestService();
	
	@Test
	public void testCreateLesson() {
		Calendar timeTable = Calendar.getInstance();
		timeTable.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		timeTable.set(Calendar.HOUR, 18);
		Calendar beginDate = Calendar.getInstance();
		beginDate.set(Calendar.MONTH, Calendar.APRIL);
		beginDate.set(Calendar.DAY_OF_MONTH, 12);
		Calendar endDate = Calendar.getInstance();
		endDate.set(Calendar.MONTH, Calendar.JUNE);
		endDate.set(Calendar.DAY_OF_MONTH, 22);
		User user = userDao.findByUsernameOrEmail("trainer");
		Court court = courtDao.findById(1);
		LessonWrapper lessonWrapper = new LessonWrapper(user, court, timeTable, beginDate, endDate);
		restService.createLesson(lessonWrapper);
	}
	/*
	
	@Test
	public void testCreateLessonUnauthorized() {
		
	}
	
	@Test
	public void testDeleteLesson() {
		
	}
	
	@Test
	public void testDeleteLessonUnauthorized() {
		
	}
	
	@Test
	public void testShowLessons() {
		
	}
	
	@Test
	public void testJoinLesson() {
		
	}
	
	@Test
	public void testJoinLessonUnauthorized() {
		
	}
	
	@Test
	public void testJoinFullLesson() {
		
	}
	
	@Test
	public void testDeleteStudent() {
		
	}
	
	@Test
	public void testDeleteStudentUnauthorized() {
		
	}
	*/
	
	@After
    public void deleteAll() {
        new RestService().deleteAll();
    }
}
