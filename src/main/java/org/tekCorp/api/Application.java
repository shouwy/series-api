package org.tekCorp.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by Inspiron on 15/06/2015.
 */
@SpringBootApplication
public class Application {

    /**
     * Classe Main starter de l'application SpringBoot
     *
     * @param args argument de lancement
     * @throws Exception Exception
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
