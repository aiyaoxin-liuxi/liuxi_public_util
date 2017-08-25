package com.liuxi.designpattern.Decoration;

/**
 * 装饰模式
        人类
 * @author 刘熙
 *
 * 2017-7-5
 */
public class Person {
    
    private String name;
    
    public Person(){}
    
    public Person(String name){
        this.name = name;
    }
    
    public void Show(){
        System.out.println("装扮的{0}");
    }

}
