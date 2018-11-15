package org.dreamlive0815.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class FileUtil
{
    public static List<String> readAllLines(String filePath) throws Exception
    {
        List<String> list = new ArrayList<String>();
        File file = new File(filePath);
        if(!file.exists())
            throw new Exception(String.format("file does not exist : %s", filePath));
        BufferedReader reader = new BufferedReader(new FileReader(file));
        try {
            String s;
            while ((s = reader.readLine()) != null) {
                list.add(s);
            }
        } catch(Exception e) {
            throw e;
        } finally {
            reader.close();
        }
        return list;
    }
}