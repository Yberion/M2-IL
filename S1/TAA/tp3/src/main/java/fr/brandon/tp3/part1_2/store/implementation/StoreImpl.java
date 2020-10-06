package fr.brandon.tp3.part1_2.store.implementation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import fr.brandon.tp3.part1_2.bank.api.Bank;
import fr.brandon.tp3.part1_2.provider.api.Provider;
import fr.brandon.tp3.part1_2.store.api.FastLane;
import fr.brandon.tp3.part1_2.store.api.JustHaveALook;
import fr.brandon.tp3.part1_2.store.api.Lane;
import fr.brandon.tp3.part1_2.store.api.Store;

@Component
public class StoreImpl implements FastLane, JustHaveALook, Lane, Store
{
    private Bank bank;
    private Provider provider;
    @Value("${storeName:Olivier}")
    private String name;

    public StoreImpl(Bank bank, Provider provider)
    {
        this.bank = bank;
        this.provider = provider;
    }

    @Override
    public void addItemToCart(String article, String client)
    {
        System.out.println("Ajout de " + article + " dans le panier au client " + client);
    }

    @Override
    public void pay(String client)
    {
        System.out.println("Paiement du panier par le client " + client);
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
        this.provider.order(article, amount);
        return true;
    }

    @Override
    public void oneShotOrder(String article, int amount)
    {
        System.out.println("Achat de " + amount + " " + article);
        this.bank.transfert("Brandon", "Olivier", 15);
    }
}
