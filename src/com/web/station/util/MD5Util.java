package com.web.station.util;

import org.apache.commons.codec.binary.Hex;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密工具类
 */
public class MD5Util {
    public static String encode(String origin, String charsetName) {
        String resultString = null;
        resultString = new String(origin);
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        if (charsetName == null || "".equals(charsetName)) {
            resultString = Hex.encodeHexString(md.digest(resultString.getBytes()));
        } else {
            try {
                resultString = Hex.encodeHexString(md.digest(resultString.getBytes(charsetName)));
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }
        return resultString;
    }

    public static String encode(String origin) {
        // String str = origin+"45678";
        return encode(origin, "utf-8");
    }

    public static String encodeStrong(String origin) {
        if (origin != null) {
            int length = origin.length();
            String str = origin + String.valueOf(length) + origin;
            //123456 6 123456
            return encode(str, "utf-8");
        }
        return encode("12345678", "utf-8");
    }
}
