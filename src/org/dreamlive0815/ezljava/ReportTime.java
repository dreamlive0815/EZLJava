package org.dreamlive0815.ezljava;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReportTime
{
    public int hour;
    public int minute;
    public int second;
    public int all;

    public ReportTime(String time) throws Exception
    {
        Pattern r = Pattern.compile("^(\\d{2}):(\\d{2}):(\\d{2})$");
        Matcher ma = r.matcher(time);
        if(!ma.find()) throw new Exception("时间不合法");
        hour = Integer.parseInt(ma.group(1));
        if(hour > 23) throw new Exception("小时不能超过23");
        minute = Integer.parseInt(ma.group(2));
        if(minute > 59) throw new Exception("分钟不能超过59");
        second = Integer.parseInt(ma.group(3));
        if(second > 59) throw new Exception("秒钟不能超过59");
        all = hour * 10000 + minute * 100 + second;
    }
}