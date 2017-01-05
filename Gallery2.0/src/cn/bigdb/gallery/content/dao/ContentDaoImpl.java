package cn.bigdb.gallery.content.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.bigdb.gallery.content.entity.Content;
import cn.bigdb.gallery.content.entity.ContentRowMapper;
import cn.bigdb.gallery.core.Constants;
import cn.bigdb.gallery.core.JDBCUtils;
import cn.bigdb.gallery.core.Page;
import cn.bigdb.gallery.utils.SqlUtils;

@Repository
public class ContentDaoImpl implements ContentDao {

	@Autowired
	private JDBCUtils<Content> jdbc;
	
	@Override
	public long save(List<Content> contents) throws SQLException {
		String sql = "insert into content(title, `desc`, duration, state, showdays, window, creator, create_time) "
				+ "values(?, ?, ?, ?, ?, ?, ?, ?)";
		List<List<Object>> vList = new ArrayList<List<Object>>(contents.size());
		for(int i = 0; i < contents.size(); i++){
			List<Object> list = new ArrayList<Object>(9);
			Content content = contents.get(i);
			list.add(content.getTitle());
			list.add(content.getDesc());
			list.add(content.getDuration());
			list.add(content.getState());
			list.add(content.getsShowDays());
			list.add(content.getsWindow());
			list.add(content.getCreator());
			list.add(content.getCreateTime());
			vList.add(list);
		}
		jdbc.saveBatch(sql, vList);
		long result = jdbc.queryForLong(Constants.LAST_INSERT_ID);
		return result;
	}

	@Override
	public List<Content> getList() {
		
		return null;
	}

	@Override
	public List<Content> getList(List<Long> idList) throws SQLException{
		StringBuilder sql = new StringBuilder("select * from content where id in(");
		sql.append(SqlUtils.sqlJoin(idList.size(), "?,"));
		sql.append(")");
		return jdbc.executeSql(sql.toString(), idList.toArray(new Object[idList.size()]), new ContentRowMapper());
	}

	@Override
	public void update(Content content) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public Content get(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Content> getList(long creator,
			Page<Content> page) throws SQLException {
		StringBuilder sql = new StringBuilder("select * from content where creator = ?");
		return jdbc.executeSqlByPage(sql.toString(), new Object[]{creator}, new ContentRowMapper(), page);
		
	}

}
