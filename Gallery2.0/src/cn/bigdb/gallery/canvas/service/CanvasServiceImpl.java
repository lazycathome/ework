package cn.bigdb.gallery.canvas.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bigdb.gallery.canvas.dao.CanvasDao;
import cn.bigdb.gallery.canvas.entity.Canvas;
import cn.bigdb.gallery.core.Code;

@Service
public class CanvasServiceImpl implements CanvasService {

	@Autowired
	private CanvasDao canvasDao;
	
	@Override
	public long save(Canvas canvas) {
		List<Canvas> canvasList = new ArrayList<Canvas>();
		return this.save(canvasList);
	}

	@Override
	public void update(Canvas canvas) {
		// TODO Auto-generated method stub

	}

	@Override
	public long save(List<Canvas> canvasList) {
		try {
			return canvasDao.save(canvasList);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Code.ERROR;
	}

	@Override
	public long init(long contentId) {
		try {
			List<Canvas> canvasList = new ArrayList<Canvas>(1);
			Canvas canvas = new Canvas();
			canvas.setContentId(contentId);
			canvasList.add(canvas);
			return canvasDao.save(canvasList);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Code.ERROR;
	}
	
	@Override
	public List<Canvas> getList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Canvas> getList(long contentId) {
		try {
			return canvasDao.getList(contentId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<Canvas>(0);
	}

	@Override
	public List<Canvas> getList(List<Long> contentIdList) {
		try {
			return canvasDao.getList(contentIdList);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<Canvas>(0);
	}

}
