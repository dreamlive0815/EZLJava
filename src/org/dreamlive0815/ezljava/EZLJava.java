package org.dreamlive0815.ezljava;

import java.io.IOException;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import okhttp3.SimpleClient;

public class EZLJava
{
    private final static String BA = "http://stu.zstu.edu.cn";
    private final static String URI = "/WebReport/ReportServer";
    private static final String xmlFormat = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><WorkBook><Version>6.5</Version><Report class=\"com.fr.report.WorkSheet\" name=\"0\"><CellElementList><C c=\"%s\" r=\"%s\"><O t=\"%s\"><![5b]CDATA[5b]%s[5d][5d]></O></C></CellElementList></Report></WorkBook>";

    public CredentialsVerifier credentialVerifier = new DefaultCredentialsVerifier();
    public ReportArgsVerifier reportArgsVerifier = new DefaultReportArgsVerifier();

    private SimpleClient client;
    private Map<String, String> moduleMap = new HashMap<String, String>();
    private String sessionId;
    private String macAddr;
    private String devName;
    private String username;
    private boolean loggedIn;

    public static String formatDouble(double d)
    {
        return String.format("%.6f", d);
    } 

    public EZLJava(String macAddr, String devName) throws Exception
    {
        this(macAddr, devName, false);
    }

    public EZLJava(String macAddr, String devName, boolean debug) throws Exception
    {
        client = new SimpleClient(debug);
        client.baseAddress = BA;

        this.macAddr = macAddr;
        this.devName = devName;
        loggedIn = false;
    }

    public void login(String username, String password) throws Exception
    {
        Credentials c = new Credentials();
        c.username = username; c.password = password;
        c.macAddress = macAddr; c.devName = devName;
        if(credentialVerifier != null) credentialVerifier.Verify(c);
        this.username = username;

        Map<String, Object> params = getBaseParams();
        params.put("cmd", "login");
        params.put("op", "fs_mobile_main");
        params.put("fr_remember", "false");
        params.put("fr_username", username);
        params.put("fr_password", password);
        params.put("macaddress", macAddr);
        params.put("devname", devName);
        params.put("username", username);
        params.put("password", password);

        String s = client.getString(URI, params);
        getJson(s);
        loggedIn = true;
        getIndexPage();
    }

    public JSONArray getIndexPage() throws Exception
    {
        Map<String, Object> params = getBaseParams();
        params.put("cmd", "module_getrootreports");
        params.put("id", "-1");
        params.put("op", "fs_main");

        String s = client.getString(URI, params);
        JSONArray jsonA = (JSONArray)getJson(s);
        getModuleMap(jsonA, "");
        return jsonA;
    }

    private JSONObject getSleepPage() throws Exception
    {
        assertLoggedIn();

        String moduleId = getModuleId(".寝室建设.归寝签到");
        Map<String, Object> params = getBaseParams();
        params.put("cmd", "entry_report");
        params.put("op", "fs_main");
        params.put("id", moduleId);
        String s = client.getString(URI, params);
        getJson(s);

        params = getBaseParams();
        params.put("cmd", "read_by_json");
        params.put("op", "fr_write");
        params.put("toVanCharts", "true");
        params.put("path", "/view/report");
        params.put("reportIndex", "0");
        params.put("pn", "1");

        s = client.getString(UC(URI, params));

        return (JSONObject)getJson(s);
    }

    public void sleepReport(SleepArgs args) throws Exception
    {
        //if(reportArgsVerifier != null) reportArgsVerifier.Verify(args);
        
        try {
            //getSleepPage();

            String lon = formatDouble(args.longitude);
            String la = formatDouble(args.latitude);
            String xml; String s;
            Map<String, Object> params = getBaseParams();
            
            params.put("cmd", "cal_write_cell");
            params.put("op", "fr_write");
            params.put("path", "/view/report");
            params.put("editcol", 2);
            params.put("editrow", 2);
            params.put("editReportIndex", "0");
            params.put("loadidxs", "[5b]0[5d]");
            xml = String.format(xmlFormat, 2, 2, 'S', lon);
            params.put("reportXML", xml);
            s = client.getString(UC(URI, params));
            //System.out.println(xml);
            
            params.put("editcol", 2);
            params.put("editrow", 3);
            xml =  String.format(xmlFormat, 2, 3, 'S', la);
            params.put("reportXML", xml);
            s = client.getString(UC(URI, params));
            //System.out.println(xml);
            



        } catch(Exception e) {
            closeSession();
            throw e;
        }
        closeSession();
    }

    private void assertLoggedIn() throws Exception
    {
        if(!loggedIn) throw new Exception(T.G("EJ.ALI.NLI"));
    }

    private void closeSession() throws Exception
    {
        if(sessionId == null) return;
        Map<String, Object> params = getBaseParams();
        params.put("op", "closesessionid");
        client.getString(UC(URI, params));
        sessionId = null;
    }

    private Map<String, Object> getBaseParams()
    {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("__device__", "android");
        //map.put("__mobileapp__", "yes");
        //map.put("isMobile", "yes");
        if(sessionId != null) map.put("sessionID", sessionId);
        return map;
    }

    private Object getJson(String content) throws Exception
    {
    	Object jsonObj = JSON.parse(content);
    	if(jsonObj instanceof JSONObject) {
    		JSONObject jsonO = (JSONObject)jsonObj;
    		if(jsonO.containsKey("errorCode")) {
    			int code = jsonO.getIntValue("errorCode");
    			String message = jsonO.getString("errorMsg");
    			throw new ReportException(code, message);
    		}
    		if(jsonO.containsKey("exception")) {
    			String exception = jsonO.getString("exception");
    			String message = jsonO.getString("message");
    			throw new ReportException(exception, message);
    		}

    		if(jsonO.containsKey("sessionid")) {
                sessionId = jsonO.getString("sessionid");
            }
    	}
		return jsonObj;
    }

    private String getModuleId(String path) throws Exception
    {
        if(!moduleMap.containsKey(path)) throw new Exception(String.format(T.G("EJ.GMI.MNF"), path));
        return moduleMap.get(path);
    }

    private void getModuleMap(JSONArray jsonA, String path)
    {
        for(Object obj : jsonA) {
            JSONObject jsonO = (JSONObject)obj;
            String text = jsonO.getString("text");
            String value = jsonO.getString("value");
            String realPath = String.format("%s.%s", path, text);
            moduleMap.put(realPath, value);
            JSONArray children = jsonO.getJSONArray("ChildNodes");
            if(children != null) getModuleMap(children, realPath);
        }
    }

    private String UC(String uri, Map<String, Object> params) throws Exception
    {
        return String.format("%s?%s", uri, SimpleClient.buildQueryString(params));
    }
}