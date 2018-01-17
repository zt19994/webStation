package com.web.station.test;

import com.web.station.util.MD5Util;
import org.junit.Test;

public class MD5UtilTest {

    /**
    * 测试MD5加密
    */
    @Test
    public void testMD5Util(){
        String encode = MD5Util.encode("admin");
        System.out.println(encode);
    }
}
