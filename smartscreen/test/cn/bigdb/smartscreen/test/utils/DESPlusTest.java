package cn.bigdb.smartscreen.test.utils;

import org.junit.Test;

import cn.bigdb.smartscreen.common.Constants;
import cn.bigdb.smartscreen.utils.DESPlus;
import cn.bigdb.smartscreen.utils.EncryptException;

public class DESPlusTest {

	@Test
	public void doEncrypt(){
		try {
			String pwd = DESPlus.doEncrypt(Constants.SECRET_KEY, "mishang");
			System.out.println(pwd);
		} catch (EncryptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void doDecrypt(){
		try {
			String pwd = DESPlus.doDecrypt(Constants.SECRET_KEY, "SJMX50CQMHY=");
			System.out.println(pwd);
		} catch (EncryptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
