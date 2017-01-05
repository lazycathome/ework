package cn.bigdb.smartscreen.test.service;

import org.junit.Test;
import org.springframework.context.ApplicationContext;

import cn.bigdb.smartscreen.model.EquipInfo;
import cn.bigdb.smartscreen.model.EquipUpdate;
import cn.bigdb.smartscreen.model.UpdateFile;
import cn.bigdb.smartscreen.service.IEquipUpdateManager;
import cn.bigdb.smartscreen.test.common.LoadXmlFile;
import cn.bigdb.smartscreen.utils.MD5Utils;
import cn.bigdb.smartscreen.utils.Utils;

public class EquipUpdateManagerTest {

	@Test
	public void add(){
		String equipId = "57eff6df027c4c7d8253e005bfda8a45";
		String zipFile="a.zip";
		
		ApplicationContext app = LoadXmlFile.load();
		IEquipUpdateManager service = (IEquipUpdateManager)app.getBean("equipUpdateManager");
		
		long time = System.currentTimeMillis();
		EquipUpdate equipUpdate = new EquipUpdate();
		EquipInfo equip = new EquipInfo();
		UpdateFile updateFile = new UpdateFile();
		equip.setId(equipId);
		equipUpdate.setId(Utils.getPriKeyId());
		equipUpdate.setEquip(equip);
		equipUpdate.setStatus(-1);
		equipUpdate.setUpdateTime(time);
		equipUpdate.setUpdateFile(updateFile);
		updateFile.setCreateTime(time);
		updateFile.setEquipUpdate(equipUpdate);
		updateFile.setFileUrl(zipFile);
		String code = MD5Utils.getMD5String(zipFile);
		updateFile.setMd5code(code);
		updateFile.setId(Utils.getPriKeyId());
		service.addEquipUpdate(equipUpdate);
	}
}
