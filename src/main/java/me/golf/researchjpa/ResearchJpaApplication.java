package me.golf.researchjpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@SpringBootApplication
public class ResearchJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResearchJpaApplication.class, args);
    }

    @PersistenceContext
    private EntityManager em;
}
