package com.liuxi.designpattern.factory.pojo;

/**
 * 工厂模式 -- 计算器---父类
 * @author 刘熙
 * 2017-7-4
 */
public class Operation {
    
    private double numberA = 0;
    private double numberB = 0;
    /**
     * @return the numberA
     */
    public double getNumberA() {
        return numberA;
    }
    /**
     * @param numberA the numberA to set
     */
    public void setNumberA(double numberA) {
        this.numberA = numberA;
    }
    /**
     * @return the numberB
     */
    public double getNumberB() {
        return numberB;
    }
    /**
     * @param numberB the numberB to set
     */
    public void setNumberB(double numberB) {
        this.numberB = numberB;
    }
    
    
    public double getResult() throws Exception{
        double result = 0;
        return result;
    }

}
