package com.se.sat.app.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.se.sat.app.dao.AbstractDao;
import com.se.sat.app.dao.StudySessionDao;
import com.se.sat.app.entity.Course;
import com.se.sat.app.entity.StudySession;
import com.se.sat.app.entity.User;

@Repository("StudySessionDao")
@Transactional
public class StusySessionDaoImpl extends AbstractDao<Integer, StudySession> implements StudySessionDao {

	@Override
	public void insertStudySession(StudySession studySession) {
		persist(studySession);
	}

	@Override
	public void updateStudySession(StudySession studySession) {
		update(studySession);
	}

	@Override
	public void deleteStudySessionById(Integer id) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("id", id));
		StudySession studySession = (StudySession) criteria.uniqueResult();

		if (studySession != null) {

			delete(studySession);
		}
	}

	@Override
	public StudySession findById(int id) {
		StudySession studySession = getByKey(id);
		return studySession;
	}

	@Override
	public List<StudySession> findStudySessionByCourse(Course course) {
		Criteria criteria = createEntityCriteria();
		criteria = criteria.add(Restrictions.eq("course", course));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		List<StudySession> studySessions = (List<StudySession>) criteria.list();

		return studySessions;
	}

}
