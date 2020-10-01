package fr.brandon.tp3.part1_2.provider.implementation;

import org.springframework.stereotype.Component;

import fr.brandon.tp3.part1_2.provider.api.Provider;

@Component
public class ProviderImpl implements Provider
{

    @Override
    public double getPrice(String article)
    {
        System.out.println("Provider: Prix de " + article);
        return 1.20;
    }

    @Override
    public void order(String article, int amount)
    {
        System.out.println("Provider: Commande de " + amount + " " + article);
    }
}
