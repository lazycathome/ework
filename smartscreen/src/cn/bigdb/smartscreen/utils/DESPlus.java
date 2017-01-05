package cn.bigdb.smartscreen.utils;

import java.security.Key;

import javax.crypto.Cipher;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 是DES加密处理类.主要用来对数据进行加密,解密操作.
 * 
 * @author thomas.h.zhang
 */
public class DESPlus
{
	private static final String Algorithm = "DES/ECB/PKCS5Padding";
	//private static final String Algorithm = "DES/ECB/NoPadding";
	//private static final String Algorithm = "DES/CBC/NoPadding";
	//private static final String Algorithm = "DES/CFB8/NoPadding";
	//private static final String Algorithm = "DES/OFB32/PKCS5Padding";
	private static final String Unicode = "UTF-8";
	private static final String DefautKey = "ultragent";
	public static final String DefautPassword = "agentless";

	/**
	 * <li>获得DES加密的密钥。在交易处理的过程中应该定时更换密钥。
	 * <li>从指定字符串生成密钥, 密钥所需的字节数组长度为8位, 不足8位时后面补0, 超出8位只取前8位
	 * <li>需要JCE的支持，如果jdk版本低于1.4，则需要安装jce-1_2_2才能正常使用。
	 * 
	 * @param bytKey/构成该字符串的字节数组
	 * @return Key 返回对称密钥
	 * @throws java.security.NoSuchAlgorithmException
	 * @see util.EncryptUtil 其中包括加密和解密的方法
	 */
	public static Key getKey(byte[] bytKey)
	// throws NoSuchAlgorithmException
	// , InvalidKeyException, InvalidKeySpecException
	{
		// 创建一个空的8位字节数组（默认值为0）
		byte[] byt8 = new byte[8];

		// 将原始字节数组转换为8位
		for (int i = 0; i < bytKey.length && i < byt8.length; i++) {
			byt8[i] = bytKey[i];
		}

		// 用 IBM 的包进行加密解密
		// Security.insertProviderAt(new com.ibm.crypto.provider.IBMJCE(), 1);
		// 用SUN 的包进行加密解密
		// Security.insertProviderAt(new com.sun.crypto.provider.SunJCE(), 1);
		// DESKeySpec desKS = new DESKeySpec(byt8);
		// SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
		// SecretKey desKey = skf.generateSecret(desKS);
		// return desKey;

		// 生成密钥
		Key key = new javax.crypto.spec.SecretKeySpec(byt8, "DES");
		return key;
	}

	/**
	 * 将指定的数据根据提供的密钥进行加密
	 * 
	 * @param key/密钥
	 * @param data/需要加密的数据
	 * @return byte[] 加密后的数据
	 * @throws EncryptException/当加密出现异常情况时,产生异常信息
	 */
	public static byte[] doEncrypt(Key key, byte[] data)
			throws EncryptException
	{
		try {
			// Get a cipher object
			Cipher cipher = Cipher.getInstance(Algorithm);
			// Encrypt
			cipher.init(Cipher.ENCRYPT_MODE, key);
			// byte[] stringBytes = amalgam.getBytes("UTF8");
			byte[] raw = cipher.doFinal(data);
			// BASE64Encoder encoder = new BASE64Encoder();
			// String base64 = encoder.encode(raw);
			return raw;
		} catch (Exception e) {
			e.printStackTrace();
			throw new EncryptException("Do encrypt occurs Exception.["
					+ e.getMessage() + "]");
		}
	}

	/**
	 * 将给定的已加密的数据通过指定的密钥进行解密
	 * 
	 * @param key/密钥
	 * @param raw/待解密的数据
	 * @return byte[] 解密后的数据
	 * @throws EncryptException/当加密出现异常情况时,产生异常信息
	 */
	public static byte[] doDecrypt(Key key, byte[] raw)
			throws EncryptException
	{
		try {
			// Get a cipher object
			Cipher cipher = Cipher.getInstance(Algorithm);
			// Decrypt
			cipher.init(Cipher.DECRYPT_MODE, key);
			// BASE64Decoder decoder = new BASE64Decoder();
			// byte[] raw = decoder.decodeBuffer(data);
			byte[] data = cipher.doFinal(raw);
			// String result = new String(stringBytes, "UTF8");
			// System.out.println("the decrypted data is: " + result);
			return data;
		} catch (Exception e) {
			e.printStackTrace();
			throw new EncryptException("Do decrypt occurs Exception.["
					+ e.getMessage() + "]");
		}
	}

	/**
	 * <li>将byte数组转换为表示16进制值的字符串，如：byte[]{8,18}转换为：0813，
	 * <li>和public static byte[] hexStr2ByteArr(String strIn) 互为可逆的转换过程
	 * 
	 * @param arrB/需要转换的byte数组
	 * @return 转换后的字符串
	 * @throws Exception/本方法不处理任何异常，所有异常全部抛出
	 */
	public static String byteArr2HexStr(byte[] arrB)
			throws Exception
	{
		int iLen = arrB.length;
		// 每个byte用两个字符才能表示，所以字符串的长度是数组长度的两倍
		StringBuffer sb = new StringBuffer(iLen * 2);
		for (int i = 0; i < iLen; i++) {
			int intTmp = arrB[i];
			// 把负数转换为正数
			while (intTmp < 0) {
				intTmp = intTmp + 256;
			}
			// 小于0F的数需要在前面补0
			if (intTmp < 16) {
				sb.append("0");
			}
			sb.append(Integer.toString(intTmp, 16));
		}
		return sb.toString();
	}

	/**
	 * <li>将表示16进制值的字符串转换为byte数组，
	 * <li>和public static String byteArr2HexStr(byte[] arrB) 互为可逆的转换过程
	 * 
	 * @param strIn/需要转换的字符串
	 * @return 转换后的byte数组
	 * @throws Exception/本方法不处理任何异常，所有异常全部抛出
	 */
	public static byte[] hexStr2ByteArr(String strIn)
			throws Exception
	{
		byte[] arrB = strIn.getBytes();
		int iLen = arrB.length;

		// 两个字符表示一个字节，所以字节数组长度是字符串长度除以2
		byte[] arrOut = new byte[iLen / 2];
		for (int i = 0; i < iLen; i = i + 2) {
			String strTmp = new String(arrB, i, 2);
			arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
		}
		return arrOut;
	}

	/**
	 * 将指定的数据根据提供的密钥进行加密
	 * 
	 * @param key/密钥，明文
	 * @param data/需要加密的数据，明文
	 * @return String/加密后的数据，密文
	 * @throws EncryptException/当加密出现异常情况时,产生异常信息
	 */
	public static String doEncrypt(String key, String data)
			throws EncryptException
	{
		try {
			Key k = getKey(key.getBytes());
			byte[] raw = doEncrypt(k, data.getBytes(Unicode));

			BASE64Encoder encoder = new BASE64Encoder();
			String base64 = encoder.encode(raw);
			return base64;
		} catch (Exception e) {
			e.printStackTrace();
			throw new EncryptException("Do encrypt occurs Exception.["
					+ e.getMessage() + "]");
		}
	}

	/**
	 * 将给定的已加密的数据通过指定的密钥进行解密
	 * 
	 * @param key/密钥，明文
	 * @param raw/待解密的数据，密文
	 * @return String/解密后的数据，明文
	 * @throws EncryptException/当解密出现异常情况时,产生异常信息
	 */
	public static String doDecrypt(String key, String raw)
			throws EncryptException
	{
		try {
			Key k = getKey(key.getBytes());

			BASE64Decoder decoder = new BASE64Decoder();
			byte[] nobase64 = decoder.decodeBuffer(raw);

			// nobase64中, 非8位字节倍数的字节一定是'\0'
			int len = nobase64.length - nobase64.length % 8;
			// 创建一个空的8位字节倍数数组（默认值为0）
			byte[] byt8 = new byte[len];

			// 将原始字节数组转换为8位
			for (int i = 0; i < nobase64.length && i < byt8.length; i++) {
				byt8[i] = nobase64[i];
			}

			byte[] data = doDecrypt(k, byt8);
			String result = new String(data, Unicode);
			// System.out.println("the decrypted data is: " + result);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new EncryptException("Do decrypt occurs Exception.["
					+ e.getMessage() + "]");
		}
	}

	/**
	 * 将指定的数据根据默认密钥("ultragent")进行加密
	 * 
	 * @param data/需要加密的数据，明文
	 * @return String/加密后的数据，密文
	 * @throws EncryptException/当加密出现异常情况时,产生异常信息
	 */
	public static String doEncrypt(String data)
			throws EncryptException
	{
		return doEncrypt(DefautKey, data);
	}

	/**
	 * 将给定的已加密的数据通过默认密钥("ultragent")进行解密
	 * 
	 * @param raw/待解密的数据，密文
	 * @return String/解密后的数据，明文
	 * @throws EncryptException/当加密出现异常情况时,产生异常信息
	 */
	public static String doDecrypt(String raw)
			throws EncryptException
	{
		return doDecrypt(DefautKey, raw);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
			throws Exception
	{
		// Key key = DESPlus.getKey("ultragent".getBytes());
		// byte[] orgData = "hello".getBytes();
		// byte[] raw = DESPlus.doEncrypt(key, orgData);
		// System.out.println("Encrypt: [" + DESPlus.byteArr2HexStr(raw) + "]");
		//
		// byte[] data = DESPlus.doDecrypt(key, raw);
		// System.out.println("Decrypt: [" + new String(data) + "]");

		String raw = DESPlus.doEncrypt("a", "agentless");
		System.out.println(raw);
		String data = DESPlus.doDecrypt("ultraCruiser", "56UvJGCl16Q=");
		System.out.println(data);
		
//ultragent 	 	cYRXh2+0FRPYch071vcCfA==
		//a      /tDXLgE3x8ckHzzFpFTCnQ==
//		agentless
		//System.out.println(DESPlus.doEncrypt("ultragent"));
	}
}