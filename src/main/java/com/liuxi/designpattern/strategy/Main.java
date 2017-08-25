package com.liuxi.designpattern.strategy;

public class Main {
    
    public static void main(String[] args) {
        double money = 0.0d;
        double txtPrices = 100;//单价
        double txtNum = 2;// 数量
        CashContext cashContext = new CashContext("正常收费");
        money = cashContext.getResult(txtPrices * txtNum);
        System.out.println(money);
    }

}
