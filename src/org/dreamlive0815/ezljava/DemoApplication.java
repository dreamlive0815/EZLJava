package org.dreamlive0815.ezljava;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import org.dreamlive0815.util.*;

public class DemoApplication {

	static String userName;
	static String passWord;
	static String mac;
	static String dev;

	public static void main(String[] args)
	{
		try {
			//sendMail("t", "b");
		} catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		report(args);
	}

	static void report(String[] args)
	{
		TI();
		try {
			EZLJava ezl = new EZLJava(mac, dev, false);
			SleepArgs as = ReportArgsGenerator.getSleepReportArgs();
			ezl.login(userName, passWord);
			ezl.sleepReport(as);
			LOG.L("归寝签到成功");
			String date = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
			sendMail(String.format("%s归寝签到成功", date), "归寝签到成功");
		} catch(Exception e) {
			LOG.L(String.format("!!!Error Occurs!!!%s%s", ENV.NL, e.getMessage()));
			e.printStackTrace();
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
