package org.pub.util.security;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Date;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

import sun.misc.BASE64Decoder;

/**
 * <p>
 * Title: RSA非对称型加密的公钥和私钥
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2003
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author not attributable
 * @version 1.0
 */

public class RSAUtil {
	static Logger logger = Logger.getLogger(RSAUtil.class);
	private KeyPairGenerator kpg = null;
	private KeyPair kp = null;
	private PublicKey public_key = null;
	private PrivateKey private_key = null;
	private static String default_enc = "UTF-8";

	/**
	 * 构造函数
	 * 
	 * @param in
	 *            指定密匙长度（取值范围：512～2048）
	 * @throws NoSuchAlgorithmException
	 *             异常
	 */
	public RSAUtil(int in, String address) throws NoSuchAlgorithmException,
			FileNotFoundException, IOException {
		kpg = KeyPairGenerator.getInstance("RSA"); // 创建‘密匙对’生成器
		kpg.initialize(in); // 指定密匙长度（取值范围：512～2048）
		kp = kpg.genKeyPair(); // 生成‘密匙对’，其中包含着一个公匙和一个私匙的信息
		public_key = kp.getPublic(); // 获得公匙
		private_key = kp.getPrivate(); // 获得私匙

		sun.misc.BASE64Encoder b64 = new sun.misc.BASE64Encoder();
		String pkStr = b64.encode(public_key.getEncoded());
		String prStr = b64.encode(private_key.getEncoded());
		System.out.println("pkStr length:" + pkStr.length() + pkStr);

		FileWriter fw = new FileWriter(address + "/private_key.dat");
		fw.write(prStr);
		fw.close();

		FileWriter fw2 = new FileWriter(address + "/public_key.dat");
		fw2.write(pkStr);
		fw2.close();
	}

	/**
	 * 加密的方法
	public static String encrypt(String keyUrl,String source,String enc) throws Exception {

		// 将文件中的公钥对象读出 
		FileReader fr = new FileReader(keyUrl);
		BufferedReader br = new BufferedReader(fr);// 建立BufferedReader对象，并实例化为br
		String getPbKey = "";
		while (true) {
			String aLine = br.readLine();
			if (aLine == null)
				break;
			getPbKey += aLine;
		}
		System.out.println("myBuilderStr :  length: " + getPbKey.length() + " " + getPbKey);
		BASE64Decoder b64d = new BASE64Decoder();
		byte[] keyByte = b64d.decodeBuffer(getPbKey);
		X509EncodedKeySpec x509ek = new X509EncodedKeySpec(keyByte);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PublicKey publicKey = keyFactory.generatePublic(x509ek);

		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		byte[] sbt = source.getBytes();
		byte[] epByte = cipher.doFinal(sbt);
		BASE64Encoder encoder = new BASE64Encoder();
		String epStr = encoder.encode(epByte);

		System.out.println("base64 encoder epStr:" +epStr.length()+"\n"+ epStr);
		
		if(enc!=null && !enc.trim().equals("")){
			epStr = encoder.encode(epStr.getBytes(enc));
		}
		System.out.println("base64 encoder epStr:" +epStr.length()+"\n"+ epStr);
		return epStr;

	}

	 */

	/**
	 * 加密的方法
	 */
	public static String encrypt(String keyUrl,String source,String enc) throws Exception {

		/** 将文件中的公钥对象读出 */
		FileReader fr = new FileReader(keyUrl);
		BufferedReader br = new BufferedReader(fr);// 建立BufferedReader对象，并实例化为br
		String getPbKey = "";
		while (true) {
			String aLine = br.readLine();
			if (aLine == null)
				break;
			getPbKey += aLine;
		}
		logger.debug("myBuilderStr :  length: " + getPbKey.length() + " " + getPbKey);
		BASE64Decoder b64d = new BASE64Decoder();
		byte[] keyByte = b64d.decodeBuffer(getPbKey);
		X509EncodedKeySpec x509ek = new X509EncodedKeySpec(keyByte);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PublicKey publicKey = keyFactory.generatePublic(x509ek);

		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		byte[] sbt = source.getBytes();
		byte[] epByte = cipher.doFinal(sbt);
		
		if(enc==null || enc.trim().equals("")){
			enc = default_enc;
		}
		
		String eptStr = new String(Base64.encodeBase64(epByte),enc);

        logger.debug("eptStr :  length"+eptStr.length()+"  " + eptStr);   
		return URLEncoder.encode(eptStr,enc);
//		return epByte;
	}
	/**
	 * 解密的方法
	public static String decrypt(String keyUrl,String cryptograph,String enc) throws Exception {

		FileReader fr = new FileReader(keyUrl);
		BufferedReader br = new BufferedReader(fr);// 建立BufferedReader对象，并实例化为
		String getPvKey = "";
		while (true) {
			String aLine = br.readLine();
			if (aLine == null)
				break;
			getPvKey += aLine;
		}
		BASE64Decoder b64d = new BASE64Decoder();
		byte[] keyByte = b64d.decodeBuffer(getPvKey);
		PKCS8EncodedKeySpec s8ek = new PKCS8EncodedKeySpec(keyByte);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PrivateKey privateKey = keyFactory.generatePrivate(s8ek);

		//** 得到Cipher对象对已用公钥加密的数据进行RSA解密 
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		BASE64Decoder decoder = new BASE64Decoder();

		if(enc!=null && !enc.trim().equals("")){
			byte d[] = decoder.decodeBuffer(cryptograph);
			cryptograph = new String(d,enc);
		}
		
		byte[] b1 = decoder.decodeBuffer(cryptograph);
		//** 执行解密操作 
		byte[] b = cipher.doFinal(b1);

		return new String(b);
	}
	*/
	
	/**
	 * 解密的方法
	 */
	public static String decrypt(String keyUrl,String cryptograph,String enc) throws Exception {

		FileReader fr = new FileReader(keyUrl);
		BufferedReader br = new BufferedReader(fr);// 建立BufferedReader对象，并实例化为
		String getPvKey = "";
		while (true) {
			String aLine = br.readLine();
			if (aLine == null)
				break;
			getPvKey += aLine;
		}
		BASE64Decoder b64d = new BASE64Decoder();
		byte[] keyByte = b64d.decodeBuffer(getPvKey);
		PKCS8EncodedKeySpec s8ek = new PKCS8EncodedKeySpec(keyByte);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PrivateKey privateKey = keyFactory.generatePrivate(s8ek);

		/** 得到Cipher对象对已用公钥加密的数据进行RSA解密 */
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, privateKey);

		if(enc==null || enc.trim().equals("")){
			enc = default_enc;
		}
		String deStr = URLDecoder.decode(cryptograph,enc);
		
		byte d[]  = Base64.decodeBase64(deStr.getBytes(enc));
		/** 执行解密操作 */
		byte[] b = cipher.doFinal(d);

		return new String(b);
	}

	/**
	public static void main(String[] args) {
		try {
			// new KeyRSA(1024, "D:/"); //私匙和公匙保存到D盘下的文件中.
			System.out.println("");
			String getEptStr = encrypt("d://public_key.dat","我问问的sd实打d实的","UTF-8");
			System.out.println("getEptStr:" +getEptStr.length()+"\n"+ getEptStr);

//			System.out.println("");
//			BASE64Encoder encoder = new BASE64Encoder();
//			String epStr = encoder.encode(getEptStr.getBytes("UTF-8"));
//			System.out.println("base64 encoder epStr:" +epStr.length()+"\n"+ epStr);
//
//			System.out.println("");
//			BASE64Decoder decoder = new BASE64Decoder();
//			byte d[] = decoder.decodeBuffer(epStr);
//			String deStr = new String(d,"UTF-8");
//			System.out.println("base64 decoder deStr:" +deStr.length()+"\n"+ deStr);

			System.out.println("");
			String drpStr = decrypt("d://private_key.dat",getEptStr,"UTF-8");
			System.out.println("drpStr:" + drpStr);
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (NoSuchAlgorithmException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
	 * @throws Exception 
	
	**/
	
	public static void main(String[] args) throws Exception {
		
	    // 创建公钥和私钥
	    RSAUtil rsaUstil = new RSAUtil(512, "d://");
	    
//		for(int i=0; i<100;i++){
	        // 公钥加密
			String getEptStr = encrypt("d://public_key.dat","需要加密的","UTF-8");
	        System.out.println("getEptStrlen : "+getEptStr.length());
	        System.out.println("getEptStr : " + getEptStr);

	        // 私钥解密
	        String drpStr = decrypt("d://private_key.dat",getEptStr,"UTF-8");
			System.out.println("drpStr:" + drpStr);
			System.out.println("==================================");
//		}
	}

}