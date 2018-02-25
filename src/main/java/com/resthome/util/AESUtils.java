package com.resthome.util;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by 一缕仙缘 on 2017-07-12.
 */
public class AESUtils
{
    public static String KEY="jiusanlianyangkw";

    public static String aesEncrypt(String str, String key) throws Exception {
        if (str == null || key == null) return null;
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key.getBytes("utf-8"), "AES"));
        byte[] bytes = cipher.doFinal(str.getBytes("utf-8"));
        return new BASE64Encoder().encode(bytes);
    }

    public static String aesDecrypt(String str, String key) throws Exception {
        if (str == null || key == null) return null;
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key.getBytes("utf-8"), "AES"));
        byte[] bytes = new BASE64Decoder().decodeBuffer(str);
        bytes = cipher.doFinal(bytes);
        return new String(bytes, "utf-8");
    }

    public static void main(String[] args) throws Exception
    {
       /* String forEntity = new RestTemplate().getForObject("http://localhost:8080/resthome//queryAllElders", String.class);
        System.out.println(forEntity);
        System.out.println(forEntity.contains("\""));
        System.out.println(forEntity.endsWith("\""));
        */

        System.out.println(aesEncrypt("{\"姓名\":\"tom\"'}",KEY));
        System.out.println(aesDecrypt("CpmdBszryxa1sgbywLMSJi3xRZZekrf7S5VgCXXXV4A=",KEY));


    }



}
