package com.liuxi.designpattern.strategy;

/**
 * 折扣
 * @author 刘熙
 *
 * 2017-7-5
 */
public class CashRebate extends CashSuper {

    @Override
    public double acceptCash(double money) {
        System.out.println("算法B实现");
        return money;
    }

}
