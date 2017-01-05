package cn.bigdb.gallery.properties;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.bigdb.gallery.utils.StringUtils;


/**
 * @author liujt
 * @date   Aug 4, 2012
 */

public class CommonProperties extends  Properties {
	private static final Log logger = LogFactory.getLog(CommonProperties.class);
	
	protected static String FILE_NAME = "system.properties";	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6748079991539205360L;

	public static CommonProperties getInstance(){
		return CommonPropertiesHandler.INSTANCE;
	}
	
	private static class CommonPropertiesHandler{
		private static final CommonProperties INSTANCE = new CommonProperties(FILE_NAME);
	}
	
	public CommonProperties(String fileName){
		try {
			init(fileName);
		} catch (FileNotFoundException e) {
			logger.error("find is not file");
		} catch (IOException e) {
			logger.error("find is not file");
		}
	}
    
	protected void init(String fileName) throws FileNotFoundException, IOException{
		this.load(CommonProperties.class.getClassLoader().getResourceAsStream("/"+fileName));			
	}

	public String getProperty(String key, String defaultValue){
		String result = this.getProperty(key);
		return StringUtils.isBlank(result) ? defaultValue : result; 
	}
	
	public int getPropertyForInt(String key){
		String temp = this.getProperty(key, "0");
		Integer result = Integer.valueOf(temp);
		return result == null ? 0 : result;
	}
	
	public int getPropertyForInt(String key, int defaultValue){
		String temp = this.getProperty(key);
		return temp == null ? defaultValue : Integer.valueOf(temp);
	}
}

