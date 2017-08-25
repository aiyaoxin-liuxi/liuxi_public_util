package com.liuxi.designpattern.Decoration;

public class Finery extends Person {

    public Person person;
    
    public void decorate(Person person){
        this.person = person;
    }
    
    public void show(){
        if(null != person){
            person.Show();
        }
    }


}
