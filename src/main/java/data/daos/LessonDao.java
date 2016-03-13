package data.daos;

import java.util.Calendar;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import data.entities.Lesson;

public interface LessonDao extends JpaRepository<Lesson, Integer> {
	
	@Query("select lesson from Lesson lesson where lesson.timeTable = ?1")
	Lesson findByTimeTable(Calendar timeTable);

	Lesson findById(int id);
	
	@Query(value = "SELECT * FROM lesson LIMIT 1", nativeQuery = true)
	Lesson findFirstById();
}
