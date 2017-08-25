package org.pub.util.https;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;
import java.net.UnknownServiceException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.pub.util.json.JsonUtil;

public class HttpClientUtil {
	private static final Logger logger = Logger.getLogger("HttpClientUtil");

	/**
	 * HTTP GET 请求
	 * @param req_url 请求地址
	 * @param enc  请求编码  默认UTF-8
	 * @param out_str 请求参数数据
	 * @return
	 * @throws ConnectException
	 * @throws SocketTimeoutException
	 * @throws MalformedURLException
	 * @throws FileNotFoundException
	 * @throws UnknownHostException
	 * @throws UnknownServiceException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	public static String httpGet(String req_url, String enc, String out_str)
			throws ConnectException, SocketTimeoutException,
			MalformedURLException, FileNotFoundException, UnknownHostException,
			UnknownServiceException, UnsupportedEncodingException, IOException {

		logger.info("Execute URL url=" + req_url + "-->" + out_str);
		String ret = HTTPUtil.http_request(req_url, HTTPUtil.REQ_METHOD_GET, out_str, enc);
		logger.info("Execute URL url=" + req_url + "-->" + ret);
		return ret;
	}
	
	/**
	 * HTTP GET 请求
	 * @param req_url 请求地址
	 * @param enc  请求编码  默认UTF-8
	 * @param parameters 请求参数集合
	 * @return
	 * @throws ConnectException
	 * @throws SocketTimeoutException
	 * @throws MalformedURLException
	 * @throws FileNotFoundException
	 * @throws UnknownHostException
	 * @throws UnknownServiceException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	public static String httpGet(String req_url, String enc, Map<String, Object> parameters)
			throws ConnectException, SocketTimeoutException,
			MalformedURLException, FileNotFoundException, UnknownHostException,
			UnknownServiceException, UnsupportedEncodingException, IOException {

		logger.info("Execute URL url=" + req_url + "-->" + parameters);
		String out_str = JsonUtil.getMapToJson(parameters);
		String ret = HTTPUtil.http_request(req_url, HTTPUtil.REQ_METHOD_GET, out_str, enc);
		logger.info("Execute URL url=" + req_url + "-->" + ret);
		return ret;
	}

	/**
	 * HTTP POST 请求
	 * @param req_url 请求地址
	 * @param enc  请求编码  默认UTF-8
	 * @param parameters 请求参数集合
	 * @return
	 * @throws ConnectException
	 * @throws SocketTimeoutException
	 * @throws MalformedURLException
	 * @throws FileNotFoundException
	 * @throws UnknownHostException
	 * @throws UnknownServiceException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	public static String httpPost(String req_url, String enc, Map<String, Object> parameters) throws ConnectException, SocketTimeoutException, MalformedURLException, FileNotFoundException, UnknownHostException, UnknownServiceException, UnsupportedEncodingException, IOException {

		logger.info("Execute URL url=" + req_url + "-->" + parameters);
		String out_str = JsonUtil.getMapToJson(parameters);
		String ret = HTTPUtil.http_request(req_url, HTTPUtil.REQ_METHOD_POST, out_str, enc);
		logger.info("Execute URL url=" + req_url + "-->" + ret);
		return ret;
	}

	/**
	 * HTTP POST 请求
	 * @param req_url 请求地址
	 * @param enc  请求编码  默认UTF-8
	 * @param out_str 请求参数
	 * @return
	 * @throws ConnectException
	 * @throws SocketTimeoutException
	 * @throws MalformedURLException
	 * @throws FileNotFoundException
	 * @throws UnknownHostException
	 * @throws UnknownServiceException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	public static String httpPost(String req_url, String enc, String out_str) throws ConnectException, SocketTimeoutException, MalformedURLException, FileNotFoundException, UnknownHostException, UnknownServiceException, UnsupportedEncodingException, IOException {

		logger.info("Execute URL url=" + req_url + "-->" + out_str);
		String ret = HTTPUtil.http_request(req_url, HTTPUtil.REQ_METHOD_POST, out_str, enc);
		logger.info("Execute URL url=" + req_url + "-->" + ret);
		return ret;
	}

	/**
	 * HTTPS POST 请求
	 * @param req_url 请求地址
	 * @param enc  请求编码  默认UTF-8
	 * @param parameters 请求参数集合
	 * @return
	 * @throws IOException 
	 * @throws UnsupportedEncodingException 
	 * @throws UnknownServiceException 
	 * @throws UnknownHostException 
	 * @throws FileNotFoundException 
	 * @throws MalformedURLException 
	 * @throws SocketTimeoutException 
	 * @throws ConnectException 
	 */
	public static String httpsPost(String req_url, String enc, Map<String, Object> parameters) throws ConnectException, SocketTimeoutException, MalformedURLException, FileNotFoundException, UnknownHostException, UnknownServiceException, UnsupportedEncodingException, IOException {

		logger.info("Execute URL url=" + req_url + "-->" + parameters);
		String out_str = JsonUtil.getMapToJson(parameters);
		String ret = HTTPSUtil.https_request(req_url, HTTPUtil.REQ_METHOD_POST, out_str, null,enc);
		logger.info("Execute URL url=" + req_url + "-->" + ret);
		return ret;
	}
	/**
	 * HTTPS POST 请求
	 * @param req_url 请求地址
	 * @param enc  请求编码  默认UTF-8
	 * @param out_str 请求参数
	 * @return
	 * @throws IOException 
	 * @throws UnsupportedEncodingException 
	 * @throws UnknownServiceException 
	 * @throws UnknownHostException 
	 * @throws FileNotFoundException 
	 * @throws MalformedURLException 
	 * @throws SocketTimeoutException 
	 * @throws ConnectException 
	 */
	public static String httpsPost(String req_url, String enc, String out_str) throws ConnectException, SocketTimeoutException, MalformedURLException, FileNotFoundException, UnknownHostException, UnknownServiceException, UnsupportedEncodingException, IOException {

		logger.info("Execute URL url=" + req_url + "-->" + out_str);
		String ret = HTTPSUtil.https_request(req_url, HTTPUtil.REQ_METHOD_POST, out_str, null,enc);
		logger.info("Execute URL url=" + req_url + "-->" + ret);
		return ret;
	}

	/**
	 * HTTPS Get 请求
	 * @param req_url 请求地址
	 * @param enc  请求编码  默认UTF-8
	 * @param parameters 请求参数集合
	 * @return
	 * @throws IOException 
	 * @throws UnsupportedEncodingException 
	 * @throws UnknownServiceException 
	 * @throws UnknownHostException 
	 * @throws FileNotFoundException 
	 * @throws MalformedURLException 
	 * @throws SocketTimeoutException 
	 * @throws ConnectException 
	 */
	public static String httpsGet(String req_url, String enc, Map<String, Object> parameters) throws ConnectException, SocketTimeoutException, MalformedURLException, FileNotFoundException, UnknownHostException, UnknownServiceException, UnsupportedEncodingException, IOException {

		logger.info("Execute URL url=" + req_url + "-->" + parameters);
		String out_str = JsonUtil.getMapToJson(parameters);
		String ret = HTTPSUtil.https_request(req_url, HTTPUtil.REQ_METHOD_GET, out_str, null,enc);
		logger.info("Execute URL url=" + req_url + "-->" + ret);
		return ret;
	}

	/**
	 * HTTPS Get 请求
	 * @param req_url 请求地址
	 * @param enc  请求编码  默认UTF-8
	 * @param parameters 请求参数集合
	 * @return
	 * @throws IOException 
	 * @throws UnsupportedEncodingException 
	 * @throws UnknownServiceException 
	 * @throws UnknownHostException 
	 * @throws FileNotFoundException 
	 * @throws MalformedURLException 
	 * @throws SocketTimeoutException 
	 * @throws ConnectException 
	 */
	public static String httpsGet(String req_url, String enc, String out_str) throws ConnectException, SocketTimeoutException, MalformedURLException, FileNotFoundException, UnknownHostException, UnknownServiceException, UnsupportedEncodingException, IOException {

		logger.info("Execute URL url=" + req_url + "-->" + out_str);
		String ret = HTTPSUtil.https_request(req_url, HTTPUtil.REQ_METHOD_GET, out_str, null,enc);
		logger.info("Execute URL url=" + req_url + "-->" + ret);
		return ret;
	}
	
	
	/**
     * 发起http请求获取文件流并下载，返回结果true/false
     * @param req_url 请求地址
     * @param req_method 请求方式（GET、POST）
     * @param out_str 提交的数据
     * @param charset 字符集
     * @param fileUrl 生成文件目录
     * @return 返回数据boolean
     */
    public static Boolean http_request(String req_url, String req_method, String out_str, String charset,String fileUrl){
        boolean b = true;
        try{
            if (charset == null || charset.trim().equals("")) {
                charset = "UTF-8";
            }
            URL url = new URL(req_url);
            HttpURLConnection http_conn = (HttpURLConnection) url.openConnection();
            //tp正文内，因此需要设为true, 默认情况下是false;
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
//            if (REQ_METHOD_GET.equalsIgnoreCase(req_method)) {
            http_conn.connect();
//            }
            // 当有数据需要提交时
            if (null != out_str) {
                OutputStreamWriter out = new OutputStreamWriter(http_conn.getOutputStream());
                out.write(out_str);
                out.flush();
                out.close();
            }
            // 将返回的输入流转换成字符串
            InputStream is = http_conn.getInputStream();
            File file = new File(fileUrl);// 要写入的文本地址
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = is.read(buffer, 0, 8192)) != -1) {
             os.write(buffer, 0, bytesRead);
            }
            os.close();
            is.close();
        }catch(Exception e){
            e.printStackTrace();
            b = false;
        }
        return b;
    }
	
	
	public static void main(String[] args) throws Exception {
		// String httpsUrl =
		// "https://www.ayjrjy.com/escrow/web/ebc/rechargeResult";
		String httpsUrl = "https://localhost:8443/testssl/test";
		String httpUrl = "http://192.168.0.172:8070/SMSPlatform/users/register";

		String param = "ret=00&merchent=012";
		Map<String, Object> m2 = new HashMap<String, Object>();
		m2.put("username", "123123");
//		System.out.println(httpsPost(httpsUrl, null, m2));
		System.out.println(httpPost(httpUrl, null, m2));
	}
}
