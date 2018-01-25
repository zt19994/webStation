package com.web.station.util;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 文件下载的工具类
 */
public class DownloadUtil {
    private static final Logger logger = LoggerFactory.getLogger(DownloadUtil.class);

    /**
     * 下载
     *
     * @param response
     * @param data     string数组
     */
    public static String download(HttpServletResponse response, List<List<String>> data) {
        //文件名
        String fileName = Long.toString(System.currentTimeMillis());
        logger.info("fileName: " + fileName);
        //响应文件格式
        response.setContentType("application/vnd.ms-excel");
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();

        ServletOutputStream outputStream = null;
        String rFileName = null;
        try {
            //进行转码，支持中文名
            String codeFileName = new String(fileName.getBytes("gbk"), "iso-8859-1");
            logger.info("codeFileName: " + codeFileName);
            response.setHeader("context-disposition", "attachment;filename=" + codeFileName + ".xls");
            rFileName = "filename=" + codeFileName + ".xls";
            logger.info("filename=" + codeFileName + ".xls");
            //往表格中装数据
            //创建一个表格
            HSSFSheet sheet1 = hssfWorkbook.createSheet("sheet1");
            for (int i = 0; i < data.size(); i++) {
                //取出一行数据
                List<String> stringList = data.get(i);
                //创建一行
                HSSFRow row = sheet1.createRow(i);
                for (int i1 = 0; i1 < stringList.size(); i1++) {
                    //取出一行中的一个数据
                    String str = stringList.get(i1);
                    //创建一行中的一个格子
                    HSSFCell cell = row.createCell(i1);
                    //把数据装入格子
                    cell.setCellValue(str);
                }
            }
            outputStream = response.getOutputStream();
            hssfWorkbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("文件下载失败");
        } finally {
            try {
                //清除缓存
                //断言
                assert outputStream != null;
                outputStream.flush();
                //关闭资源
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        logger.info("文件下载成功");
        return rFileName;
    }


    /**
     * 将对象转化为string数组
     *
     * @param bean
     * @return
     * @throws Exception
     */
    public static String[] getValueRow(Object bean) throws Exception {
        //运用反射
        //1.获取字节码
        Class<?> aClass = bean.getClass();
        //2.拿到字节
        Field[] declaredFields = aClass.getDeclaredFields();
        int length = declaredFields.length;
        String[] rows = new String[length];

        //3.从字段里面取值
        for (int i = 0; i < length; i++) {
            Field declaredField = declaredFields[i];
            String fileName = declaredField.getName();
            String methodName = "get" + fileName.substring(0, 1).toUpperCase() + fileName.substring(1);
            Method method = aClass.getMethod(methodName);
            Object invoke = method.invoke(bean);
            String value = "";
            if (invoke != null) {
                value = String.valueOf(invoke);
            }
            rows[i] = value;
        }
        return rows;
    }
}
