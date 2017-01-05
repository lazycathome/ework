/**
 * 
 */
package cn.bigdb.smartscreen.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * 
 * <strong>Title : Utils<br>
 * </strong> <strong>Description : </strong><br>
 * 提供一些工具方法
 *  <br><strong>Create on : 2014-3-17<br>
 * </strong>
 * 
 * @author liujt  liujt2009@gmail.com<br>
 * @version <strong>IASE v1.0</strong><br>
 *          <br>
 *          <strong>修改历史:</strong><br>
 *          修改人 修改日期 修改描述<br>
 *          -------------------------------------------<br>
 *          <br>
 *          <br>
 */
public class Utils {
	
	private static Log logger = LogFactory.getLog(Utils.class);
	/**
	 * 克隆，深度克隆，复制一个完全一样的对象. 
	 * @param obj 被克隆的对象
	 * @return 克隆后的对象
	 */
	public static final Object deepClone(Object obj){
		if(logger.isDebugEnabled()){
			logger.debug("开始深度克隆对象");
		}
		ByteArrayOutputStream bo = null;
		ObjectOutputStream oo = null;
		ByteArrayInputStream bi = null;
		ObjectInputStream oi = null;
		Object resultObject = null;
		try {
			//将对象写到流里 
			if(logger.isDebugEnabled()){
				logger.debug("将对象写入二进制流");
			}
			bo=new ByteArrayOutputStream(); 
			oo=new ObjectOutputStream(bo);
			oo.writeObject(obj);
			//从流里读出来 
			if(logger.isDebugEnabled()){
				logger.debug("在二进制流中将对象读出");
			}
			bi=new ByteArrayInputStream(bo.toByteArray()); 
			oi=new ObjectInputStream(bi); 
			
			resultObject = oi.readObject(); 

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			logger.fatal("没有找到对应的类");
		} finally{
			if(logger.isDebugEnabled()){
				logger.debug("关闭流对象");
			}
			try {
				if(oi != null){
						oi.close();
					oi = null;
				}
				if(bi != null){
					bi.close();
					bi = null;
				}
				if(oo != null){
					oo.close();
					oo = null;
				}
				if(bo != null){
					bo.close();
					bo = null;
				}
			} catch (IOException e) {
				logger.error("",e);
			}
		}
		return resultObject;
	}
	/**
	 * 将代理对象转换成不是由代理产生的对象,要求代理的对象，也必须是代理的clazz的对象.
	 * @param clazz 需要转换的代理对象的类
	 * @param proxy 代理对象
	 * @return 一个真实的对象
	 */
	public static <T> T proxy2Original(Class<T> clazz,Object proxy){
		String setMethodNamePre = "set";
		String getMethodNamePre = "get";
		T result = null;
		try {
			Method[] proxyMethod = proxy.getClass().getMethods();
			result = clazz.newInstance();
			Method[] methods = clazz.getMethods();
			for(int i=0;i<methods.length;i++){
				String name = methods[i].getName();
				if(name.startsWith(setMethodNamePre)){
					String fieldName = name.substring(setMethodNamePre.length());
					//需要先判断是否存在get方法
					String getMethosName = getMethodNamePre+fieldName;
					Method getMethod = null;
					for(int j=0;j<proxyMethod.length;j++){
						if(proxyMethod[j].getName().equals(getMethosName)){
							getMethod = proxyMethod[j];
						}
					}
					if(getMethod!=null){//当存在对应的get方法时，调用代理对象的get方法，否则忽略掉
						//调用代理对象的get方法
						Object srcObj = getMethod.invoke(proxy, new Object[]{});
						//调用新产生的对象的 set方法
						methods[i].invoke(result, srcObj);
					}
					
				}
			}
		} catch (InstantiationException e) {
			logger.error("",e);
		} catch (IllegalAccessException e) {
			logger.error("",e);
		} catch (SecurityException e) {
			logger.error("",e);
		} catch (IllegalArgumentException e) {
			logger.error("",e);
		} catch (InvocationTargetException e) {
			logger.error("",e);
		}
		return result;
	}
	/**
	 * 根据class类型，将str转换成对象.
	 * 时间格式支持三种:long、"yyyy-MM-dd"、"yyyy-MM-dd HH:mm:ss"
	 * @param clz 需要转换的类对象
	 * @param str 需要转换的字符串
	 * @return 转换后的对象
	 */
	public static final Object parseObject(Class<?> clz,String str){
		if(clz.equals(int.class)){
			logger.debug("返回值类型为int类型");
			return Integer.parseInt(str);
		}
		if(clz.equals(Integer.class)){
			logger.debug("返回值类型为Integer类型");
			return new Integer(str);
		}
		if(clz.equals(String.class)){
			logger.debug("返回值类型为String类型");
			return str;
		}
		if(clz.equals(Double.class)){
			logger.debug("返回值类型为Double类型");
			return new Double(str);
		}
		if(clz.equals(double.class)){
			logger.debug("返回值类型为double类型");
			return Double.parseDouble(str);
		}
		if(clz.equals(Float.class)){
			logger.debug("返回值类型为Float类型");
			return new Float(str);
		}
		if(clz.equals(float.class)){
			logger.debug("返回值类型为float类型");
			return Float.parseFloat(str);
		}
		if(clz.equals(Boolean.class)){
			logger.debug("返回值类型为Boolean类型");
			return new Boolean(str);
		}
		if(clz.equals(boolean.class)){
			logger.debug("返回值类型为boolean类型");
			return Boolean.parseBoolean(str);
		}
		if(clz.equals(byte.class)){
			logger.debug("返回对象的类型为byte类型");
			return Byte.parseByte(str);
		}
		if(clz.equals(Byte.class)){
			logger.debug("返回的数据类型为Byte类型");
			return new Byte(str);
		}
		if(clz.equals(short.class)){
			logger.debug("返回的数据类型为short类型");
			return Short.parseShort(str);
		}
		if(clz.equals(Short.class)){
			logger.debug("返回值类型为Short类型");
			return new Short(str);
		}
		if(clz.equals(char.class)){
			if(str.length() == 0){
				throw new RuntimeException("字符串长度为0，没有任何字符串");
			}else if(str.length() > 1){
				throw new RuntimeException("字符串长度超过一个字符，不能转换成字符串");
			}
			return (char) str.indexOf(0);
		}
		if(clz.equals(Character.class)){
			if(str.length() == 0){
				throw new RuntimeException("字符串长度为0，没有任何字符串");
			}else if(str.length() > 1){
				throw new RuntimeException("字符串长度超过一个字符，不能转换成字符串");
			}
			return new Character((char) str.indexOf(0));
		}
		if(clz.equals(java.util.Date.class)){
			String numberFormat = "\\d+";
			String nmdFormat = "\\d{4}\\-\\d{2}\\-\\d{2}";
			String nydhmsFormat = "\\d{4}\\-\\d{2}\\-\\d{2} \\d{2}:\\d{2}:\\d{2}";
			if(Pattern.compile(numberFormat).matcher(str).matches()){
				logger.debug("输入的日期格式为长整型");
				return new Date(Long.parseLong(str));
			}
			if(Pattern.compile(nmdFormat).matcher(str).matches()){
				logger.debug("日期格式为yyyy-MM-dd");
				try {
					return new SimpleDateFormat("yyyy-MM-dd").parse(str);
				} catch (ParseException e) {
					throw new RuntimeException("日期格式转换错误，可以转换的字符串格式为  long类型的、yyyy-MM-dd、 yyyy-MM-dd HH:mm:ss",e);
				}
			}
			if(Pattern.compile(nydhmsFormat).matcher(str).matches()){
				logger.debug("日期格式为yyyy-MM-dd HH:mm:ss");
				try {
					return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str);
				} catch (ParseException e) {
					throw new RuntimeException("日期格式转换错误，可以转换的字符串格式为  long类型的、yyyy-MM-dd、 yyyy-MM-dd HH:mm:ss",e);
				}
			}
			throw new RuntimeException("没有找到符合的时间格式,时间格式可以为：long类型的、yyyy-MM-dd、 yyyy-MM-dd HH:mm:ss");
		}
		try {
			return clz.newInstance();
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * 将字符串描述的类型转换成class对象.
	 * @param clazzType 字符串描述的类型<br>
	 *          比如：<tt>java.lang.String</tt>
	 * @return  对应的class对象
	 */
	public static final Class<?> parseClassType(String clazzType){
		logger.debug("传入的参数:"+clazzType);
		if(clazzType == null){
			logger.debug("传入的参数为null，不能返回对应的类对象");
			throw new RuntimeException("传入的类型为null");
		}
		if(clazzType.equals("int")){
			logger.debug("返回int 类型");
			return int.class;
		}
		if(clazzType.equals("float")){
			logger.debug("返回float 类型");
			return float.class;
		}
		if(clazzType.equals("double")){
			logger.debug("返回double 类型");
			return double.class;
		}
		if(clazzType.equals("byte")){
			logger.debug("返回byte 类型");
			return byte.class;
		}
		if(clazzType.equals("boolean")){
			logger.debug("返回boolean 类型");
			return boolean.class;
		}
		if(clazzType.equals("char")){
			logger.debug("返回char 类型");
			return char.class;
		}
		if(clazzType.equals("long")){
			logger.debug("返回long 类型");
			return long.class;
		}
		if(clazzType.equals("short")){
			logger.debug("返回short 类型");
			return short.class;
		}
		try {
			logger.debug("返回clazzType 类型");
			return Class.forName(clazzType);
		} catch (ClassNotFoundException e) {
			logger.error("没有找到相应的类");
			throw new RuntimeException("在当前classloader中没有找到类",e);
		}
	}
	/**
	 * 将obj转换成String类型的值.
	 * @param obj
	 * @return 字符串
	 */
	public static final String parseString(Object obj){
		if(obj instanceof java.util.Date){
			return ""+((java.util.Date)obj).getTime();
		}
		return obj.toString();
	}
	/**
	 * 产生一个主键.
	 * @return 随机字符串
	 */
	public static String getPriKeyId(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	/**
	 * 功能：对大于1000，小于2000的字符串补长到2002<br>
	 * 描述：对大于1000，小于2000的字符串补长到2002，小于1000和大于2000的原样返回<br>
	 * @param content 字符串
	 * @return 对字符串补长
	 */
	public static String createBytes(Object content){
		if(content == null ||"".equals(content.toString())) return " ";
		String str = content.toString();
		int len = str.getBytes().length;
		if(len >= 1000 && len <= 2000) 
			str = StringUtils.rightPad(str, 2002); 
		return str;
	}
	
	public static Map<String, String> strings2Map(String[] context){
		Map<String, String> ctx = new HashMap<String, String>();
		if(context == null || "".equals(context)) return ctx;
		
		for (int i = 0; i < context.length; i += 2) {
			ctx.put(context[i], context[i + 1]);
		}
		return ctx;
	}
	
	public static String paserListToString(List<String> strs){
		StringBuffer str = new StringBuffer();
		for(String s : strs){
			str.append("'");
			str.append(s);
			str.append("',");
		}
		String text = str.toString();
		if(text.length()>0){
			text = text.substring(0, text.length()-1);
		}
		return text;
	}
	
	public static boolean isEmpty(String str){
		return StringUtils.isEmpty(str);
	}
	
	/**
	 * 功能：把java 对象转换成json字符串
	 * @param o 需要转换的bean对象
	 * @return 返回转换之后的json字符串,有可能是null
	 */
	public static String object2Json(Object o){
		ObjectMapper objectMapper = new ObjectMapper();
		String data = null;
			try {
				data = objectMapper.writeValueAsString(o);
			} catch (JsonParseException e) {
				System.out.println("对象结构错误");
			} catch (JsonMappingException e) {
				e.printStackTrace();
				System.out.println("json对象和java bean对象的属性不一致");
			} catch (IOException e) {
				System.out.println(o.getClass().getName()+"格式化错误");
			}
			return data;
	}
	
	/**
	 * 功能：把json字符串转换成java 对象
	 * @param jsonStr json字符串数据
	 * @param o 需要转换的bean对象
	 * @return 返回转换之后的Object，有可能为null
	 */
	public static Object json2Object(String jsonStr, Object o){
		ObjectMapper objectMapper = new ObjectMapper();
		Object data = null;
			try {
				data = objectMapper.readValue(jsonStr, o.getClass());
			} catch (JsonParseException e) {
				System.out.println("json格式错误");
			} catch (JsonMappingException e) {
				e.printStackTrace();
				System.out.println("json对象和java bean对象的属性不一致");
			} catch (IOException e) {
				System.out.println("json格式化错误");
			}
			return data;
	}
	
	public static void println(Object o){
//		System.out.println(o+"，时间："+System.currentTimeMillis());
	}
	
	public static boolean oIsEmpty(List<?> list){
		if(list != null && list.size()>0)
			return true;
		return false;
	}
	
	public static Object extendObject(Object o1, Object o2) {
		Field[] field = o1.getClass().getDeclaredFields();
		try {
			for(int j=0 ; j<field.length ; j++){
	            String name = field[j].getName();    
	            String type = field[j].getGenericType().toString(); 
	            Method m = o1.getClass().getMethod("get"+toUpperCaseFirstOne(name));
	            if(type.equals("class java.lang.String")){   
					
	                String value2 = (String) m.invoke(o2);
	                if(value2 != null && !"".equals(value2)){
	                    m = o1.getClass().getMethod("set"+toUpperCaseFirstOne(name), String.class);
	                    m.invoke(o1, value2);
	                }
	            } else if("int".equals(type)) {
	                int value2 =  (Integer) m.invoke(o2);   
	                if( value2>0){
	                    m = o1.getClass().getMethod("set"+toUpperCaseFirstOne(name),int.class);
	                    m.invoke(o1, value2);
	                }
	            }else if("long".equals(type)) {
	                long value2 = (Long) m.invoke(o2);   
	                if( value2 > 0){
	                    m = o1.getClass().getMethod("set"+toUpperCaseFirstOne(name), long.class);
	                    m.invoke(o1, value2);
	                }
	            }else{
	            	Object value2 = m.invoke(o2);
	            	if(value2 != null){
	            		m = o1.getClass().getMethod("set"+toUpperCaseFirstOne(name), Object.class);
	                    m.invoke(o1, value2);
	            	}
	            }
	            
			}
			return o1;
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage(), e);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			logger.error("合并对象方法不匹配");
			logger.error(e.getMessage(), e);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage(), e);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage(), e);
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage(), e);
		}
		return null;
	}
	private static String toUpperCaseFirstOne(String name) {
		// TODO Auto-generated method stub
		String newName = name.substring(0,1).toUpperCase()+name.substring(1, name.length());
		return newName;
	}
	
//	public static void main(String[] args) {
//		System.out.println(toUpperCaseFirstOne("name"));
//	}
	
}
