package cn.bigdb.smartscreen.common;

import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.bigdb.smartscreen.service.IEquipInfoStatusManager;

public class StartUpServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2610400709281117031L;

	static Logger logger = null;


	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		realPath = config.getServletContext().getRealPath("/");
		initContext(config.getServletContext());
	}

	
	protected void initContext(ServletContext servlet) {
		initLog();
		initHeartbeatStatusJob();
	}
	
	private void initHeartbeatStatusJob(){
		WebApplicationContext wac = WebApplicationContextUtils
				.getRequiredWebApplicationContext(getServletContext());
		
		final IEquipInfoStatusManager statusManager = (IEquipInfoStatusManager) wac.getBean("equipInfoStatusManager");
		Timer timer = new Timer();   
        timer.schedule(new TimerTask(){
        	public void run(){
        		statusManager.startEquipInfoStatusJob();
        	}
        }, 10000, Constants.INTERVAL_TIME);
	}
	
	private void initLog(){
		 /**------------------------添加日志文件的输出开始 JuneLi add 2011-1-20------------------*/
	      //log4j文件位置 
	      String log4jFile = realPath +"WEB-INF"+ File.separator +"classes"+ File.separator + "log4j.properties"; 
	      //日志输出位置 
	      String log4jHome = realPath + "WEB-INF"+File.separator+"logs"; 
	      
	      //对日志输出位置进行set 
	      System.setProperty("log4jHome", log4jHome); 
	      //进行配置 
	      PropertyConfigurator.configure(log4jFile); 
	      logger = Logger.getLogger(StartUpServlet.class.getName());
	      logger.info("log4jFile:"+log4jFile);
	      /**------------------------添加日志文件的输出结束-------------------------------------*/
	      
	}
	
	// Process the HTTP Get request
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	  private static String realPath = "";
	  /**
	   * 获得工程部署路径
	   * @return
	   */
	  public static String getRealPath(){
		  return realPath;
	  }
}