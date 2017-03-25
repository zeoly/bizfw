package com.thinkequip.bizfw.base.impl;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.thinkequip.bizfw.base.BaseDao;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {

	@Resource
	private SessionFactory sessionFactory;

	private Class<T> clazz;

	@SuppressWarnings("unchecked")
	public Class<T> getClazz() {
		if (clazz == null) {
			clazz = ((Class<T>) (((ParameterizedType) (this.getClass().getGenericSuperclass()))
					.getActualTypeArguments()[0]));
		}
		return clazz;
	}

	@Override
	public String save(T t) {
		return (String) getSession().save(t);
	}

	@Override
	public void saveOrUpdate(T t) {
		getSession().saveOrUpdate(t);
	}

	@Override
	public List<T> list() {
		String hql = "from " + getTableName();
		Query<T> query = createQuery(hql);
		List<T> list = query.list();
		return list;
	}

	@Override
	public void delete(String id) {
		getSession().delete(load(id));
	}

	@Override
	public void update(T t) {
		getSession().update(t);
	}

	@Override
	public T queryById(String id) {
		return this.get(id);
	}

	@Override
	public List<T> queryByFieldAndValue(String field, Object value) {
		String hql = "from " + getTableName() + " as t where t." + field + " = :value";
		Query<T> query = createQuery(hql);
		query.setParameter("value", value);
		List<T> list = query.list();
		if (list == null) {
			list = new ArrayList<T>();
		}
		return list;
	}

	@Override
	public void deleteByFieldAndValue(String field, Object value) {
		String hql = "delete from " + getTableName() + " as t where t." + field + " = :value";
		Query<T> query = createQuery(hql);
		query.setParameter("value", value);
		query.executeUpdate();
	}

	@Override
	public long getCountByFieldAndValue(String field, Object value) {
		String hql = "select count(0) from " + getTableName() + " as t where t." + field + " = :value";
		Query<T> query = createQuery(hql);
		query.setParameter("value", value);
		return ((Number) query.getSingleResult()).longValue();
	}

	public T load(String id) {
		return (T) getSession().load(getClazz(), id);
	}

	public T get(String id) {
		return (T) getSession().get(getClazz(), id);
	}

	@Override
	public Session getSession() {
		return getSessionFactory().getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Query<T> createQuery(String hql) {
		Session session = getSession();
		return session.createQuery(hql);
	}

	@Override
	public String getTableName() {
		return getClazz().getSimpleName();
	}

	@Resource
	public void setMySessionFactory(SessionFactory sessionFactory) {
		this.setSessionFactory(sessionFactory);
	}

}
