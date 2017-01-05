package cn.bigdb.gallery.updatefile.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.bigdb.gallery.core.Code;
import cn.bigdb.gallery.core.JDBCUtils;
import cn.bigdb.gallery.updatefile.entity.EquipUpdate;

@Repository
public class EquipUpdateDaoImpl implements EquipUpdateDao{

	@Autowired
	private JDBCUtils<EquipUpdate> jdbc;
	
	private String UPDATEEQUIPUPDATE = "update equip_update set status=? where updateTime=? and equipId=?";
	
	private String INSERT = "insert into equip_update(updatefile_id, equip_id, update_time, status) values(?, ?, ?, ?)";
	
	@Override
	public long addEquipUpdate(EquipUpdate equipUpdate) throws SQLException {
		List<Object> list = new ArrayList<Object>(4);
		list.add(equipUpdate.getUpdateFileId());
		list.add(equipUpdate.getEquipId());
		list.add(equipUpdate.getUpdateFileId());
		list.add(equipUpdate.getStatus());
		jdbc.save(INSERT, list);
		return Code.SUCCESS;
	}

	@Override
	public int updateEquipUpdate(long status, long updateTime, String equipUpdateId)  throws SQLException{
		List<Object> list = new ArrayList<Object>();
		list.add(status);
		list.add(updateTime);
		list.add(equipUpdateId);
		jdbc.update(UPDATEEQUIPUPDATE, list);
		return Code.SUCCESS;
	}

	
}
