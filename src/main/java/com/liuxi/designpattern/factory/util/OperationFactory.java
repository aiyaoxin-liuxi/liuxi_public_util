package com.liuxi.designpattern.factory.util;

import com.liuxi.designpattern.factory.pojo.Operation;
import com.liuxi.designpattern.factory.pojo.OperationAdd;
import com.liuxi.designpattern.factory.pojo.OperationDiv;
import com.liuxi.designpattern.factory.pojo.OperationMul;
import com.liuxi.designpattern.factory.pojo.OperationSub;

/**
 * 工厂模式工厂类
 * @author 刘熙
 *
 * 2017-7-5
 */
public class OperationFactory {
    
    public static Operation createOperation(String operate){
        
        Operation operation = null;
        switch(operate){
            case "+":
                operation = new OperationAdd();
                break;
            case "-":
                operation = new OperationMul();
                break;
            case "*":
                operation = new OperationDiv();
                break;
            case "/":
                operation = new OperationSub();
                break;
        }
        return operation;
    }
    
    

}
