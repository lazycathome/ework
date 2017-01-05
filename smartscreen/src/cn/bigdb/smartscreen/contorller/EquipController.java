package cn.bigdb.smartscreen.contorller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.bigdb.smartscreen.annotation.JsonFilter;
import cn.bigdb.smartscreen.annotation.JsonFilters;
import cn.bigdb.smartscreen.annotation.filter.AreaFilter;
import cn.bigdb.smartscreen.annotation.filter.EquipInfoFilter;
import cn.bigdb.smartscreen.annotation.filter.HeartbeatFilter;
import cn.bigdb.smartscreen.common.Constants;
import cn.bigdb.smartscreen.common.MessageConstants;
import cn.bigdb.smartscreen.model.EquipInfo;
import cn.bigdb.smartscreen.model.Heartbeat;
import cn.bigdb.smartscreen.model.PlazaArea;
import cn.bigdb.smartscreen.service.IAreaManager;
import cn.bigdb.smartscreen.service.IEquipInfoManager;
import cn.bigdb.smartscreen.utils.Utils;
import cn.bigdb.smartscreen.vo.MessageVo;

@Controller
public class EquipController {
	@Resource (name="equipInfoManager")
	private IEquipInfoManager equipInfoManager;
	
	@Resource(name="areaManager")
	private IAreaManager areaManager;
	
	@RequestMapping(value="/equipInfo/edit",method=RequestMethod.POST)
	@ResponseBody
	public MessageVo addEquip(HttpServletRequest request,HttpServletResponse response,EquipInfo equip){
		equip.setId(Utils.getPriKeyId());
		long time = System.currentTimeMillis();
		equip.setCreateTime(time);
		equip.setUpdateTime(time);
		String result = equipInfoManager.addEquip(equip);
		MessageVo message = new MessageVo();
		if(result.equals(Constants.OP_SUCCESS)){
			message.setCode(MessageConstants.ACTION_SUCCESS_CODE);
			message.setMsg(MessageConstants.ACTION_SUCCESS_MSG);
		}else{
			message.setCode(MessageConstants.EUQIPINFO_MISSING_CODE);
			message.setMsg(MessageConstants.EUQIPINFO_MISSING_MSG);
		}
		return message;
		
	}
	@JsonFilters(values={
	 @JsonFilter(mixin=EquipInfoFilter.class, target=EquipInfo.class)  
	 ,@JsonFilter(mixin=HeartbeatFilter.class, target=Heartbeat.class)
	}) 
	@RequestMapping(value="/equipInfo/get",method=RequestMethod.GET )
	@ResponseBody
	public EquipInfo getEquip(HttpServletRequest request,HttpServletResponse response, String id){
		EquipInfo equip = equipInfoManager.getEquip(id);
		request.setAttribute("equip", equip);
		return equip;
	
	}
	
	@JsonFilters(values={
	 @JsonFilter(mixin=EquipInfoFilter.class, target=EquipInfo.class)  
	 ,@JsonFilter(mixin=HeartbeatFilter.class, target=Heartbeat.class)
	 ,@JsonFilter(mixin=AreaFilter.class, target=PlazaArea.class)
	}) 
	@RequestMapping(value="/equipInfo/list",method=RequestMethod.GET )
	@ResponseBody
	public List getEquipList(HttpServletRequest request,HttpServletResponse response){
		List<EquipInfo> equips = equipInfoManager.getAllEquip();
		return equips;
	
	}
	

	@JsonFilters(values={
		@JsonFilter(mixin=AreaFilter.class, target=PlazaArea.class)
	}) 
	@RequestMapping(value="/equipInfo/getArealist",method=RequestMethod.GET )
	@ResponseBody
	public List getAreaList(HttpServletRequest request,HttpServletResponse response){
		List<PlazaArea> areas = areaManager.getList();
		return areas;
	
	}
	
	@RequestMapping(value="/equipInfo/update",method=RequestMethod.POST )
	@ResponseBody
	public String updateEquip(HttpServletRequest request,HttpServletResponse response,EquipInfo equip){
		
		return equipInfoManager.updateEquip(equip);
	}
	
	@RequestMapping(value="/equipInfo/delete",method=RequestMethod.POST )
	@ResponseBody
	public String DelEquip(HttpServletRequest request,HttpServletResponse response,String id){
		
		return equipInfoManager.delEquip(id);
	}
	
	@JsonFilters(values={
	 @JsonFilter(mixin=EquipInfoFilter.class, target=EquipInfo.class)  
	 ,@JsonFilter(mixin=HeartbeatFilter.class, target=Heartbeat.class)
	 ,@JsonFilter(mixin=AreaFilter.class, target=PlazaArea.class)
	}) 
	@RequestMapping(value="/equipInfo/query",method=RequestMethod.POST )
	@ResponseBody
	public List<EquipInfo> queryEquipList(HttpServletRequest request,HttpServletResponse response,EquipInfo equip){
//		equip.setCode("taiyue");;
//		PlazaArea a = new PlazaArea();
//		a.setId("1");
//		equip.setArea(a);
//		equip.setName("11");
		List<EquipInfo> equips = equipInfoManager.queryEquip(equip);
		request.setAttribute("equips", equips);
		return equips;
	
	}
	
	
	public IEquipInfoManager getEquipInfoManager() {
		return equipInfoManager;
	}

	public void setEquipInfoManager(IEquipInfoManager equipInfoManager) {
		this.equipInfoManager = equipInfoManager;
	}
		
}
