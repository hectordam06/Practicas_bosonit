package com.bosonit.block5profilesV2;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

    @Component
    public class MyCommandLineRunner implements CommandLineRunner {

        @Autowired
        private Environment environment;

        @Override
        public void run(String... args) throws Exception {
            String activeProfile = environment.getProperty("spring.profiles.active");
            String bdUrl = environment.getProperty("bd.url");

            System.out.println("Active profile: " + activeProfile);
            System.out.println("bd.url: " + bdUrl);
        }
    }

