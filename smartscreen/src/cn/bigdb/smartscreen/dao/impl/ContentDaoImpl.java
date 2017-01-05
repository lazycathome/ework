package cn.bigdb.smartscreen.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import cn.bigdb.smartscreen.common.Constants;
import cn.bigdb.smartscreen.common.DAOException;
import cn.bigdb.smartscreen.dao.ContentDao;
import cn.bigdb.smartscreen.dao.HibernateDAO;
import cn.bigdb.smartscreen.model.Content;

public class ContentDaoImpl implements ContentDao {

	private HibernateDAO hdao;
	private static Logger logger = Logger.getLogger(ContentDaoImpl.class);
	
	public HibernateDAO getHdao() {
		return hdao;
	}

	public void setHdao(HibernateDAO hdao) {
		this.hdao = hdao;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Content> getContentList() {
		List<Content> result = null;
		try {
			result = (List<Content>) hdao.getList("from Content content");
		} catch (DAOException e) {
			logger.error("获取内容列表异常", e);
		}
		return result;
	}

	@Override
	public String addContent(Content content) {
		try {
			hdao.save(content);
		} catch (DAOException e) {
			logger.error("添加内容失败");
			return Constants.OP_ERR;
		}
		return Constants.OP_SUCCESS;
	}

	@Override
	public String updateContent(Content content) {
		try {
			hdao.update(content);
		} catch (DAOException e) {
			logger.error("更新内容失败，id："+content.getId());
			return Constants.OP_ERR;
		}
		return Constants.OP_SUCCESS;
	}

	@Override
	public Content getContent(String id) {
		try {
			Content content = (Content) hdao.get(Content.class, id);
			return content;
		} catch (DAOException e) {
			logger.info(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public List<Content> queryList(String sql,Map<String, Object> param) {
		try {
			@SuppressWarnings("unchecked")
			List<Content> list = (List<Content>) hdao.getList(sql, param);
			return list;
		} catch (DAOException e) {
			logger.info(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public String delContent(String id) {
		try {
			//hdao.delete("from Content content where congtent.id="+id);
			hdao.delete(Content.class, id);
			return Constants.OP_SUCCESS;
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			logger.info("删除内容失败");
			logger.info(e.getMessage(), e);
			return Constants.OP_ERR;
		}
	}

	@Override
	public String update(String hql, Map<String, Object> param) {
		// TODO Auto-generated method stub
		try {
			hdao.update(hql, param);
			return Constants.OP_SUCCESS;
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			logger.info("更新内容失败");
			logger.info(e.getMessage(), e);
			return Constants.OP_ERR;
		}
		
	}

}
