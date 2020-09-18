package fr.brandon.tp3.part1.store.implementation;

import org.springframework.stereotype.Component;

import fr.brandon.tp3.part1.bank.api.Bank;
import fr.brandon.tp3.part1.client.api.Client;
import fr.brandon.tp3.part1.provider.api.Provider;
import fr.brandon.tp3.part1.store.api.FastLane;
import fr.brandon.tp3.part1.store.api.JustHaveALook;
import fr.brandon.tp3.part1.store.api.Lane;
import fr.brandon.tp3.part1.store.api.Store;

@Component
public class StoreImpl implements FastLane, JustHaveALook, Lane, Store
{
    private Bank bank;
    private Provider provider;

    public StoreImpl(Bank bank, Provider provider)
    {
        this.bank = bank;
        this.provider = provider;
    }

    @Override
    public void addItemToCart(String article, Client client)
    {
        System.out.println("Ajout de " + article + " dans le panier");
    }

    @Override
    public void pay(Client client)
    {
        System.out.println("Paiement du panier");
    }

    @Override
    public double getPrice(String article)
    {
        System.out.println("Prix de " + article);
        
        return 1.50;
    }

    @Override
    public boolean isAvailable(String article, int amount)
    {
        System.out.println("Disponibilité de " + amount + " " + article);
        
        return true;
    }

    @Override
    public void oneShotOrder(String article, int amount)
    {
        System.out.println("Achat de " + amount + " " + article);
    }
}
