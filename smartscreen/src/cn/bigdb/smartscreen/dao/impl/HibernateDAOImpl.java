package cn.bigdb.smartscreen.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.bigdb.smartscreen.common.DAOException;
import cn.bigdb.smartscreen.common.JspPage;
import cn.bigdb.smartscreen.dao.HibernateDAO;


public class HibernateDAOImpl  extends HibernateDaoSupport implements HibernateDAO {

	/**
	 * Get object matching the given key and return it.
	 * 
	 * @param refClass
	 * @param key
	 * @return
	 */
	public Object get(Class<?> refClass, Serializable key) throws DAOException {
		try {
			Session session = SessionFactoryUtils.getSession(getSessionFactory(), false);
			return get(refClass, key, session);
		} catch(HibernateException heex) {
            heex.printStackTrace();
            throw SessionFactoryUtils.convertHibernateAccessException(heex);
        }
	}

	/**
	 * Get object matching the given key and return it.
	 * 
	 * @param refClass
	 * @param key
	 * @param s
	 * @return
	 */
	private Object get(Class<?> refClass, Serializable key, Session s) { 
		return s.get(refClass, key);
	}

	/**
	 * Load object matching the given key and return it.
	 * 
	 * @param refClass
	 * @param key
	 * @return
	 */
	public Object load(Class<?> refClass, Serializable key) throws DAOException {		
		try {
			Session session = SessionFactoryUtils.getSession(getSessionFactory(), false);
			return load(refClass, key, session);
		} catch(HibernateException heex) {
            heex.printStackTrace();
            throw SessionFactoryUtils.convertHibernateAccessException(heex);
        }
	}
	
	

	/**
	 * Load object matching the given key and return it.
	 * 
	 * @param refClass
	 * @param key
	 * @param s
	 * @return
	 */
	private Object load(Class<?> refClass, Serializable key, Session s) {
		return s.load(refClass, key);
	}

	/**
	 * Return all objects related to the implementation of this DAO with no filter.
	 * 
	 * @param refClass
	 * @param defaultOrder
	 * @return
	 */
	public List<?> findAll (Class<?> refClass, Order defaultOrder) throws DAOException {
		try {
			Session session = SessionFactoryUtils.getSession(getSessionFactory(), false);
			return findAll(refClass, session, defaultOrder);
		} catch(HibernateException heex) {
            heex.printStackTrace();
            throw SessionFactoryUtils.convertHibernateAccessException(heex);
        }
	}
	
	/**
	 * Return all objects related to the implementation of this DAO with no filter.
	 * Use the session given.
	 * 
	 * @param refClass
	 * @param s
	 * @param defaultOrder
	 * @return
	 */
	private List<?> findAll (Class<?> refClass, Session s, Order defaultOrder) {
		Criteria crit = s.createCriteria(refClass);
		if (null != defaultOrder) crit.addOrder(defaultOrder);
		return crit.list();
	}

	/**
	 * Execute a query. 
	 * @param queryStr a query expressed in Hibernate's query language
	 * @return a distinct list of instances (or arrays of instances)
	 */
	public Query getQuery(String queryStr) throws DAOException {
		try {
			Session session = SessionFactoryUtils.getSession(getSessionFactory(), false);
			Query query = getQuery(queryStr, session);
			return query;
		} catch(HibernateException heex) {
            heex.printStackTrace();
            throw SessionFactoryUtils.convertHibernateAccessException(heex);
        }
	}
	
	/**
	 * Execute a query. 
	 * @param queryStr a query expressed in Hibernate's query language
	 * @return a distinct list of instances (or arrays of instances)
	 */
	public SQLQuery getSQLQuery(String queryStr) throws DAOException {
		try {
			Session session = SessionFactoryUtils.getSession(getSessionFactory(), false);
			return getSQLQuery(queryStr, session);
		} catch(HibernateException heex) {
            heex.printStackTrace();
            throw SessionFactoryUtils.convertHibernateAccessException(heex);
        }
	}

	/**
	 * Execute a query but use the session given instead of creating a new one.
	 * @param queryStr a query expressed in Hibernate's query language
	 * @s the Session to use
	 */
	private SQLQuery getSQLQuery(String queryStr, Session s) {
		return s.createSQLQuery(queryStr);
	}

	
	/**
	 * Execute a query but use the session given instead of creating a new one.
	 * @param queryStr a query expressed in Hibernate's query language
	 * @s the Session to use
	 */
	private Query getQuery(String queryStr, Session s) {
		
		return s.createQuery(queryStr);
	}

	/**
	 * Execute a query. 
	 * @param query a query expressed in Hibernate's query language
	 * @param queryStr the name of a query defined externally 
	 * @param param the first parameter to set
	 * @return Query
	 */
	public Query getQuery(String queryStr, Serializable param) throws DAOException {
		try {
			Session session = SessionFactoryUtils.getSession(getSessionFactory(), false);
			return getQuery(queryStr, param, session);
		} catch(HibernateException heex) {
            heex.printStackTrace();
            throw SessionFactoryUtils.convertHibernateAccessException(heex);
        }
	}

	/**
	 * Execute a query but use the session given instead of creating a new one.
	 * @param queryStr a query expressed in Hibernate's query language
	 * @param param the first parameter to set
	 * @s the Session to use
	 * @return Query
	 */
	private Query getQuery(String queryStr, Serializable param, Session s) {
		Query q = getQuery(queryStr, s);
		q.setParameter(0, param);
		return q;
	}

	/**
	 * Execute a query. 
	 * @param queryStr a query expressed in Hibernate's query language
	 * @param params the parameter array
	 * @return Query
	 */
	public Query getQuery(String queryStr, Serializable[] params) throws DAOException {
		try {
			Session session = SessionFactoryUtils.getSession(getSessionFactory(), false);
			return getQuery(queryStr, params, session);
		} catch(HibernateException heex) {
            heex.printStackTrace();
            throw SessionFactoryUtils.convertHibernateAccessException(heex);
        }
	}

	/**
	 * Execute a query but use the session given instead of creating a new one.
	 * @param queryStr a query expressed in Hibernate's query language
	 * @param params the parameter array
	 * @s the Session
	 * @return Query
	 */
	private Query getQuery(String queryStr, Serializable[] params, Session s) {
		Query q = getQuery(queryStr, s);
		if (null != params) {
			for (int i = 0; i < params.length; i++) {
				q.setParameter(i, params[i]);
			}
		}
		return q;
	}

	/**
	 * Obtain an instance of Query for a named query string defined in the mapping file.
	 * Use the parameters given.
	 * @param queryStr a query expressed in Hibernate's query language
	 * @param params the parameter Map
	 * @return Query
	 */
	public Query getQuery(String queryStr, Map<String, Object> params) throws DAOException {
		try {
			Session session = SessionFactoryUtils.getSession(getSessionFactory(), false);
			return getQuery(queryStr, params, session);
		} catch(HibernateException heex) {
            heex.printStackTrace();
            throw SessionFactoryUtils.convertHibernateAccessException(heex);
        }
	}

	/**
	 * Obtain an instance of Query for a named query string defined in the mapping file.
	 * Use the parameters given and the Session given.
	 * @param queryStr a query expressed in Hibernate's query language
	 * @param params the parameter Map
	 * @s the Session
	 * @return Query
	 */
	private Query getQuery(String queryStr, Map<String, Object> params, Session s) {
		Query q = getQuery(queryStr, s);
		if (null != params) {
			for (Iterator<Map.Entry<String, Object>> i=params.entrySet().iterator(); i.hasNext(); ) {
				Map.Entry<String, Object> entry = i.next();
				q.setParameter(entry.getKey(), entry.getValue());
			}
		}
		return q;
	}

	/**
	 * getList
	 * 
	 * @param queryStr
	 * @return
	 * @throws DAOException
	 */
	public List<?> getList(String queryStr) throws DAOException {
		return getQuery(queryStr).list();
	}
	
	/**
	 * getList
	 * 
	 * @param queryStr
	 * @param param
	 * @return
	 * @throws DAOException
	 */
	public List<?> getList(String queryStr, Serializable param) throws DAOException {
		return getQuery(queryStr, param).list();
	}
	
	/**
	 * getList
	 * 
	 * @param queryStr
	 * @param params
	 * @return
	 * @throws DAOException
	 */
	public List<?> getList(String queryStr, Serializable[] params) throws DAOException {
		List<?> list = getQuery(queryStr, params).list();
		return list;
	}
	
	/**
	 * getList
	 * 
	 * @param queryStr
	 * @param params
	 * @return
	 * @throws DAOException
	 */
	public List<?> getList(String queryStr, Map<String, Object> params) throws DAOException {
		return getQuery(queryStr, params).list();
	}
	
	/**
	 * Used by the base DAO classes but here for your modification
	 * Persist the given transient instance, first assigning a generated identifier. 
	 * (Or using the current value of the identifier property if the assigned generator is used.) 
	 */
	public Serializable save(final Object obj) throws DAOException {
		try {
			Session session = SessionFactoryUtils.getSession(getSessionFactory(), false);
			return save(obj, session);
		} catch(HibernateException heex) {
            heex.printStackTrace();
            throw SessionFactoryUtils.convertHibernateAccessException(heex);
        }
	}

	/**
	 * Used by the base DAO classes but here for your modification
	 * Persist the given transient instance, first assigning a generated identifier. 
	 * (Or using the current value of the identifier property if the assigned generator is used.) 
	 */
	private Serializable save(Object obj, Session s) {
		return s.save(obj);
	}

	/**
	 * Used by the base DAO classes but here for your modification
	 * Either save() or update() the given instance, depending upon the value of its
	 * identifier property.
	 */
	public void saveOrUpdate(final Object obj) throws DAOException {
		try {
			Session session = SessionFactoryUtils.getSession(getSessionFactory(), false);
			saveOrUpdate(obj, session);
		} catch(HibernateException heex) {
            heex.printStackTrace();
            throw SessionFactoryUtils.convertHibernateAccessException(heex);
        }
	}

	/**
	 * Used by the base DAO classes but here for your modification
	 * Either save() or update() the given instance, depending upon the value of its
	 * identifier property.
	 */
	private void saveOrUpdate(Object obj, Session s) {
		s.saveOrUpdate(obj);
	}

	/**
	 * Used by the base DAO classes but here for your modification
	 * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
	 * instance with the same identifier in the current session.
	 * @param obj a transient instance containing updated state
	 */
	public void update(final Object obj) throws DAOException {
		try {
			Session session = SessionFactoryUtils.getSession(getSessionFactory(), false);
			update(obj, session);
		} catch(HibernateException heex) {
            heex.printStackTrace();
            throw SessionFactoryUtils.convertHibernateAccessException(heex);
        }
	}

	/**
	 * Used by the base DAO classes but here for your modification
	 * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
	 * instance with the same identifier in the current session.
	 * @param obj a transient instance containing updated state
	 * @param s the Session
	 */
	private void update(Object obj, Session s) {
		s.update(obj);
	}

	/**
	 * Delete all objects returned by the query
	 */
	public int delete (final Query query)throws DAOException {
		try {
			Session session = SessionFactoryUtils.getSession(getSessionFactory(), false);
			return delete(query, session);
		} catch(HibernateException heex) {
            heex.printStackTrace();
            throw SessionFactoryUtils.convertHibernateAccessException(heex);
        }
	}

	/**
	 * Delete all objects returned by the query
	 */
	private int delete (Query query, Session s) {
		List<?> list = query.list();
		for (Iterator<?> i=list.iterator(); i.hasNext(); ) {
			delete(i.next(), s);
		}
		return list.size();
	}

	/**
	 * Used by the base DAO classes but here for your modification
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 */
	public void delete(Class<?> refClass, Serializable key) throws DAOException {
		try {
			Session session = SessionFactoryUtils.getSession(getSessionFactory(), false);
			delete(get(refClass, key, session), session);
		} catch(HibernateException heex) {
            heex.printStackTrace();
            throw SessionFactoryUtils.convertHibernateAccessException(heex);
        }
	}

	/**
	 * Used by the base DAO classes but here for your modification
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 */
	public void delete(Class<?> refClass, Serializable[] key) throws DAOException {
		try {
			Session session = SessionFactoryUtils.getSession(getSessionFactory(), false);
			for (int i = 0; i < key.length; i++) {
				delete(get(refClass, key[i], session), session);
			}
		} catch(HibernateException heex) {
            heex.printStackTrace();
            throw SessionFactoryUtils.convertHibernateAccessException(heex);
        }
	}
	
	/**
	 * Used by the base DAO classes but here for your modification
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 */
	private void delete(Object obj, Session s) {
		s.delete(obj);
	}

	/**
	 * Used by the base DAO classes but here for your modification
	 * Re-read the state of the given instance from the underlying database. It is inadvisable to use this to implement
	 * long-running sessions that span many business tasks. This method is, however, useful in certain special circumstances.
	 */
	protected void refresh(Object obj, Session s) {
		s.refresh(obj);
	}

	/**
	 * 
	 * @param t
	 */
	protected void throwException (Throwable t) {
		if (t instanceof HibernateException) throw (HibernateException) t;
		else if (t instanceof RuntimeException) throw (RuntimeException) t;
		else throw new HibernateException(t);
	}

	public void delete(String hql) throws DAOException {
			Session session = SessionFactoryUtils.getSession(getSessionFactory(), false);
			session.createQuery(hql).executeUpdate();
	}

	/**
	 * 实现接口中的merge功能
	 * 此功能主要解决session中的游离类实例与新创建的类实例ID冲突的问题
	 * 同时此方法在无ID冲突时做save操作，有冲突时做update操作，也即：插入数据之前查询一次数据库
	 * 请使用的时候仔细考虑业务逻辑，根据实际情况选择
	 * @param obj 
	 */
	public void merge(final Object obj) throws DAOException {
		try {
			Session session = SessionFactoryUtils.getSession(getSessionFactory(), false);
			session.merge(obj);
		} catch(HibernateException heex) {
            heex.printStackTrace();
            throw SessionFactoryUtils.convertHibernateAccessException(heex);
        }
	}
	
	public void update( String hql, Map<String, Object> params) throws DAOException{
		Session session = SessionFactoryUtils.getSession(getSessionFactory(), false);
		Query update = session.createQuery(hql);
		for (Map.Entry<String, Object> field : params.entrySet()) {
			update.setParameter(field.getKey(), field.getValue());
		}
		update.executeUpdate();
	}
	
	
	public void delete(String hql, Map<String, Object> params) throws DAOException{
		Session session = SessionFactoryUtils.getSession(getSessionFactory(), false);
		Query delete = session.createQuery(hql);
		for (Map.Entry<String, Object> field : params.entrySet()) {
			delete.setParameter(field.getKey(), field.getValue());
		}
		delete.executeUpdate();
	}
	
	private SQLQuery createSQLQuery(String queryStr, Map<String, Class<?>> mapEntry) throws DAOException{
		SQLQuery sqlQuery = getSQLQuery(queryStr);
		if(mapEntry == null) return sqlQuery;
		Iterator<Map.Entry<String, Class<?>>> map =  mapEntry.entrySet().iterator();
		Map.Entry<String, Class<?>> entry = null;
		while(map.hasNext()){
			entry = map.next();
			sqlQuery.addEntity(entry.getKey(), entry.getValue());
		}
		return sqlQuery;
	}
	
	public List<?> getSQLList(String queryStr, Map<String, Class<?>> mapEntry) throws DAOException{
		return this.createSQLQuery(queryStr, mapEntry).list();
	}
	
	public List<?> getSQLList(String queryStr,Map<String, Class<?>> mapEntry, Map<String, Object> mapParam) throws DAOException{
		SQLQuery sqlQuery = this.createSQLQuery(queryStr, mapEntry);
		Iterator<Map.Entry<String, Object>> map =  mapParam.entrySet().iterator();
		Map.Entry<String, Object> entry = null;
		while(map.hasNext()){
			entry = map.next();
			sqlQuery.setParameter(entry.getKey(), entry.getValue());
		}
		return sqlQuery.list();
	}
	
	public void clearCatch(){
		Session session = SessionFactoryUtils.getSession(getSessionFactory(), false);
		session.flush();
		session.clear();
	}
	
	public List<?> getListForPage(final String hql, final int offset,final int length) {
		List<?> list = getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				query.setFirstResult(offset);
				query.setMaxResults(length);
				List<?> list = query.list();
				return list;
			}
		});
		return list;
	}

	private int  getSizeByHql(String queryStr,Serializable[] params)throws DAOException{
		String countSqlString = processCountHql(queryStr);
		List<?> list  = getList(countSqlString,params);
		int size=0;
		if(null != list && list.size()>0){
			size = Integer.parseInt(list.get(0).toString());
		}
		return size;
	}
	private  String processCountHql(String queryStr){
		StringBuffer hql = new StringBuffer(" select count(*) ");
		int index = queryStr.indexOf("from");
		if(index == -1){
			index =queryStr.indexOf("FROM");
		}
		hql.append(queryStr.substring(index, queryStr.length()));
		return hql.toString();
	}
	 
	/**
	 * hql分页查询
	 * TODO
	 * @param queryStr 查询HQL语句 
	 * @param jspPage 分页对象
	 * @return
	 * @throws DAOException
	 * List
	 */
	public List<?> getListForPage(String queryStr, JspPage jspPage) throws DAOException {
		if(null == jspPage){
			jspPage = new JspPage();
		}
		int currentPage = jspPage.getCurrentPage();
		int pageCount = jspPage.getPerPage();
		Integer pageSize =   getSizeByHql(queryStr,null);
		jspPage.setTotalRecord(pageSize);//设置总记录数
		int startIndex =0;
		int totalSize = 0;
		
		if(pageSize%pageCount==0){
			totalSize =pageSize/pageCount;
		}else{
			totalSize =(pageSize/pageCount)+1;
		}
		jspPage.setTotalPage(totalSize); //设置总页数
		
		if(currentPage>totalSize){
			currentPage=totalSize;
			jspPage.setCurrentPage(totalSize);
		} 
		startIndex = (currentPage-1)*pageCount;
		final String hql =  queryStr;
		final int startIndexs = startIndex;
		final int endIndexs = pageCount; 
		List<?> resultList = this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException { 
				Query query =  session.createQuery(hql); 
				query.setFirstResult(startIndexs);
			    query.setMaxResults(endIndexs);
				return query.list();
			}
		
		} );
		return resultList;  
	}

	public int getQueryForInt(String queryStr, Serializable[] params)
			throws DAOException {
		List<?> list  = getList(queryStr,params);
		int size=0;
		if(null != list && list.size()>0){
			size = Integer.parseInt(list.get(0).toString());
		}		
		return size;
	}

	public int getQueryForInt(String queryStr) throws DAOException {
		List<?> list  = getList(queryStr);
		int size=0;
		if(null != list && list.size()>0){
			size = Integer.parseInt(list.get(0).toString());
		}		
		return size;
	}
	
}
