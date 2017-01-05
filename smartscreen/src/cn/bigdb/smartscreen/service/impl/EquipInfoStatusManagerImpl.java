package cn.bigdb.smartscreen.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import cn.bigdb.smartscreen.common.Constants;
import cn.bigdb.smartscreen.service.IEquipInfoStatusManager;

public class EquipInfoStatusManagerImpl  implements IEquipInfoStatusManager{

	private JdbcTemplate jdbcTemplate;
	
	private String queryAllEquipInfo = "select id from sm_equip_info";
	private String queryHeartbeatByEquipId = "select lastTime from sm_heartbeat where equipid=? order by lastTime desc limit 1";
	private String updateEquipStatusById = "update sm_equip_info set status=? where id=? ";
	
	private static Logger logger = Logger.getLogger(EquipInfoStatusManagerImpl.class);
	
	@Override
	public void startEquipInfoStatusJob() {
		logger.info("设备状态统计开始");
		List<Map<String, Object>> equipData = this.queryAllEquipInfo();
		int length = equipData.size();
		int size = length / Constants.EQUIP_TOTAL;
		if(length % Constants.EQUIP_TOTAL != 0) size += 1;
		for(int i = 0; i < size; i++){
			int end = (i+1)*Constants.EQUIP_TOTAL;
			end = end > length ? length : end;
			final List<Map<String, Object>> temp = equipData.subList(i*Constants.EQUIP_TOTAL, end);
			new Thread(){
				public void run(){
					for(Map<String, Object> map : temp){
						String equipId = map.get("id").toString();
						long lastTime = getHeartbeatLastTime(equipId);
						long nowTime = System.currentTimeMillis();
						int status = 1;
						if((nowTime - lastTime) > Constants.TIME_OUT ){
							status = 0;
						}
						updateEquipStatus(equipId, status);
					}
				}
			}.start();
		}
		equipData = null;
		logger.info("设备状态统计线程启动完毕");
	}
	
	private List<Map<String, Object>> queryAllEquipInfo(){
		return  jdbcTemplate.queryForList(queryAllEquipInfo);
	}
	
	private int updateEquipStatus(String equipId, int status){
		Object[] o = new Object[2];
		o[0] = status;
		o[1] = equipId;
		return jdbcTemplate.update(updateEquipStatusById, o);
	}

	private long getHeartbeatLastTime(String equipId){
		List<Object> vList = new ArrayList<Object>();
		vList.add(equipId);
		List<Map<String, Object>> result = jdbcTemplate.queryForList(queryHeartbeatByEquipId, this.getParams(vList));
		if(result ==null || result.isEmpty()) return 0;
		Map<String, Object> map = result.get(0);
		return (Long)map.get("lasttime");
	}
	
	public Object[] getParams(List<Object> list) {
		int vLen = list.size();
		Object[] o = new Object[vLen];
		for(int i = 0; i < vLen; i++){
			o[i] = list.get(i);
		}
		return o;
	}
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	
	
}
