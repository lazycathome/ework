package cn.bigdb.smartscreen.test.common;

import java.io.File;
import java.io.FilenameFilter;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * @author liujt 2013-12-23 15:43:41
 * 加载spring文件生成spring容器,用于没有启动应用服务器的测试
 */
public class LoadXmlFile {
	/**
	 * 
	 * @param String: path 你的项目所在路径,该路径是绝对路径,例: C:/shenzou/UltraSA/
	 * @author liujt 2013-12-23 15:48:34
	 * @return ApplicationContext spring容器
	 * @throws Exception
	 */
	public static ApplicationContext load(){

			String saPath = getConfPath();
			System.out.println("你的spring配置文件所在位置: "+saPath);
			File saFile = new File(saPath);
			String[] saIst = saFile.list(new FilenameFilter(){
				public boolean accept(File dir, String name) {
					if(name.startsWith("applicationContext") && name.endsWith("xml")){
						return true;
					}else{
						return false;
					}
				}
			});
			for(int i=0;i<saIst.length;i++){
				saIst[i]=saPath+saIst[i];
				System.out.println(saIst[i]);
			}

			FileSystemXmlApplicationContext appContext = new FileSystemXmlApplicationContext(saIst);

			return appContext;
		
	}

	public static String getConfPath()
	{
		
		return "WebContent"+File.separator+"WEB-INF"+File.separator+"classes"+File.separator+"spring"+File.separator;
	}

}
