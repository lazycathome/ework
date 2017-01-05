package cn.bigdb.gallery.canvas.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.bigdb.gallery.canvas.entity.Canvas;
import cn.bigdb.gallery.canvas.entity.CanvasRowMapper;
import cn.bigdb.gallery.core.Constants;
import cn.bigdb.gallery.core.JDBCUtils;
import cn.bigdb.gallery.utils.SqlUtils;

@Repository
public class CanvasDaoImpl implements CanvasDao {

	@Autowired
	private JDBCUtils<Canvas> jdbc;

	@Override
	public void update(Canvas canvas) throws SQLException {
		
	}

	@Override
	public long save(List<Canvas> canvasList) throws SQLException {
		String sql = "insert into canvas(title, duration, background, `repeat`, backsound, backcolor, state, content_id) values(?, ?, ?, ?, ?, ?, ?, ?)";
		List<List<Object>> vList = new ArrayList<List<Object>>(canvasList.size());
		for(int i = 0; i < canvasList.size(); i++){
			List<Object> list = new ArrayList<Object>(8);
			Canvas canvas = canvasList.get(i);
			list.add(canvas.getPageName());
			list.add(canvas.getDuration());
			list.add(canvas.getBackground());
			list.add(canvas.getRepeat());
			list.add(canvas.getBacksound());
			list.add(canvas.getBackcolor());
			list.add(canvas.getState());
			list.add(canvas.getContentId());
			vList.add(list);
		}
		jdbc.saveBatch(sql, vList);
		long result = jdbc.queryForLong(Constants.LAST_INSERT_ID);
		return result;
	}

	@Override
	public List<Canvas> getList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Canvas> getList(long contentId) throws SQLException {
		String sql = "select * from canvas where content_id=?";
		return jdbc.executeSql(sql, new Object[]{contentId}, new CanvasRowMapper());
	}

	@Override
	public List<Canvas> getList(List<Long> contentIdList) throws SQLException {
		StringBuilder sql = new StringBuilder("select * from canvas where content_id in (");
		sql.append(SqlUtils.sqlJoin(contentIdList.size(), "?,"));
		sql.append(")");
		return jdbc.executeSql(sql.toString(), contentIdList.toArray(new Object[contentIdList.size()]), new CanvasRowMapper());
	}

}
