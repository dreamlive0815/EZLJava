package org.dreamlive0815.ezljava;

import java.util.Map;
import java.util.HashMap;

public abstract class ENV
{
    public static ENV instance = new DefaultENV();

    public static Object G(String key)
    {
        if(instance == null) return null;
        return instance.get(key);
    }

    public abstract Object get(String key);
}

class DefaultENV extends ENV
{
    Map<String, Object> M = new HashMap<String, Object>();

    public DefaultENV()
    {
        M.put("RAG.SLO", 120.360179);
        M.put("RAG.SLA", 30.316397);
        M.put("RAG.CLO", 120.354217);
        M.put("RAG.CLA", 30.314918);
    }

    @Override
    public Object get(String key)
    {
        if(!M.containsKey(key)) return null;
        return M.get(key);
    }
}