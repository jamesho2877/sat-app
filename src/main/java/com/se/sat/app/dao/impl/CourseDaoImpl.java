package com.se.sat.app.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.se.sat.app.dao.AbstractDao;
import com.se.sat.app.dao.CourseDao;
import com.se.sat.app.entity.Course;
import com.se.sat.app.entity.Teacher;

@Repository("CourseDao")
@Transactional
public class CourseDaoImpl extends AbstractDao<Integer, Course> implements CourseDao {

	private static final Logger log = LoggerFactory.getLogger(CourseDaoImpl.class);

	@Override
	public void insertCourse(Course course) {
		persist(course);
	}

	@Override
	public void updateCourse(Course course) {
		update(course);
	}

	@Override
	public void deleteCourse(Course course) {
		delete(course);
	}

	@Override
	public Course finById(int id) {
		Course course = getByKey(id);
		return course;
	}

	@Override
	public List<Course> findCourseByTeacher(Teacher teacher) {
		Criteria criteria = createEntityCriteria();
		criteria = criteria.add(Restrictions.eq("teacher", teacher));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		List<Course> courses = (List<Course>) criteria.list();
		return courses;
	}

}
