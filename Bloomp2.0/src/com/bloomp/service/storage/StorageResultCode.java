package com.bloomp.service.storage;


/**
 * 存储服务的全局状态码
 * @author azhi
 *
 */
public interface StorageResultCode{

	int SUCCESS = 0;
	
	int ERROR = -1;
	//不合法的媒体文件类型
	int MEDIA_FILE_ILLEGAL = 	40004;
	//不合法的文件类型
	int FILE_ILLEGAL = 	40005;
	String FILE_ILLEGAL_MESSAGE = "不合法的文件类型";
	//不合法的图片文件大小
	int PIC_SIZE_ILLEGAL = 	40006;
	String PIC_SIZE_ILLEGAL_MESSAGE = "不合法的图片文件大小";
	//不合法的语音文件大小
	int VOICE_SIZE_ILLEGAL = 	40007;
	//不合法的视频文件大小
	int VIDEO_SIZE_ILLEGAL = 	40008;
}
