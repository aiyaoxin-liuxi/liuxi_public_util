package com.liuxi.designpattern.factory.pojo;


public class OperationSub extends Operation {
    
    @Override
    public double getResult() throws Exception{
        double result = 0;
        if(getNumberB() == 0){
            throw new Exception("除数不能为0");
        }
        result = getNumberA() / getNumberB();
        return result;
    }

}
