package cn.bigdb.smartscreen.utils;

import java.io.IOException;

/**
 * 加密/解密异常.
 * 
 * @author thomas.h.zhang
 */
public class EncryptException
		extends IOException
{
	public EncryptException()
	{
	}

	public EncryptException(String msg)
	{
		super(msg);
	}
}
