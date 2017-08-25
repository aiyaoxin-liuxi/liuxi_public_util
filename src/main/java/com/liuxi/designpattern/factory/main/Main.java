package com.liuxi.designpattern.factory.main;

import com.liuxi.designpattern.factory.pojo.Operation;
import com.liuxi.designpattern.factory.util.OperationFactory;

/**
 * 工厂模式main方法
 * @author 刘熙
 * 2017-7-5
 */
public class Main {
    
    public static void main(String[] args) {

        Operation operation = OperationFactory.createOperation("-");
        operation.setNumberA(1);
        operation.setNumberB(2);
        try {
            System.out.println(operation.getResult());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
