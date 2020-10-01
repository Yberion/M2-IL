package fr.brandon.tp3.part1_2.store.api;

public interface Lane
{
    void addItemToCart(String article, String client);
    void pay(String client);
}
