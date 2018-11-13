package org.dreamlive0815.ezljava;

import java.security.MessageDigest;

public class HashUtil
{
    public static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
    public static String charset = "UTF-8";

    public static String SHA1(String s) throws Exception
    {
        MessageDigest digest = MessageDigest.getInstance("SHA1");
        digest.update(s.getBytes(charset));
        return getHEXString(digest.digest());
    }

    public static boolean Verify(String hash, String s) throws Exception
    {
        if(hash == null) return false;
        String h = SHA1(s);
        return hash.equals(h);
    }

    private static String getHEXString(byte[] bytes)
    {
        int len = bytes.length;
        StringBuilder sb = new StringBuilder(2 * len);
        for(int i = 0; i < len; i++) {
            int b = bytes[i];
            if(b < 0) b = b + 256;
            sb.append(HEX_DIGITS[(b >> 4) & 0x0f]);
            sb.append(HEX_DIGITS[b & 0x0f]);
        }
        return sb.toString();
    }
}