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
        M.put("EJ.GMI.MNF", "找不到模块[%s]的id");
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
        M.put("EJ.GMI.MNF", "cannot find the id of  %s module");
    }

    @Override
    public String get(String key)
    {
        if(!M.containsKey(key)) return null;
        return M.get(key);
    }
}

