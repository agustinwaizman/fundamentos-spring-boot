package com.fundamentosplatzi.springboot.fundamentos.component;

import org.springframework.stereotype.Component;

@Component
public class ComponentToImplement implements ComponentDependency{

    @Override
    public void saludar() {
        System.out.println("Hola mundo, aca andamo");
    }
}
