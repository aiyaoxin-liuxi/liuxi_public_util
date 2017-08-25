package com.liuxi.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;

import org.apache.log4j.Logger;


/**
 * Url 接口调用
 * @author 刘熙
 *
 */
public class UrlCommon {

	private static final Logger logger = Logger.getLogger(UrlCommon.class.getName());

	/**
	 * Creates a new instance of UrlCommon
	 */
	public UrlCommon() {
	}

	public static String getHtml(String urlString, String parms)
			throws Exception {
		if (urlString == null || urlString.trim().equals("")) {
			return "";
		}
		if (parms == null || parms.trim().equals("")) {
			return "";
		}
		parms = parms.replaceAll(" ", "%20");
		StringBuilder document = new StringBuilder();
		// try {
		URL url = new URL(urlString);
		HttpURLConnection url_conn = (HttpURLConnection) url.openConnection();
		url_conn.setRequestMethod("POST");
		url_conn.setDoOutput(true);// 是否输入参数
		
		byte[] bypes = parms.toString().getBytes();
		url_conn.getOutputStream().write(bypes);// 输入参数
		logger.info("gethtml--->" + url_conn);
		BufferedReader reader = new BufferedReader(new InputStreamReader(url_conn.getInputStream()));
		String line;
		while ((line = reader.readLine()) != null) {
			document.append(line).append("\r\n");
		}
		reader.close();
		// } catch (Exception ex) {
		// logger.error(ex.getMessage(), ex);
		// }
		return document.toString();
	}

	public static String getHtml(String urlString) {
		if (urlString == null || urlString.trim().equals("")) {
			return "";
		}
		urlString = urlString.replaceAll(" ", "%20");
		StringBuilder document = new StringBuilder();
		try {
			URL url = new URL(urlString);
			URLConnection url_conn = url.openConnection();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					url_conn.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				document.append(line).append("\r\n");
			}
			reader.close();
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
		}
		return document.toString();
	}


	
	public static void main(String args[]) {
		try {
			String notify_url = "http://localhost:8080/dhb/getAwardInfoAndInertAwardTable";
			String notify_html = UrlCommon.getHtml(notify_url,
					"awardInfo.product_id=123");
			logger.debug("notify_html1=" + notify_html);
			// }else{
			notify_html = UrlCommon.getHtml(notify_url);
			logger.debug("notify_html2=" + notify_html);
			// }
		} catch (Exception ex) {
			java.util.logging.Logger.getLogger(UrlCommon.class.getName()).log(
					Level.SEVERE, null, ex);
		}
	}

}
