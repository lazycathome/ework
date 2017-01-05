package cn.bigdb.smartscreen.test.contorller;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import cn.bigdb.smartscreen.test.common.JUnitActionBase;


public class ContentContorller extends JUnitActionBase {

	
	public void testGetList() throws Exception {  
	    MockHttpServletRequest request = new MockHttpServletRequest();  
	        MockHttpServletResponse response = new MockHttpServletResponse(); 
	        request.setLocalPort(8080);//该选项可不设置 
//		        request.setServletPath("/strategy/page");//另一种设置url的方式 
	        request.setRequestURI("/content/list");
	        request.setMethod("GET");
	        this.excuteActionWithJson(request, response);
	        System.out.println(response.getContentAsString());
	        
//	        request.addParameter("pageNo", "2");  
//	        request.addParameter("pagesize", "20"); 
//	        request.setAttribute("msg", "测试action成功"); 
	        // 执行URI对应的action  
//	        final ModelAndView mav = this.excuteAction(request, response);  
	       
	        // Assert logic  
//	        Assert.assertEquals("views/strategy/page", mav.getViewName()); 
	        
//		    String msg=(String)response.getAttribute("message");  
//	        Object msg=mav.getModel().get("param");  
//	        System.out.println(msg);  
//	        String msg2=(String)request.getAttribute("msg"); 
//	        System.out.println(msg2); 
	    } 
	
	@Test
	public void testQueryList() throws Exception {  
	    MockHttpServletRequest request = new MockHttpServletRequest();  
	        MockHttpServletResponse response = new MockHttpServletResponse(); 
	        request.setLocalPort(8080);//该选项可不设置 
	        request.setRequestURI("/content/query");
	        request.setMethod("POST"); 
	        request.addParameter("equipId", "24f716e548d24725ab3665d3e446e039");
	        this.excuteActionWithJson(request, response);
	        System.out.println(response.getContentAsString());
	}
	
	
	public void testEdit() throws Exception {  
	    MockHttpServletRequest request = new MockHttpServletRequest();  
        MockHttpServletResponse response = new MockHttpServletResponse(); 
        request.setLocalPort(8080);//该选项可不设置 
        request.setRequestURI("/content/edit");
        request.setMethod("POST"); 
        request.addParameter("id", "4f98a3986a9a4ca98288e4e86a42fbd8");
        request.addParameter("showlevel", "2");
        request.addParameter("operate", "changeShowlevel");
        this.excuteActionWithJson(request, response);
        System.out.println(response.getContentAsString());
	}

	
}
