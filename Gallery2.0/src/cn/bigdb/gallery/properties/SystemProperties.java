package cn.bigdb.gallery.properties;



/**
 * 
 * @author LJT
 * @date   Jan 22, 2014
 */

public class SystemProperties extends CommonProperties {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6504143991125955059L;

	protected final static String FILE_NAME = "system.properties";	


	public static SystemProperties getInstance(){
		return SystemPropertiesHandler.INSTANCE;
	}
	
	private static class SystemPropertiesHandler{
		private static final SystemProperties INSTANCE = new SystemProperties(FILE_NAME);
	}
	
	public SystemProperties(String fileName) {
		super(fileName);
		// TODO Auto-generated constructor stub
	}
	
}
