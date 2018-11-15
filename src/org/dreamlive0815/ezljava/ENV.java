package org.dreamlive0815.ezljava;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import org.dreamlive0815.util.FileUtil;

public abstract class ENV
{
    public static ENV instance = new DefaultENV();
    public static String NL = System.getProperty("line.separator");

    public static Object G(String key)
    {
        if(instance == null) return null;
        return instance.get(key);
    }

    public static String getWorkingDirectory()
    {
        String path = ENV.class.getProtectionDomain().getCodeSource().getLocation().getFile();
        path = path.substring(0, path.lastIndexOf("/") + 1);
        return path;
    }

    public Map<String, String> loadConfigFromFile(String filePath)
    {
        List<String> lines;
        Map<String, String> config = new HashMap<String, String>();
        try {
            lines = FileUtil.readAllLines(filePath);
        } catch(Exception e) {
            lines = new ArrayList<String>();
        }
        for (String s : lines) {
            String[] pair = s.split("=");
            String key = pair[0];
            if("".equals(key)) continue;
            String value = pair.length > 1 ? pair[1] : "";
            key = key.trim();
            value = value.trim();
            config.put(key, value);
        }
        return config;
    } 

    public abstract Object get(String key);
}

class DefaultENV extends ENV
{
    Map<String, Object> M = new HashMap<String, Object>();

    public DefaultENV()
    {
        M.put("sleep.longitude", 120.360179);
        M.put("sleep.latitude", 30.316397);
        M.put("course.longitude", 120.354217);
        M.put("course.latitude", 30.314918);
        
        Map<String, String> config = loadConfigFromFile(getWorkingDirectory() + "ezl.conf");
        M.putAll(config);
    }

    @Override
    public Object get(String key)
    {
        if(!M.containsKey(key)) return null;
        return M.get(key);
    }
}