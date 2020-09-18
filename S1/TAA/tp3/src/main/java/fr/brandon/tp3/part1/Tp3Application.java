package fr.brandon.tp3.part1;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.brandon.tp3.part1.client.api.Client;
import fr.brandon.tp3.part1.servicetest.HelloWorldService;

@SpringBootApplication
public class Tp3Application implements CommandLineRunner
{
    private HelloWorldService helloWorldService;
    private Client client;

    public Tp3Application(HelloWorldService helloWorldService, Client client)
    {
        this.helloWorldService = helloWorldService;
        this.client = client;
    }

    @Override
    public void run(String... args)
    {
        System.out.println(this.helloWorldService.getHelloMessage());
        this.client.run();
    }
    
    public static void main(String... args)
    {
        SpringApplication.run(Tp3Application.class, args);
    }
}
