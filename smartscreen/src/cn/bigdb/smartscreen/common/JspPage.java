/**
 * <p>Description: 分页类</p>
 * <p>Company: 北京东华合创数码科技股份有限公司</p>
 * @author 王福民
 * @project 东华网管软件
 * @date 2005-2-23
 */

package cn.bigdb.smartscreen.common;

 
public class JspPage
{
	/**
	 *  每页允许记录数
	 */
   private int perPage = Constants.INIT_PERPAGE; 
   /**
    * 总记录数
    */
   private int totalRecord; 
   /**
    * 总页数
    */
   private int totalPage;
   /**
    * 当前页数
    */
   private int currentPage = 1;
    
   
   /**
    * 创建分页参数对象
    * @param perPage 每页允许记录数 默认为15行，perPage为负数时使用默认值
    * @param currentPage 当前显示第几页
    */
   public JspPage(int perPage,int currentPage){
	  if(perPage<1){
		  this.perPage = Constants.INIT_PERPAGE;
	  }else{
		  this.perPage = perPage;
	  }
	  if(currentPage<=0){
		  this.currentPage = 1;
	  }else {
		  this.currentPage = currentPage;
	  }
	  
   }
   public JspPage(){
	   this.perPage = Constants.INIT_PERPAGE;
	   this.currentPage = Constants.INIT_CURRENTPAGE;
	   
   }
  

public int getPerPage() {
	return perPage;
}


public void setPerPage(int perPage) {
	this.perPage = perPage;
}


public int getTotalRecord() {
	return totalRecord;
}


public void setTotalRecord(int totalRecord) {
	this.totalRecord = totalRecord;
}


public int getTotalPage() {
	return totalPage;
}


public void setTotalPage(int totalPage) {
	this.totalPage = totalPage;
}

public int getCurrentPage() {
	return currentPage;
}
public void setCurrentPage(int currentPage) {
	this.currentPage = currentPage;
}

 
}
