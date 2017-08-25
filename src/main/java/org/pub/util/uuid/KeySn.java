package org.pub.util.uuid;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.pub.util.file.FileOperate;

public class KeySn {

	/**
	 * 得到n位长度的随机数
	 * 
	 * @param n
	 *            随机数的长度
	 * @return 返回 n位的随机整数
	 */
	private static int getRandomNumber(int n) {
		int temp = 0;
		int min = (int) Math.pow(10, n - 1);
		int max = (int) Math.pow(10, n);
		Random rand = new Random();

		while (true) {
			temp = rand.nextInt(max);
			if (temp >= min)
				break;
		}

		return temp;
	}

	public static String getKey() {
		return new SimpleDateFormat("yyMMddHHmm").format(new Date())+""+(System.currentTimeMillis()+"").substring(8)+ getRandomNumber(5);
	}

	public static void main(String[] args) {
		String str="";
		for (int i = 0; i < 10000; i++) {
			str+=KeySn.getKey()+"\n";
		}
		FileOperate.saveFile("d:\\test.txt", str);
//		str="fengyupengfengyupengfengyupengfengyupengfengyupfengyupengfengyupenfengyupengfengyupengfengyupengfengyupengfengyupfengyupengfengyupengfengyupengfengyupengfengyupfengyupengfengyupengfengyupengfengyupengfengyupfengyupengfengyupengfengyupengfengyupengfengyupfengyupengfengyupengfengyupengfengyupengfengyupfengyupengfengyupengfengyupengfengyupengfengyupfengyupengfengyupengfengyupengfengyupengfengyupfengyupengfengyupengfengyupengfengyupengfengyupfengyupengfengyupengfengyupengfengyupengfengyupgfengyupengfengyupengfengyupfenfengyupengfengyupengfengyupengfengyupengfengyupfengyupengfengyupenfengyupengfengyupengfengyupengfengyupengfengyupfengyupengfengyupengfengyupengfengyupengfengyupfengyupengfengyupengfengyupengfengyupengfengyupfengyupengfengyupengfengyupengfengyupengfengyupfengyupengfengyupengfengyupengfengyupengfengyupfengyupengfengyupengfengyupengfengyupengfengyupfengyupengfengyupengfengyupengfengyupengfengyupfengyupengfengyupengfengyupengfengyupengfengyupfengyupengfengyupengfengyupengfengyupengfengyupgfengyupengfengyupengfengyupfengyupengfengyupengfengyfengyupengfengyupengfengyupengfengyupengfengyupfengyupengfengyupenfengyupengfengyupengfengyupengfengyupengfengyupfengyupengfengyupengfengyupengfengyupengfengyupfengyupengfengyupengfengyupengfengyupengfengyupfengyupengfengyupengfengyupengfengyupengfengyupfengyupengfengyupengfengyupengfengyupengfengyupfengyupengfengyupengfengyupengfengyupengfengyupfengyupengfengyupengfengyupengfengyupengfengyupfengyupengfengyupengfengyupengfengyupengfengyupfengyupengfengyupengfengyupengfengyupengfengyupgfengyupengfengyupengfengyupfenfengyupengfengyupengfengyupengfengyupengfengyupfengyupengfengyupenfengyupengfengyupengfengyupengfengyupengfengyupfengyupengfengyupengfengyupengfengyupengfengyupfengyupengfengyupengfengyupengfengyupengfengyupfengyupengfengyupengfengyupengfengyupengfengyupfengyupengfengyupengfengyupengfengyupengfengyupfengyupengfengyupengfengyupengfengyupengfengyupfengyupengfengyupengfengyupengfengyupengfengyupfengyupengfengyupengfengyupengfengyupengfengyupfengyupengfengyupengfengyupengfengyupengfengyupgfengyupengfengyupengfengyupfengyupengfengyupengfengyupengfengyupengfengyupgyupengfengyupengfengyupengfengyupengfengyupupengfengyupengfengyupgyupengfengyupengfengyupengfengyupengfengyup";
//		System.out.println(str.length());
	}

}
