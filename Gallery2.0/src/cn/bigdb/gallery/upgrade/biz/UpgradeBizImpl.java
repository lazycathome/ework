package cn.bigdb.gallery.upgrade.biz;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bigdb.gallery.canvas.entity.Canvas;
import cn.bigdb.gallery.canvas.service.CanvasService;
import cn.bigdb.gallery.common.WebContext;
import cn.bigdb.gallery.content.entity.Content;
import cn.bigdb.gallery.content.service.ContentService;
import cn.bigdb.gallery.core.Code;
import cn.bigdb.gallery.core.JSONUtils;
import cn.bigdb.gallery.equip.entity.EquipInfo;
import cn.bigdb.gallery.equip.service.EquipInfoService;
import cn.bigdb.gallery.properties.SystemProperties;
import cn.bigdb.gallery.resource.entity.ResourceInfo;
import cn.bigdb.gallery.resource.service.ResourceInfoService;
import cn.bigdb.gallery.updatefile.dao.EquipUpdateDao;
import cn.bigdb.gallery.updatefile.entity.EquipUpdate;
import cn.bigdb.gallery.updatefile.service.UpdateFileService;
import cn.bigdb.gallery.upgrade.entity.UpgradeResult;
import cn.bigdb.gallery.utils.FileUtils;
import cn.bigdb.gallery.utils.MD5Utils;
import cn.bigdb.gallery.utils.ZIPUtils;

@Service
public class UpgradeBizImpl implements UpgradeBiz {

	@Autowired
	private UpdateFileService updateFileService;
	
	@Autowired
	private EquipUpdateDao equipUpdateDao;
	
	@Autowired
	private EquipInfoService equipInfoService;
	
	@Autowired
	private ContentService contentService;
	
	@Autowired
	private CanvasService canvasService;
	
	@Autowired
	private ResourceInfoService resourceInfoService;
	
	
	@Override
	public UpgradeResult getUpgradeData(String equipId, int status, int type, long lastQueryTime){
		long time = System.currentTimeMillis();
		UpgradeResult upgrade = new UpgradeResult();
		EquipInfo equipInfo = equipInfoService.get(equipId);
		if(equipInfo.isEmpty()){
			return null;
		}
		List<Content> contentList = contentService.getList(equipId);
		List<String> files = null;
		FileUtils fileUtils = new FileUtils();
		if(contentList.size() >= 1 ){
			equipInfo.setData(contentList);
			List<Long> contentIdList = new ArrayList<Long>(contentList.size());
			for(int i = 0; i < contentList.size(); i++){
				Content content = contentList.get(i);
				contentIdList.add(content.getId());
			}
			List<Canvas> canvasList = canvasService.getList(contentIdList);
			
			if(canvasList.size() >= 1 ){
				List<Long> canvasIdList = new ArrayList<Long>(canvasList.size());
				Map<Long, List<Canvas>> canvasMap = new HashMap<Long, List<Canvas>>();
				List<Canvas> tCanvasList = new ArrayList<Canvas>();
				for(int i = 0; i < canvasList.size(); i++){
					Canvas canvas = canvasList.get(i);
					canvasIdList.add(canvas.getId());
					if(canvasMap.get(canvas.getContentId()) == null){
						tCanvasList = new ArrayList<Canvas>();
						canvasMap.put(canvas.getContentId(), tCanvasList);
					}
					tCanvasList.add(canvas);
				}
				
				for(int i = 0; i < contentList.size(); i++){
					Content content = contentList.get(i);
					content.setMaindata(canvasMap.get(content.getId()));
				}
				
				List<ResourceInfo> resourceInfoList = resourceInfoService.getList(canvasIdList);
				Map<Long, List<ResourceInfo>> resourceMap = new HashMap<Long, List<ResourceInfo>>();
				List<ResourceInfo> tResList = null;
				List<Long> resIdList = new ArrayList<Long>(resourceInfoList.size());
				if(resourceInfoList.size() >= 1 ){
					for(int i = 0; i < canvasList.size(); i++){
						ResourceInfo resourceInfo = resourceInfoList.get(i);
						if(resourceMap.get(resourceInfo.getCanvasId()) == null){
							tResList = new ArrayList<ResourceInfo>();
							resourceMap.put(resourceInfo.getCanvasId(), tResList);
						}
						resIdList.add(resourceInfo.getId());
						tResList.add(resourceInfo);
					}
					files = fileUtils.spliceResourceFilePath(resourceInfoService.getResourceFiles(resIdList, lastQueryTime), lastQueryTime);
				}
				for(int i = 0; i < canvasList.size(); i++){
					Canvas canvas = canvasList.get(i);
					canvas.setProps(resourceMap.get(canvas.getId()));
				}
			}
		}
	
		String resourceDataFileName = "";
		if(!equipInfo.isEmpty()){
			//使用jsonData数据生成json文件
			resourceDataFileName = fileUtils.createJsonFile(equipId, JSONUtils.toJSON(equipInfo));
//			if(Utils.isEmpty(resourceDataFileName)) return tResult;
		}else{
			return upgrade;
		}
		if(files == null){
			files = new ArrayList<String>(1);
		}
		files.add(resourceDataFileName);
		String hosts = SystemProperties.getInstance().getProperty("gallery.img.host");
		String zipFile = "download"+File.separator+time+".zip";
		boolean flag = ZIPUtils.zip(files, WebContext.getRealPath()+zipFile);
		zipFile = hosts+zipFile.replaceAll("\\\\", "/");
		if(flag){
			String md5Code = MD5Utils.getMD5String(zipFile);
			long id = addEquipUpdate(equipId, zipFile, time, md5Code);
			if(id >= Code.ERROR){
				upgrade.setEquipId(equipId);
				upgrade.setMd5Code(md5Code);
				upgrade.setLastQueryTime(time);
				
				upgrade.setUrl(zipFile);
//				tResult = Utils.object2Json(upgrade);
			}
		}
		return upgrade;
	}
	
	private long addEquipUpdate(String equipId, String zipFile, long time, String md5Code){
		long updateFileId = updateFileService.save(time, zipFile, md5Code);
		
		EquipUpdate equipUpdate = new EquipUpdate();
		equipUpdate.setEquipId(equipId);
		equipUpdate.setStatus(-1);
		equipUpdate.setUpdateTime(time);
		equipUpdate.setUpdateFileId(updateFileId);
		
		try {
			return equipUpdateDao.addEquipUpdate(equipUpdate);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Code.ERROR;
	}
}
