package com.example.demokafka.util;

import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;


public class GzipUtils {
    static org.slf4j.Logger log = LoggerFactory.getLogger(GzipUtils.class);

    public static String GzipDecoder(String data) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(new BASE64Decoder().decodeBuffer(data));
            GZIPInputStream gzipInputStream = new GZIPInputStream(inputStream);
            byte[] bytes = new byte[256];
            int n;
            while ((n = gzipInputStream.read(bytes)) >= 0) {
                outputStream.write(bytes, 0, n);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.debug("解压数据大小：" + outputStream.toByteArray().length);
        log.debug("解压数据：" + outputStream);
        return outputStream.toString();
    }

    public static String GzipEncoder(String data) {
        String result = "";
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gzip;
        try {
            gzip = new GZIPOutputStream(out);
            gzip.write(data.getBytes("utf-8"));
            gzip.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        result = new BASE64Encoder().encode(out.toByteArray());
        System.out.println("------压缩成功------" + result);
        return result;
    }
}