package com.liuxi.util.id;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Key1Sn {
	
	private String key;
    private int value;
    
    public Key1Sn() {
        this.key = new SimpleDateFormat("yyMMddHHmmss").format(new Date());
        this.value = 0;
    }
    public Key1Sn(String key,int value,int value2) {
        this.key = key;
        this.value = value;
    }
    
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
    @Override
    public String toString() {
        return "Key1Sn [key=" + key + ", value=" + value + "]";
    }

}
