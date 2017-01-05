package cn.bigdb.gallery.content.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.bigdb.gallery.content.entity.EquipContent;
import cn.bigdb.gallery.content.entity.EquipContentRowMapper;
import cn.bigdb.gallery.core.Code;
import cn.bigdb.gallery.core.JDBCUtils;

@Repository
public class EquipContentDaoImpl implements EquipContentDao {

	@Autowired
	private JDBCUtils<EquipContent> jdbc;
	
	@Override
	public int save(EquipContent eContent) throws SQLException{
		String sql = "insert into equip_content(content_id, equip_id) values(?, ?)";
		List<Object> list = new ArrayList<Object>(2);
		list.add(eContent.getContentId());
		list.add(eContent.getEquipId());
		jdbc.save(sql, list);
		return Code.SUCCESS;
	}

	@Override
	public List<EquipContent> get(String equipId) throws SQLException {
		String sql = "select * from equip_content where equip_id = ?";
		return jdbc.executeSql(sql, new Object[]{equipId}, new EquipContentRowMapper());
	}

}
