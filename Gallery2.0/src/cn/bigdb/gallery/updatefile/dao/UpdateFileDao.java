package cn.bigdb.gallery.updatefile.dao;

import java.sql.SQLException;

import cn.bigdb.gallery.updatefile.entity.UpdateFile;

public interface UpdateFileDao {
	
	long save(UpdateFile updateFile) throws SQLException;
}
