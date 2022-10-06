package com.fundamentosplatzi.springboot.fundamentos.bean;

public class MyBeanWithPorpertiesImplement implements MyBeanWithProperties{
    private String name;
    private String apellido;

    public MyBeanWithPorpertiesImplement(String name, String apellido) {
        this.name = name;
        this.apellido = apellido;
    }

    @Override
    public String function() {
        return name + "-" + apellido;
    }
}
