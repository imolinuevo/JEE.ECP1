package api;

import static org.junit.Assert.*;

import org.apache.logging.log4j.LogManager;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.HttpClientErrorException;

import config.PersistenceConfig;
import config.TestsPersistenceConfig;
import data.daos.CourtDao;
import data.daos.LessonDao;
import data.daos.UserDao;
import data.entities.Court;
import data.entities.User;
import data.services.LessonService;
import business.api.Uris;
import business.wrapper.LessonWrapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class})
public class LessonResourceFunctionalTesting {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private CourtDao courtDao;
	
	@Autowired
	private LessonDao lessonDao;
	
	private RestService restService = new RestService();
	
	private LessonService lessonService = new LessonService();
	
	@Test
	public void testCreateLesson() {
		assertEquals(0, lessonDao.count());
		User user = userDao.findByUsernameOrEmail("trainer");
		Court court = courtDao.findById(1);
		LessonWrapper lessonWrapper = lessonService.getExampleLessonWrapper(user, court);
		restService.createLesson(lessonWrapper);
		assertEquals(1, lessonDao.count());
	}

	@Test
	public void testCreateLessonUnauthorized() {
		try {
			User user = userDao.findByUsernameOrEmail("trainer");
			Court court = courtDao.findById(4);
			LessonWrapper lessonWrapper = lessonService.getExampleLessonWrapper(user, court);
			new RestBuilder<Object>(RestService.URL).path(Uris.LESSONS)
			.body(lessonWrapper)
			.basicAuth("", "").post().build();
			fail();
		} catch (HttpClientErrorException httpError) {
			assertEquals(HttpStatus.UNAUTHORIZED, httpError.getStatusCode());
            LogManager.getLogger(this.getClass()).info(
                    "testCreateLesson (" + httpError.getMessage() + "):\n    " + httpError.getResponseBodyAsString());
		}
	}
	
	@Test
	public void testDeleteLesson() {
		assertEquals(0, lessonDao.count());
		User user = userDao.findByUsernameOrEmail("trainer");
		courtDao.save(new Court(1));
		Court court = courtDao.findById(1);
		LessonWrapper lessonWrapper = lessonService.getExampleLessonWrapper(user, court);
		restService.createLesson(lessonWrapper);
		assertEquals(1, lessonDao.count());
		restService.deleteLesson(lessonDao.findFirstById().getId());
		assertEquals(0, lessonDao.count());
	}
	
	@Test
	public void testDeleteLessonUnauthorized() {
		User user = userDao.findByUsernameOrEmail("trainer");
		courtDao.save(new Court(1));
		Court court = courtDao.findById(1);
		LessonWrapper lessonWrapper = lessonService.getExampleLessonWrapper(user, court);
		restService.createLesson(lessonWrapper);
		try {
			new RestBuilder<Object>(RestService.URL).path(Uris.LESSONS)
			.param("id", new Integer(lessonDao.findFirstById().getId()).toString())
			.basicAuth("", "").delete().build();
			fail();
		} catch (HttpClientErrorException httpError) {
			assertEquals(HttpStatus.UNAUTHORIZED, httpError.getStatusCode());
            LogManager.getLogger(this.getClass()).info(
                    "testDeleteLesson (" + httpError.getMessage() + "):\n    " + httpError.getResponseBodyAsString());
		}
	}

	@Test
	public void testShowLessons() {
		assertEquals(0, lessonDao.count());
		final int LESSONS = 3;
		for(int i = 1; i <= LESSONS; i++) {
			User user = userDao.findByUsernameOrEmail("trainer");
			courtDao.save(new Court(i));
			Court court = courtDao.findById(i);
			LessonWrapper lessonWrapper = lessonService.getExampleLessonWrapper(user, court);
			restService.createLesson(lessonWrapper);
		}
		assertEquals(LESSONS, lessonDao.count());
	}
	
	@Test
	public void testJoinLesson() {
		User user = userDao.findByUsernameOrEmail("trainer");
		courtDao.save(new Court(1));
		Court court = courtDao.findById(1);
		LessonWrapper lessonWrapper = lessonService.getExampleLessonWrapper(user, court);
		restService.createLesson(lessonWrapper);
		int lessonId = lessonDao.findFirstById().getId();
		User student = new User("inigo", "inigo@gmail.com", "pass", null);
		userDao.save(student);
		assertNotNull(lessonId);
		assertNotNull(student.getUsername());
		restService.addStudent(lessonId, student.getUsername());
		assertTrue(lessonDao.findFirstById().hasStudent(student.getId()));
	}
	
	
	
	@Test
	public void testJoinLessonUnauthorized() {
		User user = userDao.findByUsernameOrEmail("trainer");
		courtDao.save(new Court(1));
		Court court = courtDao.findById(1);
		LessonWrapper lessonWrapper = lessonService.getExampleLessonWrapper(user, court);
		restService.createLesson(lessonWrapper);
		int lessonId = lessonDao.findFirstById().getId();
		User student = new User("inigo", "inigo@gmail.com", "pass", null);
		userDao.save(student);
		try {
			new RestBuilder<Object>(RestService.URL).path(Uris.LESSONS).pathId(lessonId)
			.path(Uris.STUDENTS).body(student.getUsername())
			.basicAuth("", "").post().build();
		} catch(HttpClientErrorException httpError) {
			assertEquals(HttpStatus.UNAUTHORIZED, httpError.getStatusCode());
            LogManager.getLogger(this.getClass()).info(
                    "testJoinLesson (" + httpError.getMessage() + "):\n    " + httpError.getResponseBodyAsString());
		}
	}
	
	@Test
	public void testDeleteStudent() {
		User user = userDao.findByUsernameOrEmail("trainer");
		courtDao.save(new Court(1));
		Court court = courtDao.findById(1);
		LessonWrapper lessonWrapper = lessonService.getExampleLessonWrapper(user, court);
		restService.createLesson(lessonWrapper);
		int lessonId = lessonDao.findFirstById().getId();
		User student = new User("inigo", "inigo@gmail.com", "pass", null);
		userDao.save(student);
		assertNotNull(lessonId);
		assertNotNull(student.getUsername());
		restService.addStudent(lessonId, student.getUsername());
		assertTrue(lessonDao.findFirstById().hasStudent(student.getId()));
		restService.deleteStudent(lessonId, student.getUsername());
		assertFalse(lessonDao.findFirstById().hasStudent(student.getId()));
	}
	
	@Test
	public void testDeleteStudentUnauthorized() {
		User user = userDao.findByUsernameOrEmail("trainer");
		courtDao.save(new Court(1));
		Court court = courtDao.findById(1);
		LessonWrapper lessonWrapper = lessonService.getExampleLessonWrapper(user, court);
		restService.createLesson(lessonWrapper);
		int lessonId = lessonDao.findFirstById().getId();
		User student = new User("inigo", "inigo@gmail.com", "pass", null);
		userDao.save(student);
		restService.addStudent(lessonId, student.getUsername());
		try {
			new RestBuilder<Object>(RestService.URL).path(Uris.LESSONS).pathId(lessonId)
			.path(Uris.STUDENTS).body(student.getUsername())
			.basicAuth("", "").delete().build();
		} catch (HttpClientErrorException httpError) {
			assertEquals(HttpStatus.UNAUTHORIZED, httpError.getStatusCode());
            LogManager.getLogger(this.getClass()).info(
                    "testJoinLesson (" + httpError.getMessage() + "):\n    " + httpError.getResponseBodyAsString());
		}
	}
	
	@After
    public void deleteAll() {
        new RestService().deleteAll();
    }
}
