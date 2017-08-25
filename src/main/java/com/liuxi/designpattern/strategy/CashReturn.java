package com.liuxi.designpattern.strategy;

/**
 * 满返
 * @author 刘熙
 * 2017-7-5
 */
public class CashReturn extends CashSuper {

    @Override
    public double acceptCash(double money) {
        System.out.println("算法C实现");
        return money;
    }

}
