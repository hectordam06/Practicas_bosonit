package com.bosonit.block5properties;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
public class LeerComponentes implements CommandLineRunner {
    @Value("${greeting}")
    private String greeting;

     @Value("${my.number}")
      private int myNumber;

     @Value("${new.property:El valor de new.property es Sin valor}")
        private String newProperty;
    @Value("${MYURL}")
    private String MYURL;
    @Value("${MYURL2:NO_tengo_valor}")
    private String MYURL2;

    @Override
    public void run(String... args) throws Exception {

        System.out.println("El valor de MYURL es: "+MYURL);
        System.out.println("El valor de MYURL2 es: "+MYURL2);


    }
    @PostConstruct


    public  void LeerComponente(){


        System.out.println("El valor de greeting es: " + greeting);
        System.out.println("El valor de my.number es: " + myNumber);
        System.out.println("El valor de new.property es: " + newProperty);

    }
}
