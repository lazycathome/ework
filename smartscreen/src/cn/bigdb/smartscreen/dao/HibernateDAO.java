package cn.bigdb.smartscreen.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.criterion.Order;

import cn.bigdb.smartscreen.common.DAOException;
import cn.bigdb.smartscreen.common.JspPage;


public interface HibernateDAO{
	/**
	 * Get object matching the given key and return it.
	 * 
	 * @param refClass
	 * @param key  
	 * @return
	 */
	public Object get(Class<?> refClass, Serializable key) throws DAOException;

	/**
	 * Load object matching the given key and return it.
	 * 
	 * @param refClass
	 * @param key
	 * @return
	 */
	 public Object load(Class<?> refClass, Serializable key) throws DAOException;

	/**
	 * Return all objects related to the implementation of this DAO with no filter.
	 * 
	 * @param refClass
	 * @param defaultOrder
	 * @return
	 */
	public java.util.List<?> findAll (Class<?> refClass, Order defaultOrder) throws DAOException;

	/**
	 * Execute a query. 
	 * @param queryStr a query expressed in Hibernate's query language
	 * @return a distinct list of instances (or arrays of instances)
	 */
	public Query getQuery(String queryStr) throws DAOException;

	/**
	 * Execute a query. 
	 * @param query a query expressed in Hibernate's query language
	 * @param queryStr the name of a query defined externally 
	 * @param param the first parameter to set
	 * @return Query
	 */
	public Query getQuery(String queryStr, Serializable param) throws DAOException;

	/**
	 * Execute a query. 
	 * @param queryStr a query expressed in Hibernate's query language
	 * @param params the parameter array
	 * @return Query
	 */
	public Query getQuery(String queryStr, Serializable[] params) throws DAOException;

	/**
	 * Obtain an instance of Query for a named query string defined in the mapping file.
	 * Use the parameters given.
	 * @param queryStr a query expressed in Hibernate's query language
	 * @param params the parameter Map
	 * @return Query
	 */
	public Query getQuery(String queryStr, Map<String, Object> params) throws DAOException;

	/**
	 * getList
	 * 
	 * @param queryStr
	 * @return
	 * @throws DAOException
	 */
	public List<?> getList(String queryStr) throws DAOException;
	
	/**
	 * getList
	 * 
	 * @param queryStr
	 * @param param
	 * @return
	 * @throws DAOException
	 */
	public List<?> getList(String queryStr, Serializable param) throws DAOException;
	
	/**
	 * getList
	 * 
	 * @param queryStr
	 * @param params
	 * @return
	 * @throws DAOException
	 */
	public List<?> getList(String queryStr, Serializable[] params) throws DAOException;
	
	/**
	 * getList
	 * 
	 * @param queryStr
	 * @param params
	 * @return
	 * @throws DAOException
	 */
	public List<?> getList(String queryStr, Map<String, Object> params) throws DAOException;
	
	/**
	 * Used by the base DAO classes but here for your modification
	 * Persist the given transient instance, first assigning a generated identifier. 
	 * (Or using the current value of the identifier property if the assigned generator is used.) 
	 */
	public Serializable save(final Object obj) throws DAOException;

	/**
	 * Used by the base DAO classes but here for your modification
	 * Either save() or update() the given instance, depending upon the value of its
	 * identifier property.
	 */
	public void saveOrUpdate(final Object obj) throws DAOException;

	/**
	 * Used by the base DAO classes but here for your modification
	 * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
	 * instance with the same identifier in the current session.
	 * @param obj a transient instance containing updated state
	 */
	public void update(final Object obj) throws DAOException;

	/**
	 * Delete all objects returned by the query
	 */
	public int delete (final Query query)throws DAOException;

	/**
	 * Used by the base DAO classes but here for your modification
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 */
	public void delete(Class<?> refClass, Serializable key) throws DAOException;
	
	/**
	 * Used by the base DAO classes but here for your modification
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 */
	public void delete(Class<?> refClass, Serializable[] key) throws DAOException;
	public void delete( String hql) throws DAOException;
	
	/**
	 * 
	 * @param obj
	 * @throws DAOException
	 */
	public void merge(final Object obj) throws DAOException;
	
	public void update( String hql, Map<String, Object> params) throws DAOException;
	
	public void delete(String queryStr, Map<String, Object> params) throws DAOException;
	
	public List<?> getSQLList(String queryStr,Map<String, Class<?>> mapEntry) throws DAOException;
	
	public List<?> getSQLList(String queryStr,Map<String, Class<?>> mapEntry, Map<String, Object> mapParam) throws DAOException;
	
	public void clearCatch();
	
	/**
	 * hql分页查询
	 * TODO
	 * @param queryStr 查询HQL语句 
	 * @param jspPage 分页对象
	 * @return
	 * @throws DAOException
	 * List
	 */
	public List<?> getListForPage(String queryStr,JspPage jspPage)throws DAOException;
	
	public int getQueryForInt(String queryStr) throws DAOException;
	
	public int getQueryForInt(String queryStr,Serializable[] params) throws DAOException;
	
	
}
