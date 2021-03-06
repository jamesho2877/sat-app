package com.se.sat.app.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.se.sat.app.dao.AbstractDao;
import com.se.sat.app.dao.CourseDao;
import com.se.sat.app.entity.Course;
import com.se.sat.app.entity.Student;
import com.se.sat.app.entity.Teacher;

@Repository("courseDao")
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
	public void deleteCourseById(int id) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("id", id));
		Course course = (Course) crit.uniqueResult();
		if(course!=null){
			delete(course);
		}
		
	}

	@Override
	public Course findCourseById(int id) {
		Course course = getByKey(id);
		if (course != null) {
			Hibernate.initialize(course.getTeacher());
			Hibernate.initialize(course.getStudySessions());
			Hibernate.initialize(course.getStudents());
		}
		return course;
	}

	@Override
	public List<Course> findCoursesByTeacher(Teacher teacher) {
		Criteria criteria = createEntityCriteria();
		criteria = criteria.add(Restrictions.eq("teacher", teacher));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		List<Course> courses = (List<Course>) criteria.list();
		return courses;
	}

	@Override
	public List<Course> findCoursesByStudent(Student student) {
		String hql = "select c FROM Student s join s.courses c " + "WHERE s.id = :studentId";
		Query query = getSession().createQuery(hql);
		query.setInteger("studentId", student.getId());

		List<Course> courses = (List<Course>) query.list();
		return courses;
	}

	@Override
	public List<Course> findAllCourses() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("name"));
		// To avoid duplicates.
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY); 
		List<Course> courses = (List<Course>) criteria.list();
		return courses;
	}

	@Override
	public List<Object[]> getGroupsOfCoursesByTeachers(Student student) {
		String hql = "SELECT T, C FROM Course C JOIN C.teacher T "
					+ "WHERE C.id NOT IN (SELECT CS.id FROM Student ST JOIN ST.courses CS WHERE ST.id = :studentId) "
					+ "AND C.startEnrollDate <= current_date AND C.endEnrollDate >= current_date "
					+ "ORDER BY T.department, T.firstname";
		Query query = getSession().createQuery(hql);
		query.setInteger("studentId", student.getId());

		List<Object[]> items = (List<Object[]>) query.list();
		return items;
	}

}
