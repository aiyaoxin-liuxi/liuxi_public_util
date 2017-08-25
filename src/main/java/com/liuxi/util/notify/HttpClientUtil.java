package com.liuxi.util.notify;

import java.net.URI;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

public class HttpClientUtil {
	public static final String CHARSET = "UTF-8";
	private static final Logger logger = Logger.getLogger("HttpClientUtil");

	public static CloseableHttpClient createHttpsClient() throws Exception {
		X509TrustManager x509mgr = new X509TrustManager() {
			@Override
			public void checkClientTrusted(X509Certificate[] xcs, String string) {
			}

			@Override
			public void checkServerTrusted(X509Certificate[] xcs, String string) {
			}

			@Override
			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}
		};
		SSLContext sslContext = SSLContext.getInstance("TLS");
		sslContext.init(null, new TrustManager[] { x509mgr }, null);
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
				sslContext,
				SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
		return HttpClients.custom().setSSLSocketFactory(sslsf).build();
	}

	public static CloseableHttpClient createSSLClientDefault() throws Exception {

		SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null,
				new TrustStrategy() {
					// 信任所有
					public boolean isTrusted(X509Certificate[] chain,
							String authType) throws CertificateException {
						return true;
					}
				}).build();
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
				sslContext,
				SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
		return HttpClients.custom().setSSLSocketFactory(sslsf).build();
	}

	public static String httpGet(String url, String ordersn, String uuid,
			Map<String, String> params) {
		CloseableHttpClient httpclient = HttpClientBuilder.create().build();
		StringBuffer sb = new StringBuffer();
		if (!url.contains("?")) {
			sb.append("?");
		}
		if (params.size() > 0) {
			for (String key : params.keySet()) {
				sb.append(sb.length() > 0 ? "&" : "");
				sb.append(key).append("=").append(params.get(key));
			}
		}
		String ret = "";
		try {
			HttpGet httpget = new HttpGet(url + sb.toString());
			// 配置请求的超时设置
			RequestConfig requestConfig = RequestConfig.custom()
					.setConnectionRequestTimeout(120000).setConnectTimeout(120000)
					.setSocketTimeout(120000).build();
			httpget.setConfig(requestConfig);
			CloseableHttpResponse response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
			ret = EntityUtils.toString(entity);// , "utf-8");
			httpget.releaseConnection();
			logger.info("Execute Notify Result ordersn=" + ordersn + "-->uuid=" + uuid
					+ "-->" + ret);
		} catch (Exception e) {
			logger.error("HTTP get Exception uuid=" + uuid + ";ordersn="
					+ ordersn, e);
		}
		return ret;
	}

	public static String httpPost(String url, String ordersn, String uuid,
			Map<String, Object> parameters) {
		CloseableHttpClient httpclient = HttpClientBuilder.create().build();
		HttpPost httppost = new HttpPost(url);
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(120000).setConnectTimeout(120000).build();//设置请求和传输超时时间
		httppost.setConfig(requestConfig);
//		httppost.setHeader(HttpHeaders.CONTENT_TYPE,"application/x-www-form-urlencoded");
//		httppost.setHeader(HttpHeaders.CONTENT_TYPE,"application/json");
//		httppost.setHeader(HttpHeaders.CONTENT_TYPE,"text/html:charset=UTF-8");
//		httppost.setHeader(HttpHeaders.USER_AGENT,"Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.6) Gecko/20100625 Firefox/3.6.6");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		if (parameters.size() > 0) {
			for (String key : parameters.keySet()) {
				if(key != null && parameters.get(key)!=null){
					params.add(new BasicNameValuePair(key, parameters.get(key).toString()));
				}
			}
		}
		String ret = "";
		try {
			httppost.setEntity(new UrlEncodedFormEntity(params,CHARSET));
			CloseableHttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			ret = EntityUtils.toString(entity, CHARSET);
			httppost.releaseConnection();
			logger.info("Execute Notify Result ordersn=" + ordersn + "-->uuid=" + uuid
					+ "-->" + ret);
		} catch (Exception e) {
			logger.error("HTTP post Exception uuid=" + uuid + ";ordersn="
					+ ordersn, e);
		}
		return ret;
	}

	// 应用
	public static String httpsPost(String url, String ordersn, String uuid,
			Map<String, Object> map) {
		String ret = null;
		try {
			CloseableHttpClient httpClient = createSSLClientDefault();
			List<NameValuePair> pairs = null;
			if (map != null && !map.isEmpty()) {
				pairs = new ArrayList<NameValuePair>(map.size());
				for (Entry<String, Object> entry : map.entrySet()) {
					Object value = entry.getValue();
					if (entry.getKey() != null && value != null) {
						pairs.add(new BasicNameValuePair(entry.getKey(), value.toString()));
					}
				}
			}
			HttpPost httpPost = new HttpPost(new URI(url));
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(120000).setConnectTimeout(120000).build();//设置请求和传输超时时间
			httpPost.setConfig(requestConfig);
			if (pairs != null && pairs.size() > 0) {
				httpPost.setEntity(new UrlEncodedFormEntity(pairs, CHARSET));
			}
			CloseableHttpResponse response = httpClient.execute(httpPost);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != 200) {
				httpPost.abort();
				throw new RuntimeException("HttpClient,error status code :"
						+ statusCode);
			}
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				ret = EntityUtils.toString(entity, CHARSET);
			}
			EntityUtils.consume(entity);
			response.close();
			logger.info("Execute Notify Result ordersn=" + ordersn + "-->uuid=" + uuid
					+ "-->" + ret);
		} catch (Exception e) {
			logger.error("HTTPS post Exeception uuid=" + uuid + ";ordersn="
					+ ordersn, e);
		}
		return ret;
	}
	
	public static void main1(String[] args) {
		for(int i = 0 ;i<10 ;i++){
			String notify_url = "http://www.junkun.net/yytRechargeNotifyurl.do";
			String source = "returncode=00&merchno=611100000309910&dsorderid=R2015061700023007427&amount=162800.00&orderid=150617131814167&transdate=20150617&transtime=131931&syssn=315704&dstbdatasign=7FBB5615FE73CE4242304A31F6D621382E16985D2AFD85989C6F5836DCE418467E03EB1B13091ABA85E70F88C1D3EC1F037DB798FC69A49C70F0C254419652A7A8629BC9221DF4E64470D00A02AC15AFEC572B39367514448FB16089AA273B94A0AB56A2D6F4D3361C08C1EFA41800C2D5D5F78A072FF8FDEC3C9CD59C530ED59FFC3E022B3030899148109FF1CB35C2A5B9F158BD12205BD3DC8904B36129A9";
			Map<String, Object> sPara = new LinkedHashMap<String, Object>();
			String[] sourceArray = source.split("&");
			for(String s : sourceArray){
				String[] sa = s.split("=");
				sPara.put(sa[0], sa[1]);
				System.out.println("通知参数："+sa[0]+"="+sa[1]);
			}
			String str = httpPost(notify_url, "11", "22", sPara);
			System.out.println("通知结果："+str);
		}
	}
	
	public static void main(String[] args) throws Exception {
		// String httpsUrl =
		// "https://www.ayjrjy.com/escrow/web/ebc/rechargeResult";
//		String httpsUrl = "https://localhost:8443/testssl/test";
		String httpUrl = "http://www.ynfcct.com/excsh-1.0.1/ebcCallbackAction!recharge.htm";
//		String httpUrl ="http://localhost/school_card_web/application/testNotify.html";
//		
		String param = "returncode=00&merchno=611100000310108&dsorderid=YBTEST2IN00000395&amount=100&dsyburl=http://www.ynfcct.com/excsh-1.0.1/ebcCallbackAction!recharge.htm&orderid=150702173310475&transdate=20150703&transtime=092533";
		String sign ="8AE3809F87CC2C3D8FB4FC6334794BEF9CD07183622747F40CC4700D25B60ABAA13CA3893A05E4AAB70E368E03D8EC9794A7674E6033A62A0D088264331EEF28E8F898C1FF7EE9DB52ECA840C9C2BAB4894B0FD16517DFBD381713C3E7C9B378ED6C4FA4D502418C65C5D5AF18A0BB02D8559FBC9A1E87F20FF8F72589EBA568B0EB77E752472B1FF592FCCCED2D1FB4991F70CB6CC594408E8FBC49E4379CA4BEACF110B2938B7E5557A467E6027D618322C18D911B147533FD278C52C8BC666F2E2745145CA7EA712963E90014FDA3A4AD722776F01366";
		Map<String, Object> m2 = new HashMap<String, Object>();
		m2.put("dstbdata", param);
		m2.put("dstbdatasign", sign);
		
		System.out.println("手动模拟异步通知是返回："+httpPost(httpUrl,"1111","2222",m2));
	}
}
