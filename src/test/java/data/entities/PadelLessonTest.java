package data.entities;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PadelLessonTest {
	
	private PadelLesson padelLesson;
	
	@Before
	public void before() {
		padelLesson = new PadelLesson();
	}

	@Test
	public void testAddStudentRemoveStudent() {
		User user = new User();
		assertEquals(0, padelLesson.getStudents().size());
		padelLesson.addStudent(user);
		assertEquals(1, padelLesson.getStudents().size());
		padelLesson.removeStudent(user);
		assertEquals(0, padelLesson.getStudents().size());
	}

}
