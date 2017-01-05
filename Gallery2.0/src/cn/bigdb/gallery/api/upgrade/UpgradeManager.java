package cn.bigdb.gallery.api.upgrade;

import org.springframework.beans.factory.annotation.Autowired;

import cn.bigdb.gallery.upgrade.biz.UpgradeBiz;
import cn.bigdb.gallery.upgrade.entity.UpgradeResult;
import cn.bigdb.gallery.utils.ConvertUtils;
import cn.jedisoft.framework.annotations.GET;
import cn.jedisoft.framework.annotations.POST;
import cn.jedisoft.framework.annotations.Path;
import cn.jedisoft.framework.annotations.QueryParam;
import cn.jedisoft.framework.result.ApiResult;
import cn.jedisoft.framework.result.JsonResult;

@Path("/api/gallery/upgrade/?$")
public class UpgradeManager {

	@Autowired
	private UpgradeBiz upgradeBiz;
	
	@GET
	public ApiResult addUpgradeInfo(@QueryParam("type") String sType, @QueryParam("id") String id, 
			@QueryParam("lastQueryTime") String sLastQueryTime, @QueryParam("status") String sStatus){
		System.out.println("android :"+sType);
		long lastQueryTime = ConvertUtils.toLong(sLastQueryTime, 0);
		int type = ConvertUtils.toInt(sType, 1);
		int status = ConvertUtils.toInt(sStatus, 1);
		UpgradeResult upgrade = upgradeBiz.getUpgradeData(id, status, type, lastQueryTime);
		return new JsonResult(upgrade);
	}

	@POST
	public ApiResult upgradeAckInfo(@QueryParam("id") String id, @QueryParam("status") String status){
		System.out.println("android :"+id);
		return null;
		//return upgradeManager;
	}
	
}
