package org.dreamlive0815.ezljava;


public class DemoApplication {

	static String userName;
	static String passWord;
	static String mac;
	static String dev;

	public static void main(String[] args)
	{
		TI();
	
		
		try{
			EZLJava ezl = new EZLJava(mac, dev, true);
			//ezl.login(userName, passWord);
		} catch(Exception e) {
			System.out.println("发生错误");
			System.out.println(e.getMessage());
		}
		
	}
	 
	static void TI()
	{
		userName = "2015331250027";
        passWord = "252614";
        mac = "140AD8A4B7CCC4B4DWH9X17115W08684";
        dev = "HUAWEI DIG-AL00";
	}
}
