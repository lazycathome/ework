package cn.bigdb.gallery.updatefile.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.bigdb.gallery.core.Constants;
import cn.bigdb.gallery.core.JDBCUtils;
import cn.bigdb.gallery.updatefile.entity.UpdateFile;

@Repository
public class UpdateFileDaoImpl implements UpdateFileDao {

	@Autowired
	private JDBCUtils<UpdateFile> jdbc;
	
	@Override
	public long save(UpdateFile updateFile) throws SQLException {
		String sql = "insert into update_file(create_time, file_url, md5code) values(?, ?, ?)";
		List<Object> list = new ArrayList<Object>(3);
		list.add(updateFile.getCreateTime());
		list.add(updateFile.getFileUrl());
		list.add(updateFile.getMd5code());
		jdbc.save(sql, list);
		return jdbc.queryForLong(Constants.LAST_INSERT_ID);
	}

}
