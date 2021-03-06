package fr.brandon.tp3.part1_2.servicetest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class HelloWorldService
{
    @Value("${name:World}")
    private String name;

    public String getHelloMessage()
    {
        return "Hello " + this.name;
    }
}
