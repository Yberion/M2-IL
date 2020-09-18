package fr.brandon.tp3.part1.store.api;

import fr.brandon.tp3.part1.client.api.Client;

public interface Lane
{
    void addItemToCart(String article, Client client);
    void pay(Client client);
}
