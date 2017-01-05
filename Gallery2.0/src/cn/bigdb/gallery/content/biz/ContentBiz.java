package cn.bigdb.gallery.content.biz;

import java.sql.SQLException;
import java.util.List;

public interface ContentBiz {

	
	long init(String equipId, String title, String desc, int duration, int height,
			int width, long creator, List<String> picList, String... showdays) throws SQLException;
	

}
