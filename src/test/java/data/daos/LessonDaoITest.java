package data.daos;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PersistenceConfig;
import config.TestsPersistenceConfig;
import data.entities.Lesson;
import data.entities.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class})
public class LessonDaoITest {

	@Autowired
	private DaosService daosService;
	
	@Autowired
	private CourtDao courtDao;
	
	@Autowired
	private LessonDao lessonDao;
	
	@Test
	public void testFindByTimeTable() {
		Calendar date1 = Calendar.getInstance();
        date1.add(Calendar.DAY_OF_YEAR, 1);
        date1.set(Calendar.HOUR_OF_DAY, 0);
        Calendar date2 = (Calendar) date1.clone();
        date2.add(Calendar.HOUR_OF_DAY, 23);
        date2.add(Calendar.MINUTE, 59);
        Calendar date3 = (Calendar) date1.clone();
        date3.add(Calendar.HOUR_OF_DAY, 13);
        date3.add(Calendar.MINUTE, 49);
		Lesson padelLesson = new Lesson((User) daosService.getMap().get("u1"), courtDao.findOne(2), date1, date2, date3);
		lessonDao.save(padelLesson);
		assertNotNull(lessonDao.findByTimeTable(padelLesson.getTimeTable()));
	}
	
	@Test
	public void testFindById() {
		Lesson lesson = new Lesson();
		lessonDao.save(lesson);
		assertEquals(lesson.getId(), lessonDao.findById(lesson.getId()).getId());
	}
	
	@Test
	public void testFindFirst() {
		assertNotNull(lessonDao.findFirstById());
		System.out.println(lessonDao.findFirstById().getId());
	}

}
