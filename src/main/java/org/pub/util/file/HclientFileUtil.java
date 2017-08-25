package org.pub.util.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;
import org.pub.util.properties.PropertiesUtil;

/**
 * 异步上传文件方法
 * @author RDuser
 *
 */
public class HclientFileUtil {
	private static Logger logger = Logger.getLogger(HclientFileUtil.class);
	private static String url = "/upload/httpClientSave.html";
	private static String del_url = "/upload/httpClientSave.html";
	/**
	 * 上传文件的方法：通过后台post请求的方式上传
	 * @param url 请求路径
	 * @param nid 文件所属服务器模块
	 * @param file 文件
	 * @return 返回服务器上所存储的文件路径
	 * @throws Exception 
	 */
	public static String uploadFileMethod(String nid,File file) throws Exception{
		if(!FileTypeUtil.checkFileType(file)){
			throw new Exception("您上传的文件无效，请重新上传！");
		}

		Properties p = PropertiesUtil.loadProperties("pub-util.properties");
		
		String image_server_url = p.getProperty("image_server_url");
		String fileType = FileTypeUtil.getFileByFile(file);
		String targetURL = image_server_url + url+"?"+"nid="+nid+"&prefix="+fileType; // -- 指定URL
		logger.info("文件上传，targetURL:"+targetURL);
		String retStr = "";
		PostMethod filePost = new PostMethod(targetURL);
		filePost.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"utf-8"); 
		try {
			Part[] parts = { new FilePart("upload", file)};
			filePost.setRequestEntity(new MultipartRequestEntity(parts, filePost.getParams()));
			HttpClient client = new HttpClient();
			client.getHttpConnectionManager().getParams().setConnectionTimeout(50000);
			int status = client.executeMethod(filePost);
			InputStream in = filePost.getResponseBodyAsStream();
			if (status == HttpStatus.SC_OK) {
				logger.info("上传成功");
				// 上传成功
			} else {
				logger.error("上传失败"+status);
				// 上传失败
			}
			// 处理内容
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String tempBf = null;
			StringBuffer html = new StringBuffer();
			try {
				while ((tempBf = reader.readLine()) != null) {
					html.append(tempBf);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			retStr = html.toString();
			logger.info("上传文件模块为："+nid+",返回文件路径"+retStr);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			filePost.releaseConnection();
		}
		return retStr;
	}
	
	/**
	 * 删除图片 （主要用于资料上传失败后的删除）
	 * @param url 请求的方法
	 * @param param 参数字符串
	 * @return
	 * @throws IOException 
	 */
	public static String deleteImg(String param) throws IOException{
		Properties p = PropertiesUtil.loadProperties("pub-util.properties");
		String image_server_url = p.getProperty("image_server_url");
		String targetURL = image_server_url+del_url+"?imgUrl="+param;
		PostMethod method = new PostMethod(targetURL);
		HttpClient client = new HttpClient();
		client.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
		String msg = "";
		try {
			int result = client.executeMethod(method);
			if(result==HttpStatus.SC_OK){
				msg = "成功";
			}else{
				msg = "失败";
			}
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return msg;
	}
	
	public static void main(String[] args) throws Exception {
		File file = new File("d://test1.jpg");
		System.out.println(HclientFileUtil.uploadFileMethod("userinfo", file));
	}
}