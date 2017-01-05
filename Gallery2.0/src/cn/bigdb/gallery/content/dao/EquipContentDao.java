package cn.bigdb.gallery.content.dao;

import java.sql.SQLException;
import java.util.List;

import cn.bigdb.gallery.content.entity.EquipContent;

public interface EquipContentDao {

	int  save(EquipContent eContent) throws SQLException;
	
	List<EquipContent> get(String equipId) throws SQLException;
}
