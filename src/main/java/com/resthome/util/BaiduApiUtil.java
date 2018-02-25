package com.resthome.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by 一缕仙缘 on 2017-08-29.
 */
public class BaiduApiUtil
{
    private static Logger logger = LoggerFactory.getLogger(BaiduApiUtil.class);

    public static String connectURL(String dest_url)
    {
        String rec_string = "";
        URL url = null;
        HttpURLConnection urlconn = null;
        OutputStream out = null;
        BufferedReader rd = null;
        try
        {
            url = new URL(dest_url);
            urlconn = (HttpURLConnection) url.openConnection();
            urlconn.setReadTimeout(1000 * 30);
            //urlconn.setRequestProperty("content-type", "text/html;charset=UTF-8");
            urlconn.setRequestMethod("GET");

            rd = new BufferedReader(new InputStreamReader(urlconn.getInputStream()));
            StringBuffer sb = new StringBuffer();
            int ch;
            while ((ch = rd.read()) > -1)
                sb.append((char) ch);
            rec_string = sb.toString();
        }
        catch (Exception e)
        {
            logger.error(" transformat coordinate error", e);
            return "";
        }
        finally
        {
            try {
                if (out != null) {
                    out.close();
                }
                if (urlconn != null) {
                    urlconn.disconnect();
                }
                if (rd != null) {
                    rd.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return rec_string;
    }

}
