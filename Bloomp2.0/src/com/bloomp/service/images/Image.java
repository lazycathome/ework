package com.bloomp.service.images;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.StringUtils;

import com.mortennobel.imagescaling.ResampleOp;

/**
 * 图片操作包装类
 * 主要提供对图片进行裁剪、缩放及水印叠加等功能
 * @author liujt
 *
 */
public class Image {

	public static final int ALIGN_UNKNOWN = 0;

	public static final int ALIGN_LEFT = 0;
	public static final int ALIGN_CENTER = 1;
	public static final int ALIGN_RIGHT = 2;

	public static final int ALIGN_TOP = 0;
	public static final int ALIGN_MIDDLE = 1;
	public static final int ALIGN_BOTTOM = 2;

	/**
	 * 原图
	 */
	BufferedImage srcImage = null;
	/**
	 * 目标图
	 */
	BufferedImage destImage = null;

	/**
	 * 从文件系统加载图片
	 * @param fileName 文件名称
	 * @return 成功返回true，失败返回false
	 */
	public boolean read(String fileName) {
		try {
			System.out.println("fileName:"+fileName);
			srcImage = ImageIO.read(new File(fileName));

			return (srcImage != null);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;
	}
	
	/**
	 * @return
	 */
	public int[] getImageRealSize(){
		return new int[]{srcImage.getWidth(), srcImage.getHeight()};
	} 

	/**
	 * 将处理结果保存到文件
	 * @param fileName 需要处理的文件名称
	 * @return 成功返回true, 失败返回false
	 */
	public boolean saveAs(String fileName) {
		try {
			ImageIO.write(destImage, getFileFormat(fileName), new File(fileName));

			return true;

		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * 裁剪图片
	 * @param x 裁剪起始点的x坐标值
	 * @param y 裁剪起始点的y坐标值
	 * @param width 以裁剪起始点开始的裁剪宽度
	 * @param height 以裁剪起始点开始的裁剪高度
	 * @return 成功返回true，失败返回false
	 */
	public boolean cut(int x, int y, int width, int height) {
		destImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		if(destImage != null) {
			destImage.getGraphics().drawImage(srcImage, 0, 0, width, height, x, y, x + width, y + height, null);  

			return true;
		}
		
		return false;
	}

	/**
	 * 缩放图片
	 * @param width 指定缩放后的宽度
	 * @param height 指定缩放后的高度
	 * @param lockScale 是否锁定比例。取值有true和false。
	 * @return
	 */
	public boolean resize(int width, int height, boolean lockScale) {
		if (lockScale) {
			int realWidth = srcImage.getWidth();
			int realHeight = srcImage.getHeight();
			double realScale = (double) realWidth / realHeight;
			double scale = (double) width / height;
			int scaleWidth = 0;
			int scaleHeight = 0;

			if (realScale > scale) {
				scaleWidth = width;
				scaleHeight = (int) (realHeight * ((double) width / realWidth));

			} else if (realScale < scale) {
				scaleWidth = (int) (realWidth * ((double) height / realHeight));
				scaleHeight = height;

			} else {
				scaleWidth = width;
				scaleHeight = height;
			}

			ResampleOp resampleOp = new ResampleOp(scaleWidth, scaleHeight);
			BufferedImage tmpImage = resampleOp.filter(srcImage, null);

			destImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

			if(destImage != null) {
				destImage.getGraphics().fillRect(0, 0, width, height);
				destImage.getGraphics().drawImage(tmpImage, (int) ((width - scaleWidth) / 2), (int) ((height - scaleHeight) / 2), scaleWidth, scaleHeight, null);
			}

		} else {
			ResampleOp resampleOp = new ResampleOp(width, height);

			destImage = resampleOp.filter(srcImage, null);
		}

		return (destImage != null);
	}

	/**
	 * 叠加水印
	 * @param fileName
	 * @param align
	 * @param valign
	 * @param offX
	 * @param offY
	 * @param transparent
	 * @return
	 */
	public boolean watermark(String fileName, String align, String valign, int offX, int offY, boolean transparent) {
		BufferedImage wmImage = null;

		try {
			wmImage = ImageIO.read(new File(fileName));

		} catch (IOException e) {
			e.printStackTrace();
		}

		return watermark(wmImage, align, valign, offX, offY, transparent);
	}

	/**
	 * 叠加水印
	 * @param wmImage
	 * @param align
	 * @param valign
	 * @param offX
	 * @param offY
	 * @param transparent
	 * @return
	 */
	public boolean watermark(BufferedImage wmImage, String align, String valign, int offX, int offY, boolean transparent) {
		if(wmImage != null) {
			int width = srcImage.getWidth();
			int height = srcImage.getHeight();
			int wmWidth = wmImage.getWidth();
			int wmHeight = wmImage.getHeight();
			int x = 0, y = 0;

			switch(getAlign(align)) {
			case ALIGN_LEFT: x = offX; break;
			case ALIGN_CENTER: x = (int)((width - wmWidth) / 2); break;
			case ALIGN_RIGHT: x = width - wmWidth - offX; break;
			}

			switch(getAlign(valign)) {
			case ALIGN_TOP: y = offY; break;
			case ALIGN_MIDDLE: y = (int)((height - wmHeight) / 2); break;
			case ALIGN_BOTTOM: y = height - wmHeight - offY; break;
			}

			destImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

			if(destImage != null) {
				destImage.getGraphics().drawImage(srcImage, 0, 0, width, height, null);
				destImage.getGraphics().drawImage(wmImage, x, y, x + wmWidth, y + wmHeight, 0, 0, wmWidth, wmHeight, null);

				return true;
			}
		}

		return false;
	}

	/**
	 * 获取文件后缀
	 * @param fileName
	 * @return
	 */
	private String getFileFormat(String fileName) {
		int pos = fileName.lastIndexOf(".");

		if(pos != -1) {
			return fileName.substring(pos + 1);
		}

		return "jpeg";
	}

	private int getAlign(String str) {
		if(StringUtils.isNotBlank(str)) {
			if ("left".equalsIgnoreCase(str)) {
				return ALIGN_LEFT;

			} else if ("center".equalsIgnoreCase(str)) {
				return ALIGN_CENTER;

			} else if ("right".equalsIgnoreCase(str)) {
				return ALIGN_RIGHT;

			} else if ("top".equalsIgnoreCase(str)) {
				return ALIGN_TOP;

			} else if ("middle".equalsIgnoreCase(str)) {
				return ALIGN_MIDDLE;

			} else if ("bottom".equalsIgnoreCase(str)) {
				return ALIGN_BOTTOM;
			}
		}

		return ALIGN_UNKNOWN;
	}

}
