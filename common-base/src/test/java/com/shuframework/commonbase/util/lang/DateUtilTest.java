package com.shuframework.commonbase.util.lang;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DateUtilTest {

	@Test
	public void strToDate22() {
//		Date date1 = DateUtil.format2Date(1547861802049L);
		Date date1 = DateUtil.format2Date(1547861802000L);
		Date date2 = DateUtil.format2Date(1550713992000L);
		System.out.println(date1);
		System.out.println(date2);

//		Date date3 = DateUtil.format(1550714072017L);
//		Date date4 = DateUtil.format(1550671372093L);
//		System.out.println(date3);
//		System.out.println(date4);
	}
	@Test
	public void strToDate() {
		Date date1 = DateUtil.parseDate(null);
		System.out.println(date1);

	}

	@Test
	public void dateToStr() {
		String date1 = DateUtil.format(null);
		System.out.println(date1);

	}

	@Test
	public void getTime() {
		Date date1 = DateUtil.parseDate("2018-01-10 15:10:10");
		System.out.println(date1.getTime());
		
		Date date2 = new Date();
		System.out.println(date2.getTime());

		System.out.println(date1.before(date2));
		System.out.println(DateUtil.isBefore(date1, date2));
		System.out.println(date1.after(date2));
		System.out.println(DateUtil.isAfter(date1, date2));
	}
	
	@Test
	public void getInts_test1() {
		Date date = new Date();//2017-01-10 15:10:10  周二 
		int year = DateUtil.getYear(date);
		int month = DateUtil.getMonth(date);
		int day = DateUtil.getDayOfMonth(date);
		int dayOfYear = DateUtil.getDayOfYear(date);
		int daysOfMonth = DateUtil.getMaxDaysOfMonth(date);
		int week = DateUtil.getWeek(date);
		int week2 = DateUtil.getWeek2Zh(date);
		boolean isWeekend = DateUtil.isWeekend(date);
		
		System.out.println(year);//2017
		System.out.println(month);//1
		System.out.println(day);//10
		System.out.println(dayOfYear);//10
		System.out.println(daysOfMonth);//31
		System.out.println(week);//3
		System.out.println(week2);//2
		System.out.println(isWeekend);//false
	}
	
	@Test
	public void isBefore_test1() {
		Date date1 = DateUtil.parseDate("2018-01-10 15:10:10");
		System.out.println(date1.getTime());
		
		Date date2 = new Date();//2018-03-10
		System.out.println(date2.getTime());
		boolean flag = DateUtil.isBefore(date1, date2);
		System.out.println(flag);//false
	}

	@Test
	public void getBetweenCounts_test1() {
		Date startTime = DateUtil.parseDateShort("2018-10-12");
		Date endTime = DateUtil.parseDateShort("2018-10-28");
//		Date endTime = DateUtil.parseDateShort("2019-01-19");
//		Date endTime = DateUtil.addDay(startTime, 100);
//		System.out.println(DateUtil.format(endTime));
//		Date startTime = DateUtil.parseDateShort("2019-01-01");
//		Date endTime = DateUtil.parseDateShort("2019-01-12");
	//93
		Integer counts = DateUtil.getBetweenCounts(endTime, startTime);
		System.out.println(counts);
	}
	
	@Test
	public void addDay_test1() {
		Date today = new Date();
		//后一天
		Date date1 = DateUtil.addDay(today, 1);
		System.out.println(DateUtil.format(date1));
		//后一天
		Date date3 = DateUtil.addDay(today, 0);
		System.out.println(DateUtil.format(date3));
		//前一天
		Date date2 = DateUtil.addDay(today, -1);
		System.out.println(DateUtil.format(date2));
	}
	

	@Test
	public void getStartTimeOrEndTime() {
		Date date = new Date();//2017-01-10 15:10:10  周二 
		//2017-1-10 0:00:00
		Date startTime =  DateUtil.getStartTime(date);
		System.out.println(DateUtil.format(startTime));
		//2017-1-11 0:00:00
		Date endTime =  DateUtil.getEndTime(date);
		System.out.println(DateUtil.format(endTime));
	}
	
	
	@Test
	public void getFirstDayOfMonth_test() {
		Date date = new Date();//2017-01-10 15:10:10  周二 
		
		//2017-1-1 0:00:00
		Date newDate1 =  DateUtil.getFirstDayOfMonth(date, 1);
		System.out.println(DateUtil.format(newDate1));
		
		Date date2 = DateUtil.parseDate("2016-10-10 11:03:20");
		//2016-1-1 0:00:00
		Date newDate2 =  DateUtil.getFirstDayOfMonth(date2, 1);
		System.out.println(DateUtil.format(newDate2));
		
		//2017-1-1 0:00:00
		Date newDate3 =  DateUtil.getFirstDayOfMonth(date);
		System.out.println(DateUtil.format(newDate3));
		
		//2016-10-1 0:00:00
		Date newDate4 =  DateUtil.getFirstDayOfMonth(date2);
		System.out.println(DateUtil.format(newDate4));
		
		//2017-1-1 0:00:00
		Date newDate5 =  DateUtil.getFirstDayOfMonth(1);
		System.out.println(DateUtil.format(newDate5));
	}
	
	@Test
	public void getLastDayOfMonth_test() {
		Date date = new Date();//2017-01-10 15:10:10  周二 
		
		//2017-01-31 23:59:59
		Date newDate1 =  DateUtil.getLastDayOfMonth(date, 1);
		System.out.println(DateUtil.format(newDate1));
		
		Date date2 = DateUtil.parseDate("2016-10-10 11:03:20");
		//2016-01-31 23:59:59
		Date newDate2 =  DateUtil.getLastDayOfMonth(date2, 1);
		System.out.println(DateUtil.format(newDate2));
		
		//2017-01-31 23:59:59
		Date newDate3 =  DateUtil.getLastDayOfMonth(date);
		System.out.println(DateUtil.format(newDate3));
		
		//2016-10-31 23:59:59
		Date newDate4 =  DateUtil.getLastDayOfMonth(date2);
		System.out.println(DateUtil.format(newDate4));
		
		//2017-01-31 23:59:59
		Date newDate5 =  DateUtil.getLastDayOfMonth(1);
		System.out.println(DateUtil.format(newDate5));
	}


	@Test
	public void getFirstDayByAddMonth_test() {
		Date date = DateUtil.parseDate("2017-01-10 15:10:10");//2017-01-10 15:10:10  周二
		//2017-1-1 0:00:00
		Date monthStartTime =  DateUtil.getFirstDayByAddMonth(date, 0);
		System.out.println(DateUtil.format(monthStartTime));
		//2017-2-1 0:00:00
		Date monthEndTime =  DateUtil.getLastDayByAddMonth(date, 0);
		System.out.println(DateUtil.format(monthEndTime));
	}

//	@Test
//	public void getLastDayByAddMonth_test() {
//		Date date = DateUtil.parseDate("2017-01-10 15:10:10");//2017-01-10 15:10:10  周二
//		//2017-1-1 0:00:00
//		Date monthStartTime =  DateUtil.getLastDayByAddMonth(date, 0);
//		System.out.println(DateUtil.format(monthStartTime));
//		//2017-2-1 0:00:00
//		Date monthEndTime =  DateUtil.getLastDayByAddMonth(date, 0);
//		System.out.println(DateUtil.format(monthEndTime));
//	}

	@Test
	public void getFirstDayOfWeek2zh_test() {
		Date date = DateUtil.parseDate("2016-6-8 11:03:20");
		int week2Zh = DateUtil.getWeek2Zh(date);
		System.out.println(week2Zh);

		Date firstDayOfWeek2zh= DateUtil.getFirstDayOfWeek2zh(date);
		System.out.println(DateUtil.format(firstDayOfWeek2zh));
	}

	@Test
	public void getFirstDayOfYear_test() {
		Date date = DateUtil.parseDate("2016-6-8 11:03:20");
		Date firstDayOfYear = DateUtil.getFirstDayOfYear(date);
		System.out.println(DateUtil.format(firstDayOfYear));

		Date newDate1 =  DateUtil.getFirstDayOfMonth(date, 1);
		System.out.println(DateUtil.format(newDate1));
	}

	@Test
	public void getDates_test() {
		Date startTime = DateUtil.parseDate("2018-07-01 00:00:00");
		Date endTime = DateUtil.parseDate("2018-07-03 23:59:59");
//		long dayDiff = DateUtil.timeDiff(endTime, startTime)/(1000L*3600*24);
		int dayDiff = DateUtil.getDayOfMonth(endTime)- DateUtil.getDayOfMonth(startTime);

		Date tempStartTime = DateUtil.getStartTime(startTime);
		List<String> list = new ArrayList<>();
//		list.add(DateUtil.format(tempStartTime, "yyyy/MM/dd"));
//		for (int i = 0; i < dayDiff; i++){
//			tempStartTime = DateUtil.addDay(tempStartTime, 1);
//			list.add(DateUtil.format(tempStartTime, "yyyy/MM/dd"));
//		}
		for (int i = 0; i <= dayDiff; i++){
			list.add(DateUtil.format(DateUtil.addDay(tempStartTime, i), "yyyy/MM/dd"));
		}

		System.out.println(dayDiff);
		System.out.println(list);
	}

	@Test
	public void getDates_test2() {
		//UTC是本地时间
		String datetime = "2016-10-13T01:05:19.000Z";
		String datetime2 = "2016-10-13T10:05:19Z";
//		String time2 = datetime.replace("Z", " UTC");//UTC是本地时间
//		Date date1 = DateUtil.parseDate(time2, "yyyy-MM-dd'T'HH:mm:ss.SSS Z");
//		System.out.println(date1);

		Date date1 = DateUtil.parseDate(datetime, "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		System.out.println(DateUtil.format(date1));

		Date date2 = DateUtil.parseDate(datetime2, "yyyy-MM-dd'T'HH:mm:ss'Z'");
		System.out.println(DateUtil.format(date2));
	}



	@Test
	public void getTimestamp() {
		Date date = DateUtil.parseDate("2019-01-01 00:00:00");
		System.out.println(date.getTime());

		Date date1 = DateUtil.parseDate("2008-01-01 00:00:00");
		System.out.println(date1.getTime());

		Date date2 = DateUtil.parseDate("2006-01-01 00:00:00");
		System.out.println(date2.getTime());
	}

	@Test
	public void timestampToDate() {
		Date date1 = DateUtil.format2Date(1533571200000L);
		System.out.println(DateUtil.format(date1));

		Date date2 = DateUtil.format2Date(1533657599000L);
		System.out.println(DateUtil.format(date2));
	}


	@Test
	public void getWeekOfYear() {
		Date date1 = DateUtil.parseDate("2018-01-07 00:00:00");
		System.out.println(DateUtil.getWeekOfYear(date1));
		System.out.println(DateUtil.getWeekOfYear2Zh(date1));

	}

}
