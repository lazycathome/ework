package cn.bigdb.smartscreen.dao.impl;

import java.io.CharArrayReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

import cn.bigdb.smartscreen.dao.BaseDao;


public class BaseDaoImpl implements BaseDao {

	private static Logger logger =  Logger.getLogger(BaseDaoImpl.class);
	
	protected String ADD = "add";
	protected String EDIT = "edit";
	protected String DEL = "del";
	
	private JdbcTemplate jdbcTemplate;
	
	public void exceuteSql(String sql) throws SQLException{
		getJdbcTemplate().execute(sql);
	}
	
	@SuppressWarnings("unchecked")
	public List<?> queryRes(String sql, final List<Object> vList)
			throws SQLException {
//		log.info(sql);
		if(vList == null){
			return getJdbcTemplate().queryForList(sql);
		}else{
			return getJdbcTemplate().queryForList(sql, this.getParams(vList));
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
			final List<List<Object>> vList) throws SQLException {
		logger.info(vList.size());
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
		getJdbcTemplate().batchUpdate(sql,setter);
		return null;
	}

	public long queryCount(String sql,
			List<Object> vList) throws SQLException {
		if(vList == null){
			return getJdbcTemplate().queryForLong(sql);
		}else{
			return getJdbcTemplate().queryForLong(sql, this.getParams(vList));
		}
	}

	public List<Boolean> saveOrUpdateBatchData(List<String> sql, final List<List<Object>> vList) throws SQLException{
		System.out.println("同一个表，属性名不同批量保存开始："+System.currentTimeMillis());
		JdbcTemplate jt = getJdbcTemplate();
		PreparedStatement ps = null;
		Connection conn=null;
		List<Object> tempValue = null;
		int vLen = vList.size();
		for (int k = 0; k < vLen; k++){
			try { 
				if(((k+1) % 1000) ==0 || (k+1)%1000!=0){
			    	conn = jt.getDataSource().getConnection();
			        conn.setAutoCommit(false);   
				}
		        ps = conn.prepareStatement(sql.get(k));
		        tempValue = vList.get(k);
		        int tempLen = tempValue.size();
			    for (int i = 1; i <= tempLen; i++) {
			    	ps.setObject(i, tempValue.get(i-1));
			    }
			    ps.addBatch();	
	            if (((k+1) % 1000) == 0) { 
	                ps.executeBatch(); 
	            }else  if(tempLen%1000!=0){
		        	ps.executeBatch();
		        	ps.clearBatch();
	            }
	            conn.commit();
			   } catch (Exception ex) {   
				   new  SQLException(ex);
				   logger.error("批量保存失败");
			        try {
						conn.rollback();
					} catch (SQLException e) {
						logger.error("批量保存回滚失败");
						new  SQLException(e);
					}   
			    } finally {   
			        try {
			        	ps.close();
						conn.close();
					} catch (SQLException e) {
						logger.error("关闭连接异常");
						new  SQLException(e);
					}   
			    }  
			
		}
		System.out.println("同一个表，属性名不同批量保存结束："+System.currentTimeMillis());
		return null;
	}
	
	
	public List<Boolean> save(String sql,
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
		
		getJdbcTemplate().batchUpdate(sql,setter);
		return null;
	}

	public List<Boolean> update(String sql,
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
		
		getJdbcTemplate().batchUpdate(sql,setter);
		return null;
	}
	
	public String getNextVal() throws SQLException{
		String sql = "select gcdata_id.nextval from dual";
		List<?> list = this.queryRes(sql, null);
		return ((Map)list.get(0)).get("nextval").toString();
	}
	
	public long queryDataCount(String sql, List<Object> vList)
			throws SQLException {
		return this.queryCount(sql, vList);
	
	}
	
	protected List<Object> createCondition(Object str1){
		List<Object> list = new ArrayList<Object>();
		list.add(str1);
		return list;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	protected Object getValue(Map<String, Object> map, String key){
		if(map == null) return null;
		return map.get(key);
	}
	
	protected String getString(Map<String, Object> map, String key){
		Object value = getValue(map, key);
		if( value == null){
			return "";
		}
		return value.toString();
	} 
	
	protected  long getLong(Map<String, Object> map, String key){
		Object value = getValue(map, key);
		if( value == null){
			return 0;
		}
		return Long.valueOf(value.toString());
		
	}
	
	protected long getLong(String str){
		if( str == null || "".equals(str)){
			return 0;
		}
		return Long.valueOf(str);
		
	}
	
	protected boolean getBoolean(Map<String, Object> map, String key){
		Object value = getValue(map, key);
		if( value == null){
			return false;
		}
		return Boolean.valueOf(value.toString());
		
	}
	
	protected Integer getInteger(Map<String, Object> map, String key){
		Object value = getValue(map, key);
		if( value == null){
			return 0;
		}
		return Integer.valueOf(value.toString());
	}
	
	protected int getInt(Map<String, Object> map, String key){
		return getInteger(map, key);
	}
	
}
