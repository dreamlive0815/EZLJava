package org.dreamlive0815.ezljava;

import java.util.List;
import org.dreamlive0815.util.*;

public class DemoApplication {

	static String userName;
	static String passWord;
	static String mac;
	static String dev;

	public static void main(String[] args)
	{
		TI();
		try {
			
		} catch(Exception e) {
			
		}
		MailUtil.sendMail("");
		//Report(args);
	}

	static void Report(String[] args)
	{
		try {
			EZLJava ezl = new EZLJava(mac, dev, true);
			SleepArgs as = ReportArgsGenerator.getSleepReportArgs();
			ezl.login(userName, passWord);
			ezl.sleepReport(as);
			LOG.L("归寝签到成功");
		} catch(Exception e) {
			LOG.L(String.format("!!!Error Occurs!!!%s%s", ENV.NL, e.getMessage()));
		}
	}
	 
	static void TI()
	{
		userName = String.valueOf(ENV.G("username"));
        passWord = String.valueOf(ENV.G("password"));
        mac = String.valueOf(ENV.G("macaddress"));
        dev = String.valueOf(ENV.G("devname"));
	}
}
