package com.liuxi.designpattern.strategy;

public class CashContext {
    
    CashSuper cashSuper = null;
    
    public CashContext(String type){
        
        switch (type) {
        case "正常收费":
            cashSuper = new CashNormal();
            break;
        case "满300返100":
            cashSuper = new CashRebate();
            break;
        case "打8折":
            cashSuper = new CashReturn();
            break;
        }
    }
    
    public double getResult(double money){
        return cashSuper.acceptCash(money);
    }
    

}
