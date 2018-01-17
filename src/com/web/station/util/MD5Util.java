package com.web.station.util;

import org.apache.commons.codec.binary.Hex;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
    public static String encode(String origin, String charsetname){
        String resultString = null;
        resultString = new String(origin);
        MessageDigest md;
        try{
            md = MessageDigest.getInstance("MD5");
        }catch(NoSuchAlgorithmException e){
            throw new RuntimeException(e);
        }
        if(charsetname == null || "".equals(charsetname)){
            resultString = Hex.encodeHexString(md.digest(resultString.getBytes()));
        }else{
            try{
                resultString = Hex.encodeHexString(md.digest(resultString.getBytes(charsetname)));
            }catch(UnsupportedEncodingException e){
                throw new RuntimeException(e);
            }
        }
        return resultString;
    }

    public static String encode(String origi){
        // String st=origi+"45678";
        return encode(origi,"utf-8");
    }

    public static String encodeStrong(String origi){
        if (origi!=null){
            int length = origi.length();
            String str=origi+String.valueOf(length)+origi;
            //123456 6 123456
            return encode(str,"utf-8");
        }
        return encode("12345678","utf-8");
    }
}
