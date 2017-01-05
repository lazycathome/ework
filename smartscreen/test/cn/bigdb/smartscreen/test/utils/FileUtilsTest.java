package cn.bigdb.smartscreen.test.utils;

import java.io.File;
import java.util.List;

import org.junit.Test;

import cn.bigdb.smartscreen.common.Constants;
import cn.bigdb.smartscreen.utils.FileUtils;
import cn.bigdb.smartscreen.utils.ZIPUtils;

public class FileUtilsTest {

	public void createFolder(){
		String path = FileUtilsTest.class.getResource("/").getPath();
		File tempF = new File(path);
		System.out.println(tempF.isDirectory());
		String contextPath = tempF.getParentFile().getParentFile().toString();
		File file = new File(contextPath+File.separator+Constants.RESOURCEFOLDER);       
		for(int i = 0; i < 10; i++){
			long time = System.currentTimeMillis();
			String folderName = file.getPath()+File.separator+time;
			File temp = new File(folderName);
			if(!temp.isDirectory()){
				temp.mkdirs();
			}
			System.out.println(time);
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	public void readLastTimeFiles(){
		FileUtils fileUtils = new FileUtils();
		List<String> result = fileUtils.getUpgradeFiles(1395036092003l);
		ZIPUtils.zip(result, "a.zip");
		System.out.println(result.size());
	}
	
	@Test
	public void getPath(){
		FileUtils fileUtils = new FileUtils();
		System.out.println(fileUtils.getPathName());
	}
}
