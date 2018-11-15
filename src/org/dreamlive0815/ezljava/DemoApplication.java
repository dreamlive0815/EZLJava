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
			System.out.println("ERROR");
			System.out.println(e.getMessage());
		}
		
		Report();
	}

	static void Report()
	{
		try{
			EZLJava ezl = new EZLJava(mac, dev, true);
			SleepArgs as = ReportArgsGenerator.getSleepReportArgs();
			ezl.login(userName, passWord);
			ezl.sleepReport(as);
		} catch(Exception e) {
			System.out.println("ERROR");
			System.out.println(e.getMessage());
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
