package br.forsign.allo;

import org.springframework.boot.SpringApplication;

/*
 * @author prandini
 * created 5/22/24
 */
public class AlloApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(AllOApplication.class);
    }

    public static SpringApplication getApp(){
        return new SpringApplication(AllOApplication.class);
    }
}
