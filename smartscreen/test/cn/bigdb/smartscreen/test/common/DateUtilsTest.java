package cn.bigdb.smartscreen.test.common;

import java.util.Date;

import org.junit.Test;

import cn.bigdb.smartscreen.utils.DateUtil;

public class DateUtilsTest {

	@Test
	public void day(){
		System.out.println(DateUtil.getMillisOfDate(new Date()));
		System.out.println(DateUtil.getDaysOfTowDiffDate(1395285494059l, 1395226982496l));
		System.out.println(DateUtil.getNowOfDateByFormat("yyyy-MM-dd"));
		Date date = new Date(1395294880720l);
		System.out.println(DateUtil.geDateByFormat(date, "yyyy-MM-dd"));
		System.out.println(1495485494059l/(1000*60*60*24));
	}
}
