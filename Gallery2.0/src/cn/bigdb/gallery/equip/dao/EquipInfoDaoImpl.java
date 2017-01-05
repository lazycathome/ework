package cn.bigdb.gallery.equip.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.bigdb.gallery.core.JDBCUtils;
import cn.bigdb.gallery.core.Page;
import cn.bigdb.gallery.equip.entity.EquipInfo;
import cn.bigdb.gallery.equip.entity.EquipInfoRowMapper;

@Repository
public class EquipInfoDaoImpl implements EquipInfoDao {

	@Autowired
	private JDBCUtils<EquipInfo> jdbc;
	
	@Override
	public void save(EquipInfo equip) throws SQLException{
		String sql = "insert into equipinfo(id, code, name, plaza_id, area_id, location, width, height, category, photo, status, create_time, start_time, close_time, heartbeat_time, update_time, mobile, ip, description, memo) "
				+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		List<Object> list = new ArrayList<Object>(20);
		list.add(equip.getId());
		list.add(equip.getCode());
		list.add(equip.getName());
		list.add(equip.getPlazaId());
		list.add(equip.getAreaId());
		list.add(equip.getLocation());
		list.add(equip.getWidth());
		list.add(equip.getCategory());
		list.add(equip.getPhoto());
		list.add(equip.getStatus());
		list.add(equip.getCreateTime());
		list.add(equip.getStartTime());
		list.add(equip.getCloseTime());
		list.add(equip.getHeartbeatTime());
		list.add(equip.getUpdateTime());
		list.add(equip.getMobile());
		list.add(equip.getIp());
		list.add(equip.getDescription());
		list.add(equip.getMemo());
		jdbc.save(sql, list);
	}

	@Override
	public void update(EquipInfo equip) throws SQLException {
		
	}

	@Override
	public List<EquipInfo> getList(Page<EquipInfo> page, String keywords) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EquipInfo get(String id) throws SQLException {
		String sql = "select * from equipinfo where id=?";
		List<EquipInfo> results = jdbc.executeSql(sql, new Object[]{id}, new EquipInfoRowMapper());
		return (results == null || results.size() ==0) ? new EquipInfo() : results.get(0);
	}

}
