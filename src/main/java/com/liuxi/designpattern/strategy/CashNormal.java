package com.liuxi.designpattern.strategy;

/**
 * 正常
 * @author 刘熙
 *
 * 2017-7-5
 */
public class CashNormal extends CashSuper {

    @Override
    public double acceptCash(double money) {
        System.out.println("算法A实现");
        return money;
    }


}
