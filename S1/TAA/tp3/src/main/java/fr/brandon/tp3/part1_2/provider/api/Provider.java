package fr.brandon.tp3.part1_2.provider.api;

public interface Provider
{
    double getPrice(String article);

    void order(String article, int amount);
}
