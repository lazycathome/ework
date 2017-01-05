package cn.bigdb.smartscreen.test.service;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.springframework.context.ApplicationContext;

import cn.bigdb.smartscreen.model.EquipContent;
import cn.bigdb.smartscreen.model.EquipInfo;
import cn.bigdb.smartscreen.model.EquipUpdate;
import cn.bigdb.smartscreen.model.UpdateFile;
import cn.bigdb.smartscreen.service.IEquipInfoManager;
import cn.bigdb.smartscreen.test.common.LoadXmlFile;
import cn.bigdb.smartscreen.utils.Utils;

public class EquipManager {

//	@Test
//	public void add(){
//		ApplicationContext app = LoadXmlFile.load();
//		IEquipInfoManager service = (IEquipInfoManager)app.getBean("equipInfoManager");
//		
//		EquipInfo equip = new EquipInfo();
//		equip.setId(Utils.getPriKeyId());
//		equip.setCategory("1");
//		equip.setCode("xinmei");
//		equip.setCreateTime(System.currentTimeMillis());
//		
//		Set<EquipUpdate> eus = new HashSet<EquipUpdate>();
//		EquipUpdate eu = new EquipUpdate();
//		eu.setId(Utils.getPriKeyId());
//		eu.setEquip(equip);
//		eu.setUpdateTime(System.currentTimeMillis());
//		
//		UpdateFile uf = new UpdateFile();
//		uf.setId(Utils.getPriKeyId());
//		uf.setCreateTime(System.currentTimeMillis());
//		uf.setFileUrl("HTTP://");
//		uf.setEquipUpdate(eu);
//		uf.setMd5code("fff");
//		eu.setUpdateFile(uf);
//		eu.setEquip(equip);
//		eus.add(eu);
//		equip.setEquipUpdates(eus);
//		service.addEquip(equip);
//	}
//	
	@Test
	public void delete(){
		ApplicationContext app = LoadXmlFile.load();
		IEquipInfoManager service = (IEquipInfoManager)app.getBean("equipInfoManager");
		EquipInfo equip = service.getEquip("13391a8fa2e14a62a7ae6e72687f67c0");
		Set<EquipContent> ecs = equip.getEquipContents();
		if(ecs.size()==0){
			System.out.println(service.deleteById("13391a8fa2e14a62a7ae6e72687f67c0"));
		}else{
			for (EquipContent ec : ecs) {
				System.out.println("该设备正在使用！"+"----------"+ec.getContent().getId());;
			}
		}
			
		
	}
}
