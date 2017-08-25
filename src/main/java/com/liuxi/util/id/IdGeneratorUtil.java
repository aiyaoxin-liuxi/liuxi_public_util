package com.liuxi.util.id;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * id生成
 * 当前时间+随即3位数
 */
public class IdGeneratorUtil {
	
	private static Lock lockId = new ReentrantLock();
	
    public static Long generateLongId() {
    	StringBuilder id = new StringBuilder();
    	try {
    		lockId.lock();
			id.append(System.currentTimeMillis());

			Random random = new Random();
			for (int i = 0; i < 3; i++) {
				id.append(random.nextInt(10));// 取三个随机数追加到StringBuffer
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lockId.unlock();
		}
        return Long.valueOf(Long.parseLong(id.toString()));
    }
    
    /**
	 * 生成key1（order_parent.trid
	 * key1=yyMMddHHmmss+3位流水号
	 * @return
	 */
	public static Long genKey1() {
		//生成yyMMddHHmmss
		String date = "";
		int intVal=0;
		String sn = "";
		synchronized (GlobalVar.Key1Sn) {
			date = String.valueOf(System.currentTimeMillis());
		    if (!GlobalVar.Key1Sn.getKey().equals(date)) {
		        GlobalVar.Key1Sn.setKey(date);
		        GlobalVar.Key1Sn.setValue(0);
            } else {
                GlobalVar.Key1Sn.setValue(GlobalVar.Key1Sn.getValue()+1);
            }
		    intVal = GlobalVar.Key1Sn.getValue();
		    
			if (intVal < 10) {
				sn = "00"+String.valueOf(intVal);
			} else if (intVal >=10 && intVal < 100) {
				sn = "0"+String.valueOf(intVal);
			}
		}
		return Long.valueOf(date+sn);
	}
    
    
    
}
