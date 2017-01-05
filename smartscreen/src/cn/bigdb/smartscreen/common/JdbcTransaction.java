package cn.bigdb.smartscreen.common;

import java.sql.Connection;  
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JdbcTransaction
{
	private static Log log = LogFactory.getLog(JdbcTransaction.class);
	private JdbcTemplate jt;
	
	public int getMaxRow()
	{
		return jt.getMaxRows();
	}
	
	public void execute(String sqlStr)
	{
		jt.execute(sqlStr);
	}
	
	public int update(String sqlStr)
	{
		return jt.update(sqlStr);
		
	}
	
	public int update(String sql, Object[] param){
		return jt.update(sql, param);
	}
	
	public int[] updateBatch(String sql, BatchPreparedStatementSetter setter)
	{
		int[] i = null;
		i = jt.batchUpdate(sql, setter);
		return i;
	}
	
	public void updateBatch(List sqlList)
	{
		String[] sqlStr = new String[sqlList.size()];
		for (int i = 0; i < sqlList.size(); i++)
		{
			sqlStr[i] = (String) sqlList.get(i);
		}
		Connection conn = null;
		Statement stmt = null;
		try
		{
			conn = jt.getDataSource().getConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			for (int i = 0; i < sqlStr.length; i++)
			{
				stmt.addBatch(sqlStr[i]);
			}
			stmt.executeBatch();
			conn.commit();
			stmt.clearBatch();
			System.out.println("has finished.");
		}
		catch (SQLException e)
		{
			try
			{
				if(conn != null)
				{
					conn.rollback();
				}
			}
			catch (SQLException e1)
			{
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if (stmt != null)
				{
					stmt.close();
				}
				if (conn != null)
				{
					conn.close();
				}
			}
			catch (SQLException e)
			{
				e.printStackTrace();
				
			}
		}
	}
	/**
	 * 新增同名方法。用于删除调度结果集
	 * @param sqlList
	 */
	public void updateBatch(String[] sqlList)
	{
		
		System.out.println("updateBatch will start.");
		
		System.out.println("will execut");
		Connection conn = null;
		Statement stmt = null;
		try
		{
			conn = jt.getDataSource().getConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			for (int i = 0; i < sqlList.length; i++)
			{
				stmt.addBatch(sqlList[i]);
			}
			stmt.executeBatch();
			conn.commit();
			stmt.clearBatch();
			System.out.println("has finished.");
		}
		catch (SQLException e)
		{
			try
			{
				if(conn != null)
				{
					conn.rollback();
				}
			}
			catch (SQLException e1)
			{
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if (stmt != null)
				{
					stmt.close();
				}
				if (conn != null)
				{
					conn.close();
				}
			}
			catch (SQLException e)
			{
				e.printStackTrace();
				
			}
		}
	}
	
	public SqlRowSet queryForRowSet(String sqlStr)
	{
		return jt.queryForRowSet(sqlStr);
	}
	
	public int queryForInt(String sqlStr)
	{
		return jt.queryForInt(sqlStr);
	}
	
	public List queryForList(String sqlStr)
	{
		return jt.queryForList(sqlStr);
	}
	
	public Map queryForMap(String sqlStr)
	{
		return jt.queryForMap(sqlStr);
	}
	
	public void setJt(JdbcTemplate jt)
	{
		this.jt = jt;
	}
	
	public JdbcTemplate getJt()
	{
		return jt;
	}
	
	private LobHandler lobHandler = null; // ① 定义 LobHandler 属性
	
	public LobHandler getLobHandler()
	{
		return lobHandler;
	}
	
	public void setLobHandler(LobHandler lobHandler)
	{
		this.lobHandler = lobHandler;
	}
	
	public int queryForInt(String sql,Object[] param){
		return jt.queryForInt(sql, param);
	}
	public List<Map<String, Object>> queryForList(String sql,Object[] param){
		List<Map<String, Object>> list = jt.queryForList(sql, param);
		log.debug("result size: " + list.size());
		return list;
	}
}
