package cn.bigdb.smartscreen.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

import cn.bigdb.smartscreen.common.Constants;
import cn.bigdb.smartscreen.common.WebContext;
import cn.bigdb.smartscreen.utils.Utils;

/**
 * Servlet Filter implementation class WebContextFilte
 */
@Component("webContextFilter")
public class WebContextFilter implements Filter {

    /**
     * Default constructor. 
     */
	public void init(FilterConfig filterConfig) throws ServletException {      
        
    }  
      
    public void doFilter(ServletRequest req, ServletResponse resp,  
            FilterChain chain) throws IOException, ServletException {  
        HttpServletRequest request = (HttpServletRequest) req;  
        HttpServletResponse response = (HttpServletResponse) resp;  
        ServletContext servletContext = request.getSession().getServletContext();  
        
        if(isVaildLogin(request, response)){
        	try {
        		String contextPath  = request.getContextPath();
        		response.sendRedirect(contextPath+"/views/login.html");
     		} catch (IOException e) {
     			System.out.println("跳转登录页面失败");
     		}
        }else{
	        WebContext.create(request, response, servletContext);  
	        chain.doFilter(request, response);  
	        WebContext.clear();
        }
    }  
  
    private boolean isVaildLogin(HttpServletRequest request, HttpServletResponse response){
    	
    	String path = request.getRequestURI();
    	HttpSession session = request.getSession();
    	String account = (String)session.getAttribute(Constants.ACCOUNT_SESSION_KEY);
    	if(path.indexOf("/user/login") > -1 || path.indexOf("/login.html") > -1 || path.indexOf("/public.css") > -1 || path.indexOf("/login.css") > -1 || path.indexOf("/login-title.jpg") > -1 || path.indexOf("/login-head.jpg") > -1 || path.indexOf("/angular.min.js") > -1 ) {
    		return false;
    	}
    	
    	if(Utils.isEmpty(account)){
    		return true;
    	}
    	return false;
    	
    }
    
    @Override  
    public void destroy() {  
        // TODO Auto-generated method stub  
          
    }  

}
