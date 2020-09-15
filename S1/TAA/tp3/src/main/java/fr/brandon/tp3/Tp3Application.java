package fr.brandon.tp3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.brandon.tp3.service.HelloWorldService;

@SpringBootApplication
public class Tp3Application implements CommandLineRunner
{
    @Autowired
    private HelloWorldService helloWorldService;

    @Override
    public void run(String... args)
    {
        System.out.println(this.helloWorldService.getHelloMessage());
    }

    public static void main(String[] args)
    {
        SpringApplication.run(Tp3Application.class, args);
    }
}
