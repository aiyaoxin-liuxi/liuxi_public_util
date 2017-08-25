package com.liuxi.designpattern.strategy;

/**
 * 策略模式--上下文
 *      用一个ConcreteStrategy来配置，维护一个Strategy对象引用
 * @author 刘熙
 * 2017-7-5
 */
public class Context {
    
    CashSuper cashSuper = null;
    
    /**
     * 初始化时候传入具体的策略对象
     * @param strategy
     */
    public Context(CashSuper cashSuper){
        this.cashSuper = cashSuper;
    }
    
    public double getResult(double money){
        return cashSuper.acceptCash(money);
    }
    

}
