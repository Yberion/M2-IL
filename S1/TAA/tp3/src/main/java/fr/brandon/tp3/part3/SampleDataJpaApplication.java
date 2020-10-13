package fr.brandon.tp3.part3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.oas.annotations.EnableOpenApi;

@SpringBootApplication
@EnableOpenApi
public class SampleDataJpaApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(SampleDataJpaApplication.class, args);
    }
}