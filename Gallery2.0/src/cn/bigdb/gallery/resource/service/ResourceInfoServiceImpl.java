package cn.bigdb.gallery.resource.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bigdb.gallery.core.Code;
import cn.bigdb.gallery.resource.dao.ResourceInfoCanvasDao;
import cn.bigdb.gallery.resource.dao.ResourceInfoDao;
import cn.bigdb.gallery.resource.entity.ResourceInfo;
import cn.bigdb.gallery.resource.entity.ResourceInfoCanvas;
import cn.bigdb.gallery.resource.entity.ResourceState;
import cn.bigdb.gallery.resource.entity.ResourceType;

@Service
public class ResourceInfoServiceImpl implements ResourceInfoService {

	@Autowired
	private ResourceInfoDao resourceInfoDao;

	@Autowired
	private ResourceInfoCanvasDao resourceInfoCanvasDao;
	
	@Override
	public long save(List<ResourceInfo> resourceInfoList, long canvasId) {
		if(resourceInfoList == null || resourceInfoList.size() == 0) {
			return Code.ERROR;
		}
		try {
			
			long maxId = resourceInfoDao.save(resourceInfoList);
			List<ResourceInfoCanvas> rCanvasList = new ArrayList<ResourceInfoCanvas>(resourceInfoList.size());
			long time = System.currentTimeMillis();
			for(int i = resourceInfoList.size(); i > 0; i--){
				ResourceInfoCanvas rCanvas = new ResourceInfoCanvas();
				long id = maxId - i;
				rCanvas.setCanvasId(canvasId);
				rCanvas.setResourceInfoId(id);
				rCanvas.setCreateTime(time);
				rCanvasList.add(rCanvas);
			}
			resourceInfoCanvasDao.save(rCanvasList);
			return Code.SUCCESS;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Code.ERROR;
	}

	@Override
	public List<ResourceInfo> getList(List<Long> rCanvasIdList) {
		if(rCanvasIdList != null && rCanvasIdList.size() >= 1){
		
			try {
				List<ResourceInfoCanvas> rCanvasList = resourceInfoCanvasDao.get(rCanvasIdList);
				List<Long> rIdList = new ArrayList<Long>(rCanvasList.size());
				Map<Long, Long> rCanvasMap = new HashMap<Long, Long>();
				for(int  i = 0; i < rCanvasList.size(); i++){
					ResourceInfoCanvas rCanvas = rCanvasList.get(i);
					rIdList.add(rCanvas.getResourceInfoId());
					rCanvasMap.put(rCanvas.getResourceInfoId(), rCanvas.getCanvasId());
				}
				List<ResourceInfo> resourceInfoList = resourceInfoDao.getList(rIdList);
				for(int i = 0; i < resourceInfoList.size(); i++){
					ResourceInfo res = resourceInfoList.get(i);
					res.setCanvasId(rCanvasMap.get(res.getId()));;
				}
				return resourceInfoList;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return new ArrayList<ResourceInfo>(0);
	}

	@Override
	public void update(ResourceInfo resourceInfo) {
		if(resourceInfo != null && resourceInfo.getId() >= 1){
			try {
				resourceInfoDao.update(resourceInfo);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public ResourceInfo get(long id) {
		try {
			return resourceInfoDao.get(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResourceInfo();
	}

	@Override
	public void delete(long id, int state) {
		try {
			resourceInfoDao.delete(id, state);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public long init(List<String> picList, long canvasId) {
		List<ResourceInfo> rList = new ArrayList<ResourceInfo>(picList.size());
		long time = System.currentTimeMillis();
		for (int i = 0; i < picList.size(); i++) {
			ResourceInfo resourceInfo = new ResourceInfo();
			resourceInfo.setCreateTime(time);
			resourceInfo.setSort(i);
			resourceInfo.setType(ResourceType.PIC.getType());
			resourceInfo.setState(ResourceState.ONLINE.getState());
			resourceInfo.setContent(picList.get(i));
			resourceInfo.setName("page"+i);
			rList.add(resourceInfo);
		}
		try {
			long resId = resourceInfoDao.save(rList);
			List<ResourceInfoCanvas> rCanvasList = new ArrayList<ResourceInfoCanvas>(picList.size());
			for(int i = picList.size(); i > 0; i--){
				long resourceInfoId = resId - i;
				ResourceInfoCanvas rCanvas = new ResourceInfoCanvas();
				rCanvas.setCanvasId(canvasId);
				rCanvas.setCreateTime(time);
				rCanvas.setResourceInfoId(resourceInfoId);
				rCanvasList.add(rCanvas);
			}
			resourceInfoCanvasDao.save(rCanvasList);
			return resId;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Code.ERROR;
	}

	@Override
	public int update(List<ResourceInfo> sortList) {
		try {
			return resourceInfoDao.updateSort(sortList);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Code.ERROR;
	}

	@Override
	public List<String> getResourceFiles(List<Long> resIdList, long lastQueryTime) {
		try {
			List<ResourceInfo> datas = resourceInfoDao.getResourceFiles(resIdList, lastQueryTime);
			List<String> results = new ArrayList<String>(datas.size());
			for(int i = 0; i < datas.size(); i++){
				ResourceInfo res = datas.get(i);
				results.add(res.getContent());
			}
			return results;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ArrayList<String>(0);
	}

}
