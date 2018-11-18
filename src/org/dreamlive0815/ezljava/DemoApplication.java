package org.dreamlive0815.ezljava;

import java.lang.Thread;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Random;

import org.dreamlive0815.util.*;

public class DemoApplication
{
	static boolean useProxy = false;

	static String userName;
	static String passWord;
	static String mac;
	static String dev;

	public static void main(String[] args)
	{
		try {
			
			report(args);

		} catch(Exception e) {
			LOG.L(String.format("!!!Error Occurs!!!%s%s%s", ENV.NL, e.getMessage(), ENV.NL));
			e.printStackTrace();
		}	
	}

	static void delay(int maxMilliSeconds) throws Exception
	{
		Random rand = new Random();
		int sec = rand.nextInt(maxMilliSeconds);
		LOG.L(String.format("gonna sleep %s milliseconds", sec));
		Thread.sleep(sec);
	}

	static void report(String[] args) throws Exception
	{
		TI();
		LOG.L("ezl is starting...");
		delay(180 * 1000);
		System.out.println(String.format("UseProxy:%s", useProxy));
		EZLJava ezl = new EZLJava(mac, dev, useProxy);
		ezl.login(userName, passWord);
		String date = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
		String type = args.length > 0 ? args[0] : "";
		if("Course".equals(type)) {
			String timeType = args.length > 1 ? args[1] : "";
			CourseArgs cs = ReportArgsGenerator.getCourseReportArgs(timeType);
			ezl.courseReport(cs);
			LOG.L("course report done");
			sendMail(String.format("[%s]Course Report[%s]", date, timeType), "Course Report Done For Timetype : " + timeType);
		} else if("Sleep".equals(type)) {
			SleepArgs as = ReportArgsGenerator.getSleepReportArgs();
			ezl.sleepReport(as);
			LOG.L("sleep report done");
			sendMail(String.format("[%s]Sleep Report", date), "Sleep Report Done");
		} else {
			throw new Exception("unknown report type");
		}
	}
	 
	static void TI()
	{
		userName = String.valueOf(ENV.G("username"));
        passWord = String.valueOf(ENV.G("password"));
        mac = String.valueOf(ENV.G("macaddress"));
        dev = String.valueOf(ENV.G("devname"));
	}

	static void sendMail(String title, String body) throws Exception
	{
		String path = ENV.getWorkingDirectory() + "mail.conf";
		System.out.println("configPath: " + path);
		Map<String, String> config = ENV.loadConfigFromFile(path);
		MailUtil mailUtil = new MailUtil(config.get("host"));
		mailUtil.setCredentials(config.get("username"), config.get("password"));
		mailUtil.setFromTo(config.get("from"), config.get("to"));
		mailUtil.sendText(title, body);
	}
}
