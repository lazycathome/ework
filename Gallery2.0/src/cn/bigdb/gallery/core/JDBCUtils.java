package cn.bigdb.gallery.core;

import java.io.CharArrayReader;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 * @author liujt
 * @date 2014年7月15日
 * @mail liujiangtao@gaiay.cn
 */

public class JDBCUtils<T> extends JdbcTemplate {
	private static Logger logger = Logger.getLogger(JDBCUtils.class.getName());

	public JDBCUtils(){}
	
	
	/**
	 * 执行一条sql语句    insert 
	 * @param sql  sql串
	 * @param pram  参数集合
	 * @return
	 */
	public void executeInsert(String sql, Object [] pram)throws SQLException{
		update(sql, pram); 
	}

	/**
	 * 执行一条sql语句  update 
	 * @param sql  sql串
	 * @param params  参数集合 
	 * @return
	 */
	public void executeUpdate(String sql, Object[] params)throws SQLException{
		executeInsert(sql,params);
	}
	/**
	 * 
	 * TODO  查询sql
	 * @param sql 查询的sql语句
	 * @param param sql语句参数集合
	 * @param row  结集所要反回的对像集合
	 * @return
	 * @throws Exception
	 * List 反回 RowMapper 封装的对象集合
	 */
	@SuppressWarnings("unchecked")
	public List<T> executeSql(String sql, Object[] param, RowMapper row) throws SQLException{
		List<T> list = query(sql, param,row);
		return list;
		
	}
	/**
	 * 
	 * TODO 查询 指定的SQL
	 * @param sql 需要查询的sql语句
	 * @param param sql语句的参数集合
	 * @return 结果集合 list->map[id:value]
	 * @throws Exception
	 * List
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> executeSql(String sql, Object[] param) throws SQLException{
		return queryForList(sql, param);
		
	}
	/**
	 * 
	 * TODO 查询sql 必需知道这条SQL结果集只有一行 多行结果集抛异常
	 * @param sql  指定sql串
	 * @param param参数结果集
	 * @return
	 * @throws Exception
	 * Map map->[column:value]
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> executeSqlMap(String sql, Object[] param) throws SQLException{ 
		return queryForMap(sql, param);
		
	}
	
	/**
	 * 
	 * TODO
	 * @param sql sql语句
	 * @param param 参数集合
	 * @param currentPage 当前第几页
	 * @param pageCount 每页显示多少条
	 * @return
	 * @throws Exception
	 * List<Map<String,Object>> 结果集list
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> executeSqlByPage(String sql, Object[]param, Page<T> page)throws SQLException{
		if(null == page){
			page = new Page<T>();
		}
		long pageNo = page.getPageNo();
		long pageSize = page.getPageSize();
		
		long count = executeSQlByCount(sql,param); 
		page.setTotalCount(count);

		long startIndex =0;
		long endIndex =0;
		long pageCount = 0;
		
		if(pageSize%count==0){
			pageCount =pageSize/count;
		}else{
			pageCount =(pageSize/count)+1;
		}
		page.setPageCount(pageCount); //设置总页数
		
		startIndex = (pageNo-1)*pageSize+1;
		endIndex = pageNo*pageSize;
		String executeSqlString = getSql(sql,startIndex,endIndex);
		return queryForList(executeSqlString, param);
	}
	/**
	 * 
	 * @param sql sql语句
	 * @param param 参数集合
	 * @param row RowMapper 封装的对象
	 * @param currentPage 当前第几页
	 * @param pageCount 第页显示多少条
	 * @return
	 * @throws Exception
	 * List<Map<String,Object>> 结果集list
	 */ 
	@SuppressWarnings("unchecked")
	public Page<T> executeSqlByPage(String sql, Object[]param, RowMapper row,Page<T> page)throws SQLException{
		
		if(null == page){
			page = new Page<T>();
		}
		long pageNo = page.getPageNo();
		int pageSize = page.getPageSize();
		long count = executeSQlByCount(sql,param); 
		page.setTotalCount(count);//设置总记录数
		long startIndex =0;
		long endIndex =0;
		long pageCount = 0;
		
		if(count%pageSize==0){
			pageCount =count/pageSize;
		}else{
			pageCount =(count/pageSize)+1;
		}
		page.setPageCount(pageCount); //设置总页数
		
		if(pageNo>pageCount){
			page.setPageNo(pageCount);
		} 
		startIndex = (pageNo-1)*pageSize;
		endIndex = pageNo*pageSize;
		String executeSqlString = getSql(sql,startIndex,endIndex);
		List<T> list = query(executeSqlString, param, row);
		page.setResults(list);
		return page;
	}
	
	private Integer executeSQlByCount(String sql, Object[]param) throws SQLException{
		StringBuffer sqlintString = new StringBuffer();
		sqlintString.append("select count(*) from (");
		sqlintString.append(sql);
		sqlintString.append(" ) as t");
		
		return queryForInt(sqlintString.toString(), param);
	}
	private String getSql(String sql,long startIndex,long endIndex){
		StringBuffer sb  =  new StringBuffer();
		sb.append(sql);
		sb.append(" limit ");
		sb.append(startIndex);
		sb.append(",");
		sb.append(endIndex);
		return sb.toString();
	}
	
	public List<?> queryRes(String sql, final List<Object> vList)
			throws SQLException {
		if(vList == null){
			return queryForList(sql);
		}else{
			return queryForList(sql, this.getParams(vList));
		}
	}
	
	public Object[] getParams(List<Object> list) {
		int vLen = list.size();
		Object[] o = new Object[vLen];
		for(int i = 0; i < vLen; i++){
			o[i] = list.get(i);
		}
		return o;
	}

	public List<Boolean> delete(String sql,
			final List<Object> list) throws SQLException {
		List<List<Object>> vList = new ArrayList<List<Object>>(1);
		vList.add(list);
		return this.deleteBatch(sql, vList);
	}
	
	public List<Boolean> deleteBatch(String sql,
			final List<List<Object>> vList) throws SQLException {
		
		BatchPreparedStatementSetter setter = new BatchPreparedStatementSetter() {
			public int getBatchSize() {
				return vList.size();
			}
			public void setValues(PreparedStatement ps, int i)throws SQLException {
				List<Object> list = vList.get(i);
				int listLen = list.size();
				for(int k=1; k<=listLen; k++){
					ps.setObject(k, list.get(k-1));
				}
			}
		};
		logger.info(sql);
		batchUpdate(sql,setter);
		return null;
	}

	public long queryCount(String sql,
			List<Object> vList) throws SQLException {
		if(vList == null){
			return queryForLong(sql);
		}else{
			return queryForLong(sql, this.getParams(vList));
		}
	}
	
	public List<Boolean> save(String sql,
			final List<Object> list) throws SQLException {
		List<List<Object>> vList = new ArrayList<List<Object>>(1);
		vList.add(list);
		return this.saveBatch(sql, vList);
	}

	public List<Boolean> saveBatch(String sql,
			final List<List<Object>> vList) throws SQLException {
		BatchPreparedStatementSetter setter = new BatchPreparedStatementSetter() {
			public int getBatchSize() {
				return vList.size();
			}
			public void setValues(PreparedStatement ps, int i)throws SQLException {
				List<Object> list = vList.get(i);
				int listLen = list.size();
				for(int k=1; k<=listLen; k++){
					Object obj = list.get(k-1);
					if(obj instanceof char[]){
						char[] cr = (char[])obj;
						CharArrayReader crd = new CharArrayReader(cr);
						ps.setCharacterStream(k, crd, cr.length);
					}else{
						ps.setObject(k,obj);
					}
				}
			}
		};
		
		batchUpdate(sql,setter);
		return null;
	}
	
	public List<Boolean> update(String sql,
			final List<Object> list) throws SQLException {
		List<List<Object>> vList = new ArrayList<List<Object>>(1);
		vList.add(list);
		return this.updateBatch(sql, vList);
	}

	public List<Boolean> updateBatch(String sql,
			final List<List<Object>> vList) throws SQLException {
		BatchPreparedStatementSetter setter = new BatchPreparedStatementSetter() {
			public int getBatchSize() {
				return vList.size();
			}
			public void setValues(PreparedStatement ps, int i)throws SQLException {
				List<Object> list = vList.get(i);
				int listLen = list.size();
				for(int k=1; k<=listLen; k++){
					ps.setObject(k,list.get(k-1));
				}
			}
		};
		
		batchUpdate(sql,setter);
		return null;
	}

}
