package cn.bigdb.gallery.common;


/**
 * <p>
 * IASE系统常量定义接口。
 * </p>
 * 用来存放IASE所使用的常量，IASE中共用的常量均定义到该类中。
 * 
 * @author $Author: liujt $
 * @version $Revision: 1.21 $ $Date: 2014/02/17 02:47:35 $
 * 
 */
public interface Constants {
	
	//心跳时间
	int HEARTBEAT_TIME = 300000;
	
	//同步间隔时间
	int UPGRADE_TIME = 1800000;
	
	//状态统计间隔时间，单位s
	int INTERVAL_TIME = 350;
	
	//超时时间
	int TIME_OUT = 400000;
	
	//每个线程统计的设备总数
	int EQUIP_TOTAL = 50;
	
	String ACCOUNT_SESSION_KEY = "account_session_id";
	
	/**
	 * 画廊项目的秘钥
	 */
	String SECRET_KEY = "gallery";

	/** 删除、保存、更新失败 */
	String OP_ERR = "err";
	/** 删除操作存在关联关系导致删除失败 */
	String OP_FAIL = "fail";
	/** 删除、保存、更新成功 */
	String OP_SUCCESS = "success";

	/**
	 * 数据分页默认一页显示记录数
	 */
	int INIT_PERPAGE = 15;
	/**
	 * 数据分页默认当前显示页
	 */
	int INIT_CURRENTPAGE = 1;
	
	/**
	 * 系统文件更新
	 */
	String UPGRADE_MANAGER = "upgradeManager";
	
	/**
	 * 资源文件更新
	 */
	String UPGRADE_RESOURCE = "upgradeResource";
	
	/**
	 * 存储需要更新到终端设备的资源文件目录名称
	 */
	String RESOURCEFOLDER = "res";
	
	/**
	 * 存储终端设备图片的文件目录名称
	 */
	String EQUIPIMG = "equipimg";
	
	String UPGRADE_TYPE_RES = "res";
	
	String UPGRADE_TYPE_SYSTEM = "system";
	
	/**
	 * 对于内容更新的操作名称
	 */
	String CON_CHANGE_STATUS = "changeStatus";
	String CON_RISING = "rising";//内容展示升级
	String CON_FALLING = "falling";//内容展示降级
	String CON_CHANGE_INFO = "changeInfo";
	
	//设备照片
	String UPLOAD_TYPE_EQUIP_PIC = "equipPic";
	
	//内容背景图
	String UPLOAD_TYPE_PIC = "pic";
	
	//视频
	String UPLOAD_TYPE_VIDEO = "video";
	
	//背景音乐
	String UPLOAD_TYPE_AUDIO = "audio";
	
	
}
