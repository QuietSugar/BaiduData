package com.maybe.main;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * 调用上传servlet并上传文件 工具类
 * @author Administrator
 *
 */
public class FormUpload {

	//protected static Logger logger = Logger.getLogger(FormUpload.class);
	/**
     * 上传文件
     * 
     * @param urlStr
     * @param textMap
     * @param fileMap
     * @return
     */ 
	public static String formUpload(String urlStr) { 

    	
        String res = ""; 
        HttpURLConnection conn = null; 
        String BOUNDARY = "---------------------------123821742118716"; //boundary就是request头和上传文件内容的分隔符 
        try { 
            URL url = new URL(urlStr); 
            conn = (HttpURLConnection) url.openConnection(); 
            conn.setConnectTimeout(5000); //设置连接超时时间,毫秒
            conn.setReadTimeout(30000); //设置流读取超时时间,毫秒
            conn.setDoOutput(true); 
            conn.setDoInput(true); 
            conn.setUseCaches(false); 
            conn.setRequestMethod("POST"); 
            conn.setRequestProperty("Connection", "Keep-Alive"); 
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)");
            //设置头信息,以下表示上传文件
            conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY); 
   
            OutputStream out = new DataOutputStream(conn.getOutputStream()); 
            
            
            byte[] endData = ("\r\n--" + BOUNDARY + "--\r\n").getBytes(); 
            out.write(endData); 
            out.flush(); 
            out.close(); 
   
            // 读取返回数据 
            StringBuffer strBuf = new StringBuffer(); 
            BufferedReader reader = new BufferedReader(new InputStreamReader( 
                    conn.getInputStream())); 
            String line = null; 
            while ((line = reader.readLine()) != null) { 
                strBuf.append(line).append("\n"); 
            } 
            res = strBuf.toString(); 
            //判断调用服务端servlet上传txt文件是否成功
            if(res=="error\n"||res.equals("error\n")){
            }else if (res=="success\n"||res.equals("success\n")){
            }
            
            reader.close(); 
            reader = null; 
            
        } catch (Exception e) { 
        	System.out.println("发送POST请求出错。地址：" + urlStr+"错误信息："+e.getMessage());
        } finally { 
            if (conn != null) { 
                conn.disconnect(); 
                conn = null; 
            } 
        } 
        return res; 
    } 
}
