package cn.bigdb.gallery.resource.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.bigdb.gallery.core.Constants;
import cn.bigdb.gallery.core.JDBCUtils;
import cn.bigdb.gallery.resource.entity.ResourceInfo;
import cn.bigdb.gallery.resource.entity.ResourceInfoRowMapper;
import cn.bigdb.gallery.utils.SqlUtils;

@Repository
public class ResourceInfoDaoImpl implements ResourceInfoDao {

	@Autowired
	private JDBCUtils<ResourceInfo> jdbc;
	
	@Override
	public long save(List<ResourceInfo> resourceInfoList) throws SQLException {
		String sql = "insert into resourceinfo(title, type, duration, position, region, beautify, state, create_time) values(?, ?, ?, ?, ?, ?, ?, ?)";
		List<List<Object>> vList = new ArrayList<List<Object>>(resourceInfoList.size());
		for(int i = 0; i < resourceInfoList.size(); i++){
			List<Object> list = new ArrayList<Object>(8);
			ResourceInfo resourceInfo = resourceInfoList.get(i);
			list.add(resourceInfo.getName());
			list.add(resourceInfo.getType());
			list.add(resourceInfo.getDuration());
			list.add(resourceInfo.getPosition());
			list.add(resourceInfo.getRegion());
			list.add(resourceInfo.getBeautify());
			list.add(resourceInfo.getState());
			list.add(resourceInfo.getCreateTime());
			vList.add(list);
		}
		jdbc.saveBatch(sql, vList);
		return jdbc.queryForLong(Constants.LAST_INSERT_ID);
	}

	@Override
	public List<ResourceInfo> getList(List<Long> id) throws SQLException {
		StringBuilder sql = new StringBuilder("select * from resourceinfo where state = 2 and id in (");
		sql.append(SqlUtils.sqlJoin(id.size(), "?,"));
		sql.append(") order by sort asc");
		return jdbc.executeSql(sql.toString(), id.toArray(new Object[id.size()]), new ResourceInfoRowMapper());
	}

	@Override
	public void update(ResourceInfo resourceInfo) throws SQLException {
		String sql = "update resourceinfo set title=?, type = ?, duration = ?, position = ?, region = ?, beautify = ?, update_time = ? where id = ?";
		List<Object> list = new ArrayList<Object>(8);
		list.add(resourceInfo.getName());
		list.add(resourceInfo.getType());
		list.add(resourceInfo.getDuration());
		list.add(resourceInfo.getPosition());
		list.add(resourceInfo.getRegion());
		list.add(resourceInfo.getBeautify());
		list.add(resourceInfo.getUpdateTime());
		list.add(resourceInfo.getId());
		jdbc.update(sql, list);
	}

	@Override
	public ResourceInfo get(long id) throws SQLException {
		String sql = "select * from resourceinfo where id = ?";
		List<ResourceInfo> results = jdbc.executeSql(sql, new Object[]{id}, new ResourceInfoRowMapper());
		return (results == null || results.size() == 0) ? new ResourceInfo() : results.get(0);
	}
	
	@Override
	public void delete(long id, int state) throws SQLException {
		String sql = "update resourceinfo set state = ?, update_time = ? where id = ?";
		List<Object> list = new ArrayList<Object>(2);
		list.add(state);
		list.add(id);
		jdbc.update(sql, list);
	}
	
	@Override
	public int updateSort(List<ResourceInfo> sortList) throws SQLException{
		String sql = "update resourceinfo set title=?, type = ?, duration = ?, position = ?, region = ?, beautify = ?, sort = ?, update_time = ? where id = ?";
		long time = System.currentTimeMillis();
		List<List<Object>> vList = new ArrayList<List<Object>>(sortList.size());
		for (int i = 0; i < sortList.size(); i++) {
			List<Object> list = new ArrayList<Object>(2);
			ResourceInfo rInfo = sortList.get(i);
			list.add(rInfo.getName());
			list.add(rInfo.getType());
			list.add(rInfo.getDuration());
			list.add(rInfo.getPosition());
			list.add(rInfo.getRegion());
			list.add(rInfo.getBeautify());
			list.add(rInfo.getSort());
			list.add(time);
			list.add(rInfo.getId());
			vList.add(list);
		}
		
		jdbc.updateBatch(sql, vList);
		return sortList.size();
	}

	@Override
	public List<ResourceInfo> getResourceFiles(List<Long> resIdList,
			long lastQueryTime) throws SQLException {
		StringBuilder sql = new StringBuilder("select * from resourceinfo where update_time >= ? and type >= 1 and id in (");
		sql.append(SqlUtils.sqlJoin(resIdList.size(), "?,"));
		sql.append(")");
		resIdList.add(0, lastQueryTime);
		return jdbc.executeSql(sql.toString(), resIdList.toArray(new Object[resIdList.size()]), new ResourceInfoRowMapper());
	}
}
