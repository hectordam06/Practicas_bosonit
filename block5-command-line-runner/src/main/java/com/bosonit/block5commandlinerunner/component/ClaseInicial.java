package com.bosonit.block5commandlinerunner.component;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class ClaseInicial {
    @PostConstruct

    public void ejecutarPostConstruct(){
        System.out.println("Hola desde la clase inicial");
    }


}
