package cn.bigdb.gallery.updatefile.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bigdb.gallery.updatefile.dao.UpdateFileDao;
import cn.bigdb.gallery.updatefile.entity.UpdateFile;

@Service
public class UpdateFileServiceImpl implements UpdateFileService {

	@Autowired
	private UpdateFileDao updateFileDao;
	
	@Override
	public long save(long createTime, String fileUrl,
			String md5Code) {
		UpdateFile updateFile = new UpdateFile(); 
		updateFile.setCreateTime(createTime);
		updateFile.setFileUrl(fileUrl);
		updateFile.setMd5code(md5Code);
		try {
			updateFileDao.save(updateFile);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

}
