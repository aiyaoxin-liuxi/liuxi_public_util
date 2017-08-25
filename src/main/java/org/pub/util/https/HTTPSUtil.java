package org.pub.util.https;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;
import java.net.UnknownServiceException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * http请求工具类
 */
public class HTTPSUtil {

    /**
     * 默认值.字符集
     */
    public static final String default_charset = "UTF-8";
    public static final String REQ_METHOD_GET = "GET";
    public static final String REQ_METHOD_POST = "POST";

    /**
     * 发起HTTPS.GET请求并获取结果
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
    public static String do_https_get(String req_url)
            throws MalformedURLException, ConnectException, SocketTimeoutException, FileNotFoundException,
            UnknownHostException, UnknownServiceException,
            UnsupportedEncodingException, IOException {
        return https_request(req_url, REQ_METHOD_GET, null, null, default_charset);
    }

    /**
     * 发起HTTPS.GET请求并获取结果
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
    public static String do_https_get(String req_url, String out_str)
            throws MalformedURLException, ConnectException, SocketTimeoutException, FileNotFoundException,
            UnknownHostException, UnknownServiceException,
            UnsupportedEncodingException, IOException {
        return https_request(req_url, REQ_METHOD_GET, out_str, null, default_charset);
    }

    /**
     * 发起HTTPS.GET请求并获取结果
     *
     * @param req_url 请求地址
     * @param out_str 提交的数据
     * @param ssf SSL工厂
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
    public static String do_https_get(String req_url, String out_str, SSLSocketFactory ssf)
            throws MalformedURLException, ConnectException, SocketTimeoutException, FileNotFoundException,
            UnknownHostException, UnknownServiceException,
            UnsupportedEncodingException, IOException {
        return https_request(req_url, REQ_METHOD_GET, out_str, ssf, default_charset);
    }

    /**
     * 发起HTTPS.POST请求并获取结果
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
    public static String do_https_post(String req_url)
            throws MalformedURLException, ConnectException, SocketTimeoutException, FileNotFoundException,
            UnknownHostException, UnknownServiceException,
            UnsupportedEncodingException, IOException {
        return https_request(req_url, REQ_METHOD_POST, null, null, default_charset);
    }

    /**
     * 发起HTTPS.POST请求并获取结果
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
    public static String do_https_post(String req_url, String out_str)
            throws MalformedURLException, ConnectException, SocketTimeoutException, FileNotFoundException,
            UnknownHostException, UnknownServiceException,
            UnsupportedEncodingException, IOException {
        return https_request(req_url, REQ_METHOD_POST, out_str, null, default_charset);
    }

    /**
     * 发起HTTPS.POST请求并获取结果
     *
     * @param req_url 请求地址
     * @param out_str 提交的数据
     * @param ssf SSL工厂
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
    public static String do_https_post(String req_url, String out_str, SSLSocketFactory ssf)
            throws MalformedURLException, ConnectException, SocketTimeoutException, FileNotFoundException,
            UnknownHostException, UnknownServiceException,
            UnsupportedEncodingException, IOException {
        return https_request(req_url, REQ_METHOD_POST, out_str, ssf, default_charset);
    }

    /**
     * 发起http请求并获取结果
     *
     * @param url 请求地址
     * @param method 请求方式（GET、POST）
     * @param out_str 提交的数据
     * @param ssf SSL工厂
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
    public static String https_request(String url, String method, String out_str, SSLSocketFactory ssf, String charset)
            throws MalformedURLException, ConnectException, SocketTimeoutException, FileNotFoundException,
            UnknownHostException, UnknownServiceException,
            UnsupportedEncodingException, IOException {
    	if(charset==null || charset.trim().equals("")){
    		charset = default_charset;
    	}
        String ctype = "application/json;charset=" + charset;
        byte[] content = {};
        if (out_str != null) {
            content = out_str.getBytes(charset);
        }
        int connectTimeout = 60 * 1000;
        int readTimeout = 60 * 1000;
        return do_https(url, method, ctype, content, connectTimeout, readTimeout);
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

    public static String do_https(String url, String method, String ctype, byte[] content, int connectTimeout, int readTimeout) {
        String rsp = null;
        try {
            HttpsURLConnection conn = null;
            OutputStream out = null;
            SSLContext ctx = SSLContext.getInstance("TLS");
            ctx.init(new KeyManager[0], new TrustManager[]{new DefaultTrustManager()}, new SecureRandom());
            SSLContext.setDefault(ctx);

            conn = getConnection(new URL(url), method, ctype);
            conn.setHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            conn.setConnectTimeout(connectTimeout);
            conn.setReadTimeout(readTimeout);
            out = conn.getOutputStream();
            out.write(content);
            rsp = getResponseAsString(conn);
            out.close();
            conn.disconnect();
        } catch (Exception ex) {
//            ex.printStackTrace();
        }
        return rsp;
    }

    private static class DefaultTrustManager implements X509TrustManager {

        @Override
        public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

    }

    private static HttpsURLConnection getConnection(URL url, String method, String ctype)
            throws IOException {
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setRequestMethod(method);
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setRequestProperty("Accept", "text/xml,text/javascript,text/html");
        conn.setRequestProperty("User-Agent", "stargate");
        conn.setRequestProperty("Content-Type", ctype);
        return conn;
    }

    protected static String getResponseAsString(HttpURLConnection conn) throws IOException {
        String charset = getResponseCharset(conn.getContentType());
        InputStream es = conn.getErrorStream();
        if (es == null) {
            return getStreamAsString(conn.getInputStream(), charset);
        } else {
            String msg = getStreamAsString(es, charset);
            if (msg == null || msg.equals("")) {
                throw new IOException(conn.getResponseCode() + ":" + conn.getResponseMessage());
            } else {
                throw new IOException(msg);
            }
        }
    }

    private static String getStreamAsString(InputStream stream, String charset) throws IOException {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream, charset));
            StringWriter writer = new StringWriter();
            char[] chars = new char[256];
            int count = 0;
            while ((count = reader.read(chars)) > 0) {
                writer.write(chars, 0, count);
            }
            return writer.toString();
        } finally {
            if (stream != null) {
                stream.close();
            }
        }
    }

    private static String getResponseCharset(String ctype) {
        String charset = default_charset;
        if (ctype != null && !ctype.equals("")) {
            String[] params = ctype.split(";");
            for (String param : params) {
                param = param.trim();
                if (param.startsWith("charset")) {
                    String[] pair = param.split("=", 2);
                    if (pair.length == 2) {
                        if (pair[1] != null && !pair[1].equals("")) {
                            charset = pair[1].trim();
                        }
                    }
                    break;
                }
            }
        }
        return charset;
    }
}
