package data.daos;

import java.util.Calendar;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import data.entities.PadelLesson;

public interface PadelLessonDao extends JpaRepository<PadelLesson, Integer> {
	
	@Query("select padelLesson from PadelLesson padelLesson where padelLesson.timeTable = ?1")
	PadelLesson findByTimeTable(Calendar timeTable);

}
