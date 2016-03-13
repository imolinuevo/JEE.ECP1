package data.services;

import java.util.Calendar;

import org.springframework.stereotype.Service;

import business.wrapper.LessonWrapper;
import data.entities.Court;
import data.entities.User;

@Service
public class LessonService {

	public LessonWrapper getExampleLessonWrapper(User user, Court court) {
		Calendar timeTable = Calendar.getInstance();
		timeTable.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		timeTable.set(Calendar.HOUR, 18);
		Calendar beginDate = Calendar.getInstance();
		beginDate.set(Calendar.MONTH, Calendar.APRIL);
		beginDate.set(Calendar.DAY_OF_MONTH, 12);
		Calendar endDate = Calendar.getInstance();
		endDate.set(Calendar.MONTH, Calendar.JUNE);
		endDate.set(Calendar.DAY_OF_MONTH, 22);
		return new LessonWrapper(user, court, timeTable, beginDate, endDate);
	}
}
