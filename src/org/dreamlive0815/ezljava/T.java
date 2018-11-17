package org.dreamlive0815.ezljava;

import java.util.Map;
import java.util.HashMap;

public abstract class T
{
    public static T instance = new T_EN();

    public static String G(String key)
    {
        if(instance == null) return null;
        return instance.get(key);
    }

    public abstract String get(String key);
}

class T_CN extends T
{
    Map<String, String> M = new HashMap<String, String>();

    public T_CN()
    {
        //M.put("", "");
        M.put("DCV.V.MNM", "MAC不匹配");
        M.put("DRAV.ATNAN.A", "用于签到的时间不能早于当前时间");
        M.put("DRAV.CCL.LON", "课前签到的经度必须在%s-%s之间");
        M.put("DRAV.CCL.LA", "课前签到的纬度必须在%s-%s之间");
        M.put("DRAV.CCT.IL", "不合法的课前签到时间:%s对于时间段:%s");
        M.put("DRAV.CCT.UKP", "不合法的时间段:%s");
        M.put("DRAV.CSL.LON", "归寝签到的经度必须在%s-%s之间");
        M.put("DRAV.CSL.LA", "归寝签到的纬度必须在%s-%s之间");
        M.put("DRAV.CST.IL", "不合法的归寝签到时间:%s");
        M.put("EJ.ALI.NLI", "请先登录");
        M.put("EJ.CR.UKTT", "不合法的时间段");
        M.put("EJ.GMI.MNF", "找不到模块[%s]的id");
        M.put("EJ.SR.WVF", "归寝签到校验失败:%s");
        M.put("RAG.GRD.OFB", "base必须在minLimit和maxLimit之间(%s-%s)");
    }

    @Override
    public String get(String key)
    {
        if(!M.containsKey(key)) return null;
        return M.get(key);
    }
}

class T_EN extends T
{
    Map<String, String> M = new HashMap<String, String>();

    public T_EN()
    {
        M.put("DCV.V.MNM", "MAC does not match");
        M.put("DRAV.ATNAN.A", "you cannot report using the time ahead now");
        M.put("DRAV.CCL.LON", "the longitude of course location must be bewteen %s-%s");
        M.put("DRAV.CCL.LA", "the latitude of course location must be bewteen %s-%s");
        M.put("DRAV.CCT.IL", "illegal time : %s for course period : %s");
        M.put("DRAV.CCT.UKP", "illegal course period : %s");
        M.put("DRAV.CSL.LON", "the longitude of sleep location must be bewteen %s-%s");
        M.put("DRAV.CSL.LA", "the latitude of sleep location must be bewteen %s-%s");
        M.put("DRAV.CST.IL", "illegal sleep time : %s");
        M.put("EJ.ALI.NLI", "please log in first");
        M.put("EJ.CR.UKTT", "illegal course report timetype : %s");
        M.put("EJ.GMI.MNF", "cannot find the id of the module : %s");
        M.put("EJ.SR.WVF", "sleep report verify failed : %s");
        M.put("RAG.GRD.OFB", "base must be  between minLimit and maxLimit(%s-%s)");
    }

    @Override
    public String get(String key)
    {
        if(!M.containsKey(key)) return null;
        return M.get(key);
    }
}

