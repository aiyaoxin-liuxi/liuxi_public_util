package com.liuxi.test.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class TestIo {
	
	public InputStream inputIo(){
		
		String inUrl = PropertiesUtil.getProperty("inputUrl");
		
		BufferedInputStream in = null;
		try {
			in = new BufferedInputStream(new FileInputStream(inUrl));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
		return in;
	}
	
	public void outputIo(InputStream in){
		
		String outUrl = PropertiesUtil.getProperty("outputUrl");
		
		BufferedOutputStream out = null;
		
		try {
			out = new BufferedOutputStream(new FileOutputStream(outUrl));
			
			byte[] b = new byte[1024];
			int len = 0;
			
			while((len = in.read(b)) != -1){
				out.write(b, 0, len);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(null != out){
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(null != in){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		try {
			TestIo t = new TestIo();
			
			t.outputIo(t.inputIo());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
