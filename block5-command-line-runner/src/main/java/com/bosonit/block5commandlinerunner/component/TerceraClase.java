package com.bosonit.block5commandlinerunner.component;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TerceraClase implements CommandLineRunner {
@Override
public void run (String... args){
    System.out.println("Soy la tercera clase");

    if (args.length>0){
        System.out.println("Estos son los valores pasados como parametro: ");
        for (String arg: args){
            System.out.println(arg);
        }
    }


}
}
