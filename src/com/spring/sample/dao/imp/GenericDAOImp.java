package com.spring.sample.dao.imp;

import com.spring.sample.dao.GenericDAO;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.criterion.*;
import org.hibernate.query.Query;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;

/**
 * @author ducda referenced from CaveatEmptor project tm JBoss Hibernate version
 */
public abstract class GenericDAOImp<E, Id extends Serializable> extends HibernateDaoSupport
		implements GenericDAO<E, Id> {

	private Class<E> persistentClass;

	public GenericDAOImp(Class<E> persistentClass) {
		this.persistentClass = persistentClass;
	}

	public Class<E> getPersistentClass() {
		return persistentClass;
	}

	@SuppressWarnings("unchecked")
	public E find(Id id) {
		DetachedCriteria criteria = DetachedCriteria.forClass(getPersistentClass());
		criteria.add(Restrictions.eq("id", id));
		return (E) DataAccessUtils.uniqueResult(getHibernateTemplate().findByCriteria(criteria));
	}

	public E find(Id id, boolean lock) throws Exception {
		if (lock) {
			return getHibernateTemplate().load(getPersistentClass(), id, LockMode.PESSIMISTIC_WRITE);
		} else {
			return getHibernateTemplate().get(getPersistentClass(), id);
		}

	}

	public List<E> findAll() {
		return findByCriteria();
	}

	public List<E> findByExample(E exampleInstance) {
		return getHibernateTemplate().findByExample(exampleInstance);
	}

	@SuppressWarnings("unchecked")
	public List<E> findByExample(E exampleInstance, String[] excludeProperty) {
		DetachedCriteria criteria = DetachedCriteria.forClass(getPersistentClass());
		Example example = Example.create(exampleInstance);
		for (String exclude : excludeProperty) {
			example.excludeProperty(exclude);
		}
		criteria.add(example);
		return (List<E>) getHibernateTemplate().findByCriteria(criteria);
	}

	public int count(E exampleInstance, String[] excludeProperty, boolean isLike) {
		DetachedCriteria criteria = DetachedCriteria.forClass(getPersistentClass());
		Example example = Example.create(exampleInstance);
		for (String exclude : excludeProperty) {
			example.excludeProperty(exclude);
		}
		if (isLike) {
			example.enableLike(MatchMode.ANYWHERE).ignoreCase();
		}
		return DataAccessUtils.intResult(
				getHibernateTemplate().findByCriteria(criteria.add(example).setProjection(Projections.rowCount())));
	}

	public int count() {
		DetachedCriteria criteria = DetachedCriteria.forClass(getPersistentClass());
		return DataAccessUtils
				.intResult(getHibernateTemplate().findByCriteria(criteria.setProjection(Projections.rowCount())));
	}

	public int count(Criterion... criterion) {
		DetachedCriteria criteria = DetachedCriteria.forClass(getPersistentClass());
		for (Criterion c : criterion) {
			criteria.add(c);
		}
		return DataAccessUtils
				.intResult(getHibernateTemplate().findByCriteria(criteria.setProjection(Projections.rowCount())));
	}

	public E makePersistent(E entity) throws Exception {
		getHibernateTemplate().saveOrUpdate(entity);
		getHibernateTemplate().flush();
		return entity;
	}

	public void makeTransient(E entity) throws Exception {
		getHibernateTemplate().delete(entity);
		getHibernateTemplate().flush();
	}

	@SuppressWarnings("unchecked")
	public List<E> findByCriteria(Criterion... criterion) {
		DetachedCriteria criteria = DetachedCriteria.forClass(getPersistentClass());
		for (Criterion c : criterion) {
			criteria.add(c);
		}
		return (List<E>) getHibernateTemplate().findByCriteria(criteria);
	}

	public Timestamp getSystemTimestamp() {
		String sql = "SELECT CURRENT_TIMESTAMP AS systemtimestamp";
		Object obj = getHibernateTemplate().execute(session -> session.createNativeQuery(sql).uniqueResult());
		Timestamp syatemTimestamp = null;
		if (obj != null) {
			syatemTimestamp = (Timestamp) obj;
		}
		return syatemTimestamp;
	}

	private Page<E> wrapResult(List<E> results, Pageable page, long count) {
		if (results == null) {
			results = Collections.emptyList();
		}
		return new PageImpl<>(results, page, count);
	}

}
