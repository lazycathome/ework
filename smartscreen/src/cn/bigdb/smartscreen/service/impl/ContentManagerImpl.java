package cn.bigdb.smartscreen.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.jdbc.core.JdbcTemplate;

import cn.bigdb.smartscreen.common.Constants;
import cn.bigdb.smartscreen.dao.ContentDao;
import cn.bigdb.smartscreen.model.Content;
import cn.bigdb.smartscreen.model.EquipContent;
import cn.bigdb.smartscreen.service.IContentManager;
import cn.bigdb.smartscreen.utils.Utils;

public class ContentManagerImpl implements IContentManager {
	
	private ContentDao contentDao;
	
	
	public ContentDao getContentDao() {
		return contentDao;
	}

	public void setContentDao(ContentDao contentDao) {
		this.contentDao = contentDao;
	}

	@Override
	public String addContent(Content content) {
		return contentDao.addContent(content);
	}

	@Override
	public String updateContent(Content content) {
		// TODO Auto-generated method stub
		Content newContent = null;
		if(content != null ){
			String id = content.getId();
			if(!Utils.isEmpty(id)){
				Content con = getContent(id);
				content.setLastTime(System.currentTimeMillis());
				newContent = (Content) Utils.extendObject( con, content);
				if(newContent != null)
					return contentDao.updateContent(newContent);
			}
		}
		return Constants.OP_ERR;
	}

	@Override
	public String delContent(String id) {
		// TODO Auto-generated method stub
		return contentDao.delContent(id);
	}

	@Override
	public List<Content> getAllContent() {
		// TODO Auto-generated method stub
		return contentDao.getContentList();
	}

	@Override
	public List<Content> queryConent(Content content,String equipId) {
		// TODO Auto-generated method stub
		
		if((equipId == null || "".equals(equipId)) && content == null){
			return getAllContent();
		}
		String sql = null;
		Map<String,Object> params = new HashMap<String,Object>();
		StringBuffer condition = new StringBuffer();
		if(equipId != null && !"".equals(equipId)){
			
			sql = "select c from Content c ,EquipInfo e,EquipContent ec "
					+ "where c.id in (ec.content) and e.id in(ec.equip) and e.id='"+equipId+"'"
					+ " and ec in elements(c.equipContents)";
		}else{
			sql = " from Content c where 1=1 ";
		}
		if(content != null){
//			String id = content.getId();
//			if(isNotEmpty(id)){
//				List<Content> list=new ArrayList<Content>();
//				list.add(contentDao.getContent(id));
//				return list;
//			}
			String author = content.getAuthor();
			if(isNotEmpty(author)){
				condition.append(" and c.author=:author ");
				params.put("author", author);
			}
			long createTime = content.getCreateTime();
			if(createTime>0){
				condition.append(" and c.createTime>:createTime");
				params.put("createTime", createTime);
			}
			String label = content.getLabel();
			if(isNotEmpty(label)){
				condition.append(" and c.label like :label ");
				params.put("label", label);
			}
		}
		condition.append(" order by c.showlevel");
		sql = sql+condition.toString();	
		return contentDao.queryList(sql, params);
		 
	}
	
	private boolean isNotEmpty(String str){
		return str != null && !"".equals(str);
	}

	public Content getContent(String id){
		return contentDao.getContent(id);
	}

	@Override
	public List<Content> getContentByEquip(String equipId) {
		// 这里用c.*是会报错的，必须写出查询的所有字段
//		String sql = "select c.* from sm_content c,sm_equip_info e,sm_equip_content ec "
//				+ " where e.id = '"+equipId+"' and e.id = ec.equipId and ec.contentId = c.id";
		String hql = "select c from Content c ,EquipInfo e,EquipContent ec "
				+ "where c.id in (ec.content) and e.id in(ec.equip) and e.id='"+equipId+"'"
						+ " and ec in elements(c.equipContents)";
		return contentDao.queryList(hql, null);
	}

	@Override
	public String updateContent(Content content, String operate) {
		// TODO Auto-generated method stub
		if(Constants.CON_CHANGE_INFO.equals(operate))  //更新内容信息
			return updateContent(content);
		else if(Constants.CON_CHANGE_STATUS.equals(operate)){ //更新内容的播放状态，暂停或者启用
			return changeStatus(content);
		}else if(Constants.CON_FALLING.equals(operate)){  //内容展示降级
			int mainLevel = content.getShowlevel();
			int auxLevel = mainLevel-1;
			return changeLevel(content, mainLevel, auxLevel);
		}else if(Constants.CON_RISING.equals(operate)){ //内容展示升级
			int mainLevel = content.getShowlevel();
			int auxLevel = mainLevel+1;
			return changeLevel(content, mainLevel, auxLevel);
		}else
			return null; //其他操作，返回null
		
	}
	
	/**
	 * 功能：改变内容的状态，用于前台启动或者是暂停操作。
	 * @param content 内容信息，只有id和status属性值。
	 * @return 更新成功返回 success, 失败返回err
	 */
	private String changeStatus(Content content){
		Map<String, Object> param = new HashMap<String,Object>();
		String hql = null;
		long lastTime = System.currentTimeMillis();
		param.put("lastTime", lastTime);
		String id = content.getId();
		param.put("id", id);
		int status = content.getStatus();
		param.put("status", status);
		hql = "update Content c set c.status=:status,c.lastTime=:lastTime where c.id=:id";
		return contentDao.update(hql, param);
	}
	
	/**
	 * 功能：改变内容的等级。
	 * 描述：content中 id字段值存放主动改变等级内容的ID，description字段值，存放协同改变等级内容的ID。
	 * @param content 主动改变等级的内容信息 
	 * @param mainLevel int 主动更改的 等级
	 * @param auxiliaryLevel int 因为内容等级改变需要协同改变的等级
	 * @return 更新成功返回 success, 失败返回err
	 */
	private String changeLevel(Content content,int mainLevel,int auxiliaryLevel){
		Map<String, Object> param = new HashMap<String,Object>();
		String hql = "update Content c set c.showlevel=:showlevel,c.lastTime=:lastTime where c.id=:id";
		long lastTime = System.currentTimeMillis();
		param.put("lastTime", lastTime);
		String id = content.getId();
		param.put("id", id);
		param.put("showlevel", mainLevel);
		String result = contentDao.update(hql, param); //更新需要变化级别的信息。
		if(Constants.OP_SUCCESS.equals(result)){
			String auxId = content.getDescription();
			param.put("id", auxId);
			param.put("showlevel", auxiliaryLevel);
			result = contentDao.update(hql, param);//更新协同变化级别的信息
		}
		return result;
	}
}
