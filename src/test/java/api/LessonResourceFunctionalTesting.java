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
			Court court = courtDao.findById(1);
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
		
	}
	/*
	
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
