package cn.bigdb.smartscreen.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;

/**
 * zip打包工具类
 * 
 * @author $Author liujt$
 * 
 */
public class ZIPUtils {
	

	private static Logger logger = Logger.getLogger(ZIPUtils.class);
	private static boolean bOverWrite = true;  //是否覆盖同名文件 取值范围为True和False
	private static int BUFFER = 4000;

	/**
	 * 将指定的文件fileName打包进名称为zipFile的zip文件中
	 * 
	 * @param fileName
	 *            文件名称,也可以是文件夹名称
	 * @param zipFile
	 *            zip包文件名 注：设置编码方式为gbk可以使用中文文件名
	 */
	public static boolean zip(String fileName, String zipFile) {
		List<String> filePaths = new ArrayList<String>();
		filePaths.add(fileName);
		return zip(filePaths, zipFile);
	}
	
	/**
	 * 功能：打包文件夹或者文件
	 * 描述：把指定的文件夹集合或者文件名称压缩到指定的zip下
	 * @param filePaths 文件夹路径或者文件名称集合
	 * @param zipFile 压缩包名称以及路径
	 * @return 成功返回true，失败返回false。
	 */
	public static boolean zip(List<String> filePaths, String zipFile) {
		logger.info("ZIPUtils starting...");
		logger.info("sourceDir : " + filePaths.toString());
		logger.info("zipFile : " + zipFile);
		// 输出流
		OutputStream os = null;
		boolean result = false;

		try {
			os = new FileOutputStream(zipFile);// 流文件
			BufferedOutputStream bos = new BufferedOutputStream(os);// 输出缓冲
			ZipOutputStream zos = new ZipOutputStream(bos);// zip文件流
			File file = null;
			Iterator<String> it = filePaths.iterator();
			while(it.hasNext()){
				file = new File(it.next());
				String basePath = null; // 根目录，用他来扫描子文件夹

				if (file.isDirectory()) {
					basePath = file.getPath();
				} else {
					basePath = file.getParent();
				}
				zipFile(file, basePath, zos);
			}
			
			zos.setEncoding("gbk");// 解决中文乱码问题
			zos.closeEntry();// 关闭zip条目
			zos.close();// 关闭zip流
			result = true;
		} catch (Exception e) {
			System.out.println("create zipFile fail");
			e.printStackTrace();
		}
		logger.info("ZIPUtils success? " + result);
		return result;
	}
	
	/**
	* 给定根目录及文件的实际路径，返回带有相对路径的文件名，用于zip文件中的路径.
	* 如将绝对路径，baseDir\dir1\dir2\file.txt改成 dir1/dir2/file.txt
	* 
	* @param baseDir
	*            java.lang.String 根目录
	* @param realFileName
	*            java.io.File 实际的文件名
	* @return 相对文件名
	*/
	private static String getAbsFileName(String baseDir, File realFileName) {
		   File real = realFileName;
		   File base = new File(baseDir);
		   String ret = real.getName();
		   while (true) {
		    real = real.getParentFile();
		    if (real == null)
		     break;
		    if (real.equals(base))
		     break;
		    else {
		     ret = real.getName() + "/" + ret;
		    }
		   }
		   return ret;
		}
	
	/**
	 * 将文件source写入zip文件流中
	 * 
	 * @param source
	 *            文件源（是一个文件对象）
	 * @param basePath
	 *            文件源的上级目录
	 * @param zos
	 *            zip文件流
	 */
	private static void zipFile(File source, String basePath,
			ZipOutputStream zos) {

		File[] files = new File[0];

		/**
		 * 如果source是目录，就列出所有文件，存入files数组 如果是文件，就直接处理
		 */
		if (source.isDirectory()) {
			files = source.listFiles();// 文件列表
		} else {
			files = new File[1];
			files[0] = source;
		}

		String pathName = null;// 文件名或路径
		byte[] buf = new byte[1024];
		int length = 0;

		try {
			for (File file : files) {
				if (file.isDirectory()) {
					pathName = file.getPath().substring(basePath.length() + 1)
							+ File.separator;// 目录名
					// 递归调用，一层层往下走，遍历所有的子文件夹
					zos.putNextEntry(new ZipEntry(pathName));// 写入下一天zip条目
					zipFile(file, basePath, zos);
				} else {
					pathName = file.getPath().substring(basePath.length() + 1);// 文件名
					logger.info("fileName:" + pathName);
					InputStream is = new FileInputStream(file);
					BufferedInputStream bis = new BufferedInputStream(is);
					String path = file.getParentFile().getParent();
					zos.putNextEntry(new ZipEntry(getAbsFileName(path, file)));
					while ((length = bis.read(buf)) > 0) {
						zos.write(buf, 0, length);
					}
					is.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 功能：解压压缩包
	 * 描述：解压指定的压缩包文件，且把文件放到指定的文件路径下
	 * @param source zip包文件路径
	 * @param destination 解压文件存放路径
	 * @return 返回成功文件路径集合
	 * @throws Exception 
	 */
	public static List<String> unZip(String source, String destination)
			throws Exception {
		List<String> allFileName = new ArrayList<String>();
		try {
			ZipFile zipFile = new ZipFile(source);
			Enumeration<ZipEntry> emu = zipFile.getEntries();
	
			while (emu.hasMoreElements()) {
				ZipEntry entry = (ZipEntry) emu.nextElement();
				// 会把目录作为一个file读出一次，所以只建立目录就可以，之下的文件还会被迭代到。
				if (entry.isDirectory()) {
					new File(destination + entry.getName()).mkdirs();
					continue;
				}
				BufferedInputStream bis = new BufferedInputStream(zipFile
						.getInputStream(entry));
				File file = new File(destination + entry.getName());
				//判断文件是否存在，如果存在且不覆盖的话直接跳出当前循环，进行下一次循环
				if(file.exists() && !isBOverWrite()){
					continue;
				}
				// 加入这个的原因是zipfile读取文件是随机读取的，这就造成可能先读取一个文件
				// 而这个文件所在的目录还没有出现过，所以要建出目录来。
				File parent = file.getParentFile();
				if (parent != null && (!parent.exists())) {
					parent.mkdirs();
				}
				FileOutputStream fos = new FileOutputStream(file);
				BufferedOutputStream bos = new BufferedOutputStream(fos, BUFFER);
				int count;
				allFileName.add(file.getAbsolutePath());
				byte data[] = new byte[BUFFER];
				while ((count = bis.read(data, 0, BUFFER)) != -1) {
					bos.write(data, 0, count);
				}
				bos.flush();
				bos.close();
				bis.close();
			}
			zipFile.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return allFileName;
	}

	public static boolean isBOverWrite() {
		return bOverWrite;
	}

	public static void setBOverWrite(boolean overWrite) {
		bOverWrite = overWrite;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ZIPUtils z = new ZIPUtils();
		List<String> a = new ArrayList<String>();
		try {
			z.setBOverWrite(false);
//			a = z.unZip("c:\\src.zip", "c:\\");
			a.add("c:\\aaa.xml");
			a.add("c:\\bbb.xml");
			z.zip(a, "c:\\aaaa.zip");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(a.size());
	}

	// public static void main(String[] args){
	// String sourceDir = "F:\\test\\tetete\\";
	// //String zipFile = "F:\\test\\tetete\\tetete.zip";//这里会造成无限递归
	// String zipFile = "F:\\test\\tetete.zip";
	// ZIPUtils.zip(sourceDir, zipFile);
	// }
}