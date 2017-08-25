package org.pub.util.https;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;
import java.net.UnknownServiceException;

/**
 * http请求工具类
 */
public class HTTPUtil {

    /**
     * 默认值.字符集
     */
    public static final String default_charset = "UTF-8";
    public static final String REQ_METHOD_GET = "GET";
    public static final String REQ_METHOD_POST = "POST";

    /**
     * 发起HTTP.GET请求并获取结果
     *
     * @param req_url 请求地址
     * @return 返回数据
     * @throws java.net.MalformedURLException
     * @throws java.net.ConnectException
     * @throws java.net.SocketTimeoutException
     * @throws java.io.FileNotFoundException
     * @throws java.net.UnknownHostException
     * @throws java.net.UnknownServiceException
     * @throws java.io.UnsupportedEncodingException
     * @throws java.io.IOException
     */
    public static String do_http_get(String req_url)
            throws MalformedURLException, ConnectException, SocketTimeoutException, FileNotFoundException,
            UnknownHostException, UnknownServiceException,
            UnsupportedEncodingException, IOException {
        return http_request(req_url, REQ_METHOD_GET, null, default_charset);
    }

    /**
     * 发起HTTP.GET请求并获取结果
     *
     * @param req_url 请求地址
     * @param out_str 提交的数据
     * @return 返回数据
     * @throws java.net.MalformedURLException
     * @throws java.net.ConnectException
     * @throws java.net.SocketTimeoutException
     * @throws java.io.FileNotFoundException
     * @throws java.net.UnknownHostException
     * @throws java.net.UnknownServiceException
     * @throws java.io.UnsupportedEncodingException
     * @throws java.io.IOException
     */
    public static String do_http_get(String req_url, String out_str)
            throws MalformedURLException, ConnectException, SocketTimeoutException, FileNotFoundException,
            UnknownHostException, UnknownServiceException,
            UnsupportedEncodingException, IOException {
        return http_request(req_url, REQ_METHOD_GET, out_str, default_charset);
    }

    /**
     * 发起HTTP.POST请求并获取结果
     *
     * @param req_url 请求地址
     * @return 返回数据
     * @throws java.net.MalformedURLException
     * @throws java.net.ConnectException
     * @throws java.net.SocketTimeoutException
     * @throws java.io.FileNotFoundException
     * @throws java.net.UnknownHostException
     * @throws java.net.UnknownServiceException
     * @throws java.io.UnsupportedEncodingException
     * @throws java.io.IOException
     */
    public static String do_http_post(String req_url)
            throws MalformedURLException, ConnectException, SocketTimeoutException, FileNotFoundException,
            UnknownHostException, UnknownServiceException,
            UnsupportedEncodingException, IOException {
        return http_request(req_url, REQ_METHOD_POST, null, default_charset);
    }

    /**
     * 发起HTTP.POST请求并获取结果
     *
     * @param req_url 请求地址
     * @param out_str 提交的数据
     * @return 返回数据
     * @throws java.net.MalformedURLException
     * @throws java.net.ConnectException
     * @throws java.net.SocketTimeoutException
     * @throws java.io.FileNotFoundException
     * @throws java.net.UnknownHostException
     * @throws java.net.UnknownServiceException
     * @throws java.io.UnsupportedEncodingException
     * @throws java.io.IOException
     */
    public static String do_http_post(String req_url, String out_str)
            throws MalformedURLException, ConnectException, SocketTimeoutException, FileNotFoundException,
            UnknownHostException, UnknownServiceException,
            UnsupportedEncodingException, IOException {
        return http_request(req_url, REQ_METHOD_POST, out_str, default_charset);
    }

    /**
     * 发起http请求并获取结果
     *
     * @param req_url 请求地址
     * @param req_method 请求方式（GET、POST）
     * @param out_str 提交的数据
     * @param charset 字符集
     * @return 返回数据
     * @throws java.net.MalformedURLException
     * @throws java.net.ConnectException
     * @throws java.net.SocketTimeoutException
     * @throws java.io.FileNotFoundException
     * @throws java.net.UnknownHostException
     * @throws java.net.UnknownServiceException
     * @throws java.io.UnsupportedEncodingException
     * @throws java.io.IOException
     */
    public static String http_request(String req_url, String req_method, String out_str, String charset)
            throws MalformedURLException, ConnectException, SocketTimeoutException, FileNotFoundException,
            UnknownHostException, UnknownServiceException,
            UnsupportedEncodingException, IOException {
        StringBuilder str_buid = new StringBuilder();

        if (charset == null || charset.trim().equals("")) {
            charset = default_charset;
        }
        URL url = new URL(req_url);
        HttpURLConnection http_conn = (HttpURLConnection) url.openConnection();
        //http正文内，因此需要设为true, 默认情况下是false;
        http_conn.setDoOutput(true);
        //设置是否从httpUrlConnection读入，默认情况下是true;
        http_conn.setDoInput(true);
        //Post请求不能使用缓存
        http_conn.setUseCaches(false);
        //设定请求的方法为"POST"，默认是GET
        http_conn.setRequestMethod(req_method);
        //连接主机的超时时间（单位：毫秒）
        http_conn.setConnectTimeout(60 * 1000);
        //从主机读取数据的超时时间（单位：毫秒）
        http_conn.setReadTimeout(60 * 1000);
        //设置通用的请求属性
        http_conn.setRequestProperty("Accept", "*/*");
        http_conn.setRequestProperty("Connection", "Keep-Alive");
        http_conn.setRequestProperty("Cache-Control", "no-cache");
        http_conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        http_conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; Foxy/1; .NET CLR 2.0.50727; MEGAUPLOAD 1.0)");
//        if (REQ_METHOD_GET.equalsIgnoreCase(req_method)) {
        http_conn.connect();
//        }
        // 当有数据需要提交时
        if (null != out_str) {
            OutputStreamWriter out = new OutputStreamWriter(http_conn.getOutputStream());
            out.write(java.net.URLEncoder.encode(out_str, charset));
            out.flush();
            out.close();
        }
        // 将返回的输入流转换成字符串
        InputStream is = http_conn.getInputStream();
        InputStreamReader isr = new InputStreamReader(is, charset);
        BufferedReader br = new BufferedReader(isr);
        String str = null;
        while ((str = br.readLine()) != null) {
            str_buid.append(str);
        }
        br.close();
        isr.close();
        is.close();
        is = null;
        // } catch (ConnectException ce) {
        // //log.error("Weixin server connection timed out.");
        // } catch (Exception e) {
        // //log.error("https request error:{}", e);
        // }
        return str_buid.toString();
    }

    /**
     * 处理URL有关"?","&"连接符格式，无“?”时增加，有时（最后一个，不处理，非最后，增加“&”）
     *
     * @param url 原始URL
     * @return 处理过的URL
     */
    public static String process_url_link(String url) {
        if (url != null) {
            if (url.contains("?")) {
                if (url.indexOf("?") != url.length() - 1) {
                    url += "&";
                }
            } else {
                url += "?";
            }
        }
        return url;
    }

}
