package com.liuxi.util;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestUtil implements Runnable {
	
	private static int counts = 1000;
	private static int a = 0;
	private Lock lock = new ReentrantLock();
	
	public static void main(String[] args) {
		start();
	}
	
	public static void start(){
		
		TestUtil tu = new TestUtil();
		
		new Thread(tu).start();
		new Thread(tu).start();
		
	}

	@Override
	public void run() {
		for (int i = 1; i <= counts; i++) {
			test(Thread.currentThread());
		}
	}
	
	public void test(Thread thread){
		lock.lock();
		System.out.println("线程:"+ thread +"执行：" + a++);
		lock.unlock();
	}
}
