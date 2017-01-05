package com.bloomp;
/**
* @author liujt
* @date 2014年11月6日
* @mail liujiangtao@gaiay.cn
*/

public interface Code {
	
	int PARAM_ILLEGAL = 11999;
	String PARAM_ILLEGAL_MESSAGE = "参数错误";
	
	int USER_ILLEGAL = 11998;
	
	int SUCCESS = 0;
	String SUCCESS_MESSAGE = "成功";
	
	int ERROR = -1;
	String ERROR_MESSAGE = "失败";
	
	int NO_MORE_DATA = 11997;
	String NO_MORE_DATA_MESSAGE = "没有更多数据";
	
	int USER_NAME_EXSIST = 20001;
	String USER_NAME_EXSIST_MESSAGE = "用户名存在";
	
	int USER_NAME_NOT_EXSIST = 20002;
	String USER_NAME_NOT_EXSIST_MESSAGE = "用户名不存在";
	
	int PASSWORD_ERROR = 20003;
	String PASSWORD_ERROR_MESSAGE = "用户名或者密码错误";
	
	int FILE_ILLEGAL = 40001;
	String FILE_ILLEGAL_MESSAGE = "不合法的文件类型";
	
	int PIC_SIZE_ILLEGAL = 40002;
	String PIC_SIZE_ILLEGAL_MESSAGE = "不合法的图片文件大小";
}

